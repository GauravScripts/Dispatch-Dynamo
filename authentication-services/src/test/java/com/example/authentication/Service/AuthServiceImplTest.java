package com.example.authentication.Service;

import com.example.authentication.DTO.LogInData;
import com.example.authentication.Domain.Authentication;
import com.example.authentication.Domain.SignUpDataUser;
import com.example.authentication.Exception.UserAlreadyPresentException;
import com.example.authentication.Exception.UserNotFoundException;
import com.example.authentication.RabbitMq.DataProducer;
import com.example.authentication.Repositery.AuthRepositery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    @Mock
    private AuthRepositery authRepositery;

    @Mock
    private DataProducer dataProducer;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateOtp() {
        String email = "test@example.com";
        int otp = authService.generateotp(email);
        assertNotNull(otp);
    }

    @Test
    public void testUpdatePassword() {
        String email = "test@example.com";
        String newPassword = "newPassword";
        Authentication auth = new Authentication(email, "oldPassword", "User");
        when(authRepositery.findById(email)).thenReturn(Optional.of(auth));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        boolean result = authService.updatePassword(email, newPassword);
        assertTrue(result);
        verify(authRepositery, times(1)).findById(email);
        verify(authRepositery, times(1)).save(auth);
    }

    @Test
    public void testValidateUser() throws UserNotFoundException {
        String email = "example@gmail.com";
        String password = "password123";

        Authentication auth = new Authentication(email, password, "User");
        when(authRepositery.findById(email)).thenReturn(Optional.of(auth));
        LogInData logInData = new LogInData("example@gmail.com", "password123", "User");

        boolean result = authService.validateUser(logInData);
        assertFalse(result);
        verify(authRepositery, times(1)).findById(email);
    }


}
