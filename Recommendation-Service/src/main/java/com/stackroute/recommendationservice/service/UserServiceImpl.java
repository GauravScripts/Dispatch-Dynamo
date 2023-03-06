package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.VendorModel;
import com.stackroute.recommendationservice.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private VendorRepository vendorRepository;


    @Override
    public List<VendorModel> getUsersByCity(String city) {
        return vendorRepository.getUsersByCity(city);
    }
    @Override
    public List<VendorModel>getalldetails(){
        return vendorRepository.getalldetails();
    }

    public List<VendorModel> getallvendors()
    {
        return vendorRepository.findAll();
    }

   public VendorModel saveVendor(VendorModel vendor)
   {
       return vendorRepository.save(vendor);
   }


}


