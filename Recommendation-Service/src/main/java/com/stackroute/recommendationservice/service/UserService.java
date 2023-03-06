package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.VendorModel;

import java.util.List;

public interface UserService {

    public abstract List<VendorModel> getUsersByCity(String city);
  public abstract List<VendorModel> getalldetails();
}
