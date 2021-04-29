package kz.csse.balling.basketballing.rest;

//import com.javaee.task3javaee.entities.Users;
//import com.javaee.task3javaee.jwt.JwtTokenGenerator;
//import com.javaee.task3javaee.models.JwtRequest;
//import com.javaee.task3javaee.models.JwtResponse;
//import com.javaee.task3javaee.models.UserDTO;
//import com.javaee.task3javaee.services.UserService;
import kz.csse.balling.basketballing.entites.Users;
import kz.csse.balling.basketballing.jwt.JwtTokenGenerator;
import kz.csse.balling.basketballing.models.JwtRequest;
import kz.csse.balling.basketballing.models.JwtResponse;
import kz.csse.balling.basketballing.models.UserDTO;
import kz.csse.balling.basketballing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/auth")
    public ResponseEntity<?> auth(@RequestBody JwtRequest request) throws Exception{
//        authenticate(request.getEmail(),request.getPassword());

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        }catch (DisabledException e){
            return new ResponseEntity<>("USER_DISABLE",HttpStatus.BAD_GATEWAY);

        }catch (BadCredentialsException e){

            return new ResponseEntity<>("INVALID_CREDENTIALS",HttpStatus.BAD_GATEWAY);
        }
        final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        if(userDetails.getUsername() == null){
            return new ResponseEntity<>("No such user",HttpStatus.BAD_GATEWAY);
        }
        if(userDetails.isEnabled()){
            final String token = jwtTokenGenerator.generateToken(userDetails);
            Date date = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 7);
            date = c.getTime();

            return new ResponseEntity<>(new JwtResponse(token,date), HttpStatus.OK);
        }



        return new ResponseEntity<>("Failed",HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> toRegister(@RequestBody Users user){
        Users newUser = new Users();
        newUser.setFullName(user.getFullName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setAva("https://www.kindpng.com/picc/m/24-248253_user-profile-default-image-png-clipart-png-download.png");
        return ResponseEntity.ok(userService.createUser(newUser));

    }

    @GetMapping(value = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<?> profilePage(){
        Users user = getUser();

        assert user != null;
        return new ResponseEntity<>(new UserDTO(user.getId(), user.getEmail(), user.getFullName(),user.getAva(), user.getPassword(), user.getRoles()), HttpStatus.OK);
    }

    public void authenticate(String email, String password) throws Exception{

        try{

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (DisabledException e){
            System.out.println("USER_DISABLED");
            throw new Exception("USER_DISABLED", e);

        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            Users user = (Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }
}
