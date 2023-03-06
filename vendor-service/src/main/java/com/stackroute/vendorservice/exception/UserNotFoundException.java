package com.stackroute.vendorservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = org.springframework.http.HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends Exception{
}
