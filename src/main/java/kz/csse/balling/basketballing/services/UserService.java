package kz.csse.balling.basketballing.services;


import kz.csse.balling.basketballing.entites.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users getUserByEmail(String email);

    String createUser(Users user);
    Users updateProfile(Users user);
    Users updatePassword(Users user);
    String confirmToken(String token);


}

