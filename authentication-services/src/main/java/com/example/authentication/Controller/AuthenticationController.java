package com.example.authentication.Controller;


import com.example.authentication.DTO.LogInData;
import com.example.authentication.Domain.Authentication;
import com.example.authentication.Domain.SignUpDataUser;
import com.example.authentication.Exception.UserAlreadyPresentException;
import com.example.authentication.Exception.UserNotFoundException;
import com.example.authentication.Service.AuthService;
import com.example.authentication.Token.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    // http://localhost:9201/api/v1/auth/adduser
    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody @Valid SignUpDataUser signUpDataUser) throws UserAlreadyPresentException {

    return new ResponseEntity<>(authService.addUser(signUpDataUser), HttpStatus.OK);
    }

    @PostMapping("/getOtp")
    public int sendMail(@RequestBody String mail)
    {
        return authService.generateotp(mail);
    }


@PostMapping("/genratetoken")
public ResponseEntity<?> addCustomer(@RequestBody LogInData logInData) throws UserNotFoundException {
    System.out.println(logInData);
    boolean a = authService.validateUser(logInData);
    System.out.println(a);
    if(a)
    {
        Optional<Authentication> a1=authService.getDataById(logInData);
        return new ResponseEntity<>(securityTokenGenerator.generateToken(a1), HttpStatus.OK);
    }
    else {
        return new ResponseEntity<>("Authonthication Failed....", HttpStatus.NOT_FOUND);
    }

}

    // http://localhost:9201/api/v1/auth/updatepassword
@PostMapping("/updatepassword")
public ResponseEntity<?> updatePassword(@RequestBody List<String> l1)
{
    return new ResponseEntity<>(authService.updatePassword(l1.get(0),l1.get(1)),HttpStatus.ACCEPTED);
}
}
