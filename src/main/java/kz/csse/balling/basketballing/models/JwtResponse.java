package kz.csse.balling.basketballing.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 987654321L;



    private String jwtToken;
    private Date jwtExpired;

//    public JwtResponse(String jwtToken){
//        this.jwtToken = jwtToken;
//    }

    public String getJwtToken(){
        return this.jwtToken;
    }


}
