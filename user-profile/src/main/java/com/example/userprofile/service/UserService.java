package com.example.userprofile.service;

import com.example.userprofile.domain.ModelImpl.UserModel;
import com.example.userprofile.exception.UserAlreadyExistException;
import com.example.userprofile.exception.UserNotExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public abstract UserModel addUser(UserModel user)throws UserAlreadyExistException;
    public abstract UserModel getUserById(String id);
    public abstract UserModel updateUserById(String emailId,UserModel user)throws UserNotExistException;

    public abstract boolean deleteUserById(String emailId) throws UserNotExistException;

    public String getCity(String emailId) throws UserNotExistException;

}
