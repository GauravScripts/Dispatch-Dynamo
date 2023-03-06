package com.example.authentication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "User Already Present Use Different Id")
public class UserAlreadyPresentException extends Exception{
}
