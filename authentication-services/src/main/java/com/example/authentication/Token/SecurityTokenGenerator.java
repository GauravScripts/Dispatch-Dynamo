package com.example.authentication.Token;

import com.example.authentication.Domain.Authentication;

import java.util.Map;
import java.util.Optional;

public interface SecurityTokenGenerator {

   Map<String, String> generateToken(Optional<Authentication> logInData);
}
