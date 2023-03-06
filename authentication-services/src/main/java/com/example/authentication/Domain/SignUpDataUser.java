package com.example.authentication.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"password", "cnfPassword"})
public class SignUpDataUser {
    private String name;
    @NotBlank(message = "Email Id is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email Id is invalid")
    private String emailId;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$",
            message = "Password must contain at least one digit, one lower case, one upper case, one special character and no white space, and must be between 8 to 20 characters long")
    private String password;
    @NotBlank(message = "Confirm password is mandatory")
    private String cnfPassword;
    private String role;
}
