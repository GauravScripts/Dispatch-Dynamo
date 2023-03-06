package com.example.userprofile.configuration;

import com.example.userprofile.domain.AddressModel;
import com.example.userprofile.domain.ModelImpl.UserModel;
import com.example.userprofile.exception.UserAlreadyExistException;
import com.example.userprofile.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserService userService;
    @RabbitListener(queues = "User_Queue")
    public void getDataFromQueue(UserData userData) throws UserAlreadyExistException {
        UserModel userModel=new UserModel();
        userModel.setEmailId(userData.getEmailId());
        userModel.setName(userData.getName());
        userModel.setMobileNo(userData.getMobileNo());
        System.out.println(userModel);
//        Address address=userData.getAddress();
//        AddressModel addressModel=new AddressModel(address.getAddressLine1(), address.getCity(), address.getState(), address.getCountry(), address.getZipcode());
//
//        userModel.setAddress(addressModel);
        userService.addUser(userModel);
    }

}
