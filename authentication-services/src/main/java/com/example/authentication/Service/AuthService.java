package com.example.authentication.Service;

import com.example.authentication.Domain.Authentication;
import com.example.authentication.DTO.LogInData;
import com.example.authentication.Domain.SignUpDataUser;
import com.example.authentication.Exception.UserAlreadyPresentException;
import com.example.authentication.Exception.UserNotFoundException;

import java.util.Optional;

public interface AuthService {

    public int generateotp(String email);
    public int generaterandom();

    public Authentication addUser(SignUpDataUser signUpDataUser) throws UserAlreadyPresentException;

    public String encodepassword(String password);

    public boolean updatePassword(String email,String newPassword);

    public boolean validateUser(LogInData logInData) throws UserNotFoundException;

    public Optional<Authentication> getDataById(LogInData logInData);
}
