package com.example.userprofile.controller;

import com.example.userprofile.domain.ModelImpl.UserModel;
import com.example.userprofile.exception.UserAlreadyExistException;
import com.example.userprofile.exception.UserNotExistException;

import com.example.userprofile.repository.UserRepository;
import com.example.userprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profile")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //Optional
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserModel user) throws UserAlreadyExistException {

        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    //http://localhost:8085/user-profile/updateUserById/
    @PutMapping("/updateUserById/{emailId}")
    public ResponseEntity<?> updateUser(@PathVariable String emailId,@RequestBody UserModel user)throws UserNotExistException {
        try {
            return new ResponseEntity<>(userService.updateUserById(emailId, user), HttpStatus.OK);
        } catch (UserNotExistException e) {
            throw new UserNotExistException();
        }

    }
//http://localhost:8085/user-profile/deleteUserById/
    @DeleteMapping("/deleteUserById/{emailId}")
public ResponseEntity<?> deleteUser(@PathVariable String emailId) throws UserNotExistException
{
    try
    {
        userService.deleteUserById(emailId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    catch (UserNotExistException userNotExistException)
    {
        throw new UserNotExistException();
    }
}


    //http://localhost:8085/user-profile/getUserById/{emailId}
    @GetMapping("/getUserById/{emailId}")
    public ResponseEntity<?> getUserById(@PathVariable String emailId) {
        return new ResponseEntity<>(userService.getUserById(emailId), HttpStatus.OK);
    }

    @GetMapping("/getUserCityById/{emailId}")
    public ResponseEntity<?> getUserCityById(@PathVariable String emailId) throws UserNotExistException {
        return new ResponseEntity<>(userService.getCity(emailId), HttpStatus.OK);
    }

}

