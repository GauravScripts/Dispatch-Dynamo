package com.example.authentication.Controller;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.authentication.DTO.LogInData;
import com.example.authentication.Domain.Authentication;
import com.example.authentication.Domain.SignUpDataUser;
import com.example.authentication.Exception.UserAlreadyPresentException;
import com.example.authentication.Exception.UserNotFoundException;
import com.example.authentication.Service.AuthService;
import com.example.authentication.Token.SecurityTokenGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;





@RunWith(MockitoJUnitRunner.class)
public class AuthenticationControllerTest {

    @InjectMocks
    AuthenticationController authenticationController;

    @Mock
    AuthService authService;

    @Mock
    SecurityTokenGenerator securityTokenGenerator;

    @Test
    public void addUser_ShouldReturnResponseEntity_WhenValidInputIsGiven() throws UserAlreadyPresentException {
        SignUpDataUser signUpDataUser = new SignUpDataUser();
        Authentication authentication = new Authentication();
        Mockito.when(authService.addUser(signUpDataUser)).thenReturn(authentication);
        ResponseEntity<?> responseEntity = authenticationController.addUser(signUpDataUser);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void sendMail_ShouldReturnInteger_WhenValidInputIsGiven() {
        String mail = "test@example.com";
        Mockito.when(authService.generateotp(mail)).thenReturn(123);
        int response = authenticationController.sendMail(mail);
        Assert.assertEquals(123, response);
    }

    @Test
    public void generateToken_ShouldReturnResponseEntity_WhenValidInputIsGiven() throws UserNotFoundException {
        LogInData logInData = new LogInData();
        Authentication authentication = new Authentication();
        Mockito.when(authService.validateUser(logInData)).thenReturn(true);
        Mockito.when(authService.getDataById(logInData)).thenReturn(Optional.of(authentication));
        ResponseEntity<?> responseEntity = authenticationController.addCustomer(logInData);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updatePassword_ShouldReturnResponseEntity_WhenValidInputIsGiven() {
        List<String> l1 = Arrays.asList("test@example.com", "password");
        Mockito.when(authService.updatePassword(l1.get(0), l1.get(1))).thenReturn(true);
        ResponseEntity<?> responseEntity = authenticationController.updatePassword(l1);
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }
}
