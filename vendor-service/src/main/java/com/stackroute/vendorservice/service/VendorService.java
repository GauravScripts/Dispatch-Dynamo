package com.stackroute.vendorservice.service;

import com.stackroute.vendorservice.domain.*;
import com.stackroute.vendorservice.exception.UserNotFoundException;

import java.util.List;

public interface VendorService {
    VendorModel saveVendor(VendorModel vendorModel);
    Iterable<VendorModel> getAllVendors();
    VendorModel getVendorById(String emailId) throws UserNotFoundException;
    boolean deleteVendor(String emailId) throws UserNotFoundException;
    VendorModel patchVendorName(VendorModel vendorModel) throws UserNotFoundException;

    List<String> getAllCitiesOfVendor(String email);
    VendorModel updateCity(String city,String email);

    VendorModel deleteCity(String city,String email);

    Iterable<VendorModel> getAllVendors(String city1,String City2);

    VendorModel updateAddress(String email, AddressModel address);

    public VendorModel updatePrice(String email, UpdatePrice price);

    public TransportModel getDomesticPrice(String email);
    public TransportModelInternational getInternationalPrice(String email);

    VendorModel patchVendorDetails(String emailId , UpdatedData updatedData) throws UserNotFoundException;


}
