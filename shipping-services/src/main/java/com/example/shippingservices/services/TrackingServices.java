package com.example.shippingservices.services;

import com.example.shippingservices.domain.BookingDetails;
import com.example.shippingservices.domain.CalculatedPrice;
import com.example.shippingservices.domain.CityData;
import com.example.shippingservices.domain.VendorModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TrackingServices {

    Double calculateDistance(String city1 , String city2);

//    Double calculatePrice(Double weight, List<String> l2);

    public List<CityData> addData(List<CityData> city);
    public List<VendorModel> getVendors() throws IOException, InterruptedException;
    public List<VendorModel> filterVendorsbyCity(String city1 ,List<VendorModel> list1);
    public List<VendorModel> filteredCities(String city1 , String city2 ) throws IOException, InterruptedException;
    public List<CalculatedPrice> getDomesticPriceByCity(String city1 , String city2 , String Mode) throws IOException, InterruptedException;
    public List<CalculatedPrice> getInternaTionalPriceByCity(String city1 , String city2 , String mode) throws IOException, InterruptedException;
    public Optional<CityData> returnCityData(String cityname);
    public List<CalculatedPrice> getPrice(BookingDetails bookingDetails) throws IOException, InterruptedException;
}
