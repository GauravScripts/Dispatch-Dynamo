package com.example.authentication.Service;

import com.example.authentication.DTO.LogInData;
import com.example.authentication.DTO.UserData;
import com.example.authentication.DTO.VendorData;
import com.example.authentication.Domain.*;
import com.example.authentication.Exception.UserAlreadyPresentException;
import com.example.authentication.Exception.UserNotFoundException;
import com.example.authentication.RabbitMq.DataProducer;
import com.example.authentication.RabbitMq.MailServiceData;
import com.example.authentication.Repositery.AuthRepositery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthServiceImpl implements AuthService{

    String a="Hello welcome to our Application this is your one time password";

    @Autowired
    private DataProducer dataProducer;

    @Autowired
    private AuthRepositery authRepositery;


    @Override
    public int generateotp(String email) {
        int b;
        b=generaterandom();
        String c = a+" :"+b;
        System.out.println("The Body of the email is : "+c);
        MailServiceData m1 = new MailServiceData(email,"OTP For Registration to CargoPlus is ",c);
        System.out.println("********************"+m1);
         dataProducer.sendMailDataToQueue(m1);
        return b;
    }

    @Override
    public int generaterandom() {
        Random ab = new Random();
        int a = ThreadLocalRandom.current().nextInt(10000,999999);
        return a ;
    }

    public String encodepassword(String password)
    {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode(password);
        return pass;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        Authentication a1 = authRepositery.findById(email).get();
        a1.setPassword(encodepassword(newPassword));
        MailServiceData m1 =new MailServiceData(a1.getEmailId(),"Password Update","Your Password is Successfully Updated");
        dataProducer.sendMailDataToQueue(m1);
        authRepositery.save(a1);
        return true;
    }

    @Override
    public boolean validateUser(LogInData logInData) throws UserNotFoundException {
        Authentication a1 = authRepositery.findById(logInData.getEmailId()).get();
        PasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();

        if(a1!=null) {
            boolean match = passwordEncoder1.matches(logInData.getPassword(), a1.getPassword());
            return match ;
        }
        else
        {
            throw new UserNotFoundException();
        }

    }

    @Override
    public Optional<Authentication> getDataById(LogInData logInData) {
        System.out.println(logInData);
       Optional<Authentication> a=authRepositery.findById(logInData.getEmailId());
        System.out.println(a);
        return a;
    }

    @Override
    public Authentication addUser(SignUpDataUser signUpDataUser) throws UserAlreadyPresentException {
        if(authRepositery.findById(signUpDataUser.getEmailId()).isEmpty())
        {

            if(signUpDataUser.getRole().equalsIgnoreCase("User"))
            {
                Authentication a1 = new Authentication(signUpDataUser.getEmailId(), signUpDataUser.getPassword(),signUpDataUser.getRole());
                a1.setPassword(encodepassword(signUpDataUser.getPassword()));
                UserData u1 = new UserData(signUpDataUser.getEmailId(),signUpDataUser.getName());
                dataProducer.sendUserDataToQueue(u1);
                String c = "Welcome " + signUpDataUser.getName() + " To DispatchDynamo Family This is the mail to confirm that you are Succesfully Registered As a "+signUpDataUser.getRole();
                MailServiceData m1 = new MailServiceData(signUpDataUser.getEmailId(), " Successfully Registered ", c);
                dataProducer.sendMailDataToQueue(m1);
                return authRepositery.save(a1);

            }
            else
            {
                Authentication a1 = new Authentication(signUpDataUser.getEmailId(), signUpDataUser.getPassword(),signUpDataUser.getRole());
                a1.setPassword(encodepassword(signUpDataUser.getPassword()));
                VendorData u1 = new VendorData(signUpDataUser.getEmailId(),signUpDataUser.getName());
                dataProducer.sendVendorDataToQueue(u1);
                String c = "Welcome " + signUpDataUser.getName() + " To DispatchDynamo Family This is the mail to confirm that you are Succesfully Registered As a "+signUpDataUser.getRole();
                MailServiceData m1 = new MailServiceData(signUpDataUser.getEmailId(), " Successfully Registered ", c);
                dataProducer.sendMailDataToQueue(m1);
                return authRepositery.save(a1);

            }
        }
        else
        {
            throw new UserAlreadyPresentException();
        }

   }



}
