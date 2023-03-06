package com.example.authentication.Token;

import com.example.authentication.Domain.Authentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(Optional<Authentication> logInData) {
        Map<String,String> result= new HashMap<String,String>();
        Map<String,Object> login=new HashMap<String,Object>();
        logInData.get().setPassword(" ");
        login.put("User",logInData);
        login.put("user_email",logInData.get().getEmailId());
        login.put("role",logInData.get().getRole());
        String jwtToken= Jwts.builder()
                .setClaims(login)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,"G-KaPdSgVkYp3s6v9y/B?E(H+MbQeThWmZq4t7w!z%C&F)J@NcRfUjXn2r5u8x/A")
                .compact();
        result.put("Token",jwtToken);
        result.put("Message","UserSuccessfully Logged In");
        result.put("emailId",logInData.get().getEmailId());
        result.put("role",logInData.get().getRole());
        System.out.println(result);
        return result;
    }
}
