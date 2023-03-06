package com.example.userprofile.service;

import com.example.userprofile.configuration.Address;
import com.example.userprofile.domain.AddressModel;
import com.example.userprofile.domain.ModelImpl.UserModel;
import com.example.userprofile.exception.UserAlreadyExistException;
import com.example.userprofile.exception.UserNotExistException;

import com.example.userprofile.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel addUser(UserModel user)throws UserAlreadyExistException {
        if(userRepository.findById(user.getEmailId()).isEmpty()){
            AddressModel addressModel=new AddressModel("","","","","");
            user.setAddress(addressModel);
            System.out.println(user);
            return userRepository.save(user);
        }
        else throw new UserAlreadyExistException();
    }

    @Override
    public UserModel getUserById(String id) {

        return userRepository.findById(id).get();
    }

    @Override
    public UserModel updateUserById(String emailId,UserModel user)throws UserNotExistException {
        if(userRepository.findById(emailId).isPresent()){

          UserModel u1=  userRepository.findById(emailId).get();
          u1.setMobileNo(user.getMobileNo());
          u1.setName(user.getName());
          u1.setEmailId(user.getEmailId());
          u1.setAddress(user.getAddress());
            Address address=new Address();
            AddressModel addressModel=new AddressModel(address.getAddressLine1(), address.getCity(), address.getState(), address.getCountry(), address.getZipcode() );
            user.setAddress(addressModel);
            return userRepository.save(u1);
        }
        else throw new UserNotExistException();
    }

    @Override
//    , UserModel user
    public boolean deleteUserById(String emailId) throws UserNotExistException {
            if(userRepository.findById(emailId).isPresent()) {
                userRepository.deleteById(emailId);
                return true;
            }
            else throw new UserNotExistException();
    }

    @Override
    public String getCity(String emailId) throws UserNotExistException {
       if(userRepository.findById(emailId).isPresent())
       {
           UserModel u1 = userRepository.findById(emailId).get();
           String a= u1.getAddress().getCity();
           System.out.println(a);
           return a ;
       }
       else throw new UserNotExistException();
    }


}

