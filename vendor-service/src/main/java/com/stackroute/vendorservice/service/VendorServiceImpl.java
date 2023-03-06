package com.stackroute.vendorservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.vendorservice.domain.*;
import com.stackroute.vendorservice.exception.UserNotFoundException;
import com.stackroute.vendorservice.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService{
    VendorRepository vendorRepository;
    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    @Override
    public VendorModel saveVendor(VendorModel vendorModel) {
        System.out.println(vendorModel);
        return vendorRepository.save(vendorModel);
    }

    @Override
    public Iterable<VendorModel> getAllVendors() {
        System.out.println(vendorRepository.findAll());
        return vendorRepository.findAll();
    }

    @Override
    public VendorModel getVendorById(String emailId) throws UserNotFoundException{
        VendorModel vendorModel = vendorRepository.findById(emailId).isPresent()?vendorRepository.findById(emailId).get():null;
        if(vendorModel== null){
            throw new UserNotFoundException();
        }
        return vendorModel;

    }

    @Override
    public boolean deleteVendor(String emailId){
        VendorModel vendorModel = vendorRepository.findById(emailId).isPresent()?vendorRepository.findById(emailId).get():null;
        if(vendorModel!=null)
        {
            vendorRepository.deleteById(emailId);
            return true;
        }
        return false;
    }

    @Override
    public VendorModel patchVendorName(VendorModel vendorModel) throws UserNotFoundException {
        VendorModel existingVendor = vendorRepository.findById(vendorModel.getEmailId()).orElse(null);
        if (existingVendor == null) {
            throw new UserNotFoundException();
        }
        existingVendor.setName(vendorModel.getName());
        return vendorRepository.save(existingVendor);
    }

    @Override
    public List<String> getAllCitiesOfVendor(String email) {
      VendorModel v1 = vendorRepository.findById(email).get();
      List<String> cities = v1.getCities();
        return cities;
    }

    @Override
    public VendorModel updateCity(String city,String email) {
        System.out.println(email);
        System.out.println(city);
        VendorModel v1 = vendorRepository.findById(email).get();
        System.out.println("123456");
        System.out.println(v1);
        System.out.println(v1.getCities());
        if(v1.getCities()==null)
        {
           List<String> l1 = new ArrayList<>();
           l1.add(city);
           v1.setCities(l1);
        }
        else
        {
            v1.getCities().add(city);

        }
        return vendorRepository.save(v1);
    }

    @Override
    public VendorModel deleteCity(String city, String email) {
        VendorModel v1 = vendorRepository.findById(email).get();
        vendorRepository.deleteById(email);
        List<String> cities = v1.getCities();
        for(int i=0;i<cities.size();i++)
        {
            if(cities.contains(city))
            {
                cities.remove(city);
            }
        }
        v1.setCities(cities);
        return vendorRepository.save(v1);
    }

    @Override
    public List<VendorModel> getAllVendors(String city1, String City2) {
        System.out.println("************************************************");
        System.out.println(city1+"      "+City2);
        System.out.println(getAllVendors());
        List<VendorModel> list1 = (List<VendorModel>) vendorRepository.findAll();
        System.out.println();
        System.out.println("All list of vendors");
        System.out.println(list1);
        List<String> list2 = new ArrayList<>();
        List<VendorModel> list3 = new ArrayList<>();
        list2.add(city1);
        list2.add(City2);
        System.out.println("***********************");
        System.out.println(list2);
        for(int i=0;i< list1.size();i++)
        {
            if(list1.get(i).getCities().containsAll(list2))
            {
                list3.add(list1.get(i));
            }
        }
        return list3;

    }

    @Override
    public VendorModel updateAddress(String email, AddressModel address) {
        System.out.println(email+"    "+address);
        VendorModel v1 = vendorRepository.findById(email).get();
        System.out.println(v1);
        v1.setAddress(address);
        return vendorRepository.save(v1);
    }

    @Override
    public VendorModel updatePrice(String email, UpdatePrice price){
        VendorModel v1 = vendorRepository.findById(email).get();
        System.out.println(price);
        if(price.getPriceType().equalsIgnoreCase("International")) {
            System.out.println("Inside International");
            TransportModelInternational t1 = new TransportModelInternational();
            t1.setByAir(price.getByAir());
            t1.setByRailway(price.getByRailway());
            t1.setByRoad(price.getByRoad());
            System.out.println(t1);
            v1.getPrice().setInternationalPrice(t1);
            System.out.println(v1);
        }
        else
        if(price.getPriceType().equalsIgnoreCase("Domestics"))
        {
            System.out.println("Inside Domestic");
            TransportModel t1 = new TransportModel();
            t1.setByAir(price.getByAir());
            t1.setByRailway(price.getByRailway());
            t1.setByRoad(price.getByRoad());
            v1.getPrice().setDomesticPrice(t1);
        }
        vendorRepository.deleteById(v1.getEmailId());
        return vendorRepository.save(v1);
    }
public VendorModel updateNameAndContact(String email,List<String> list1)
{
    VendorModel v1 = vendorRepository.findById(email).get();
    v1.setCompanyName(list1.get(0));
    v1.setOfficeContact(list1.get(1));
    return vendorRepository.save(v1);
}

public TransportModel getDomesticPrice(String email)
{
    VendorModel v1 = vendorRepository.findById(email).get();
    TransportModel t1 = v1.getPrice().getDomesticPrice();
    return t1;
}

    public TransportModelInternational getInternationalPrice(String email)
    {
        VendorModel v1 = vendorRepository.findById(email).get();
        TransportModelInternational t1 = v1.getPrice().getInternationalPrice();
        return t1;
    }

    @Override
    public VendorModel patchVendorDetails(String emailId , UpdatedData updatedData) throws UserNotFoundException {

        VendorModel existingVendor=vendorRepository.findById(emailId).get();
        existingVendor.setName(updatedData.getName());
        existingVendor.setCompanyName(updatedData.getCompanyName());
        existingVendor.setOfficeContact(updatedData.getOfficeContact());
        AddressModel a1 = new AddressModel();
        a1.setAddressLine1(updatedData.getAddressLine1());
        a1.setCity(updatedData.getCity());
        a1.setState(updatedData.getState());
        a1.setCountry(updatedData.getCountry());
        a1.setZipcode(updatedData.getZipcode());
        existingVendor.setAddress(a1);
        vendorRepository.deleteById(emailId);
        System.out.println(existingVendor);
        return vendorRepository.save(existingVendor);

    }

    public List<VendorModel> getVendors() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET().header("name","value").uri(URI.create("http://localhost:8083/api/vendor/getall")).build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        String a = response.body().substring(11,response.body().length()-301);
        System.out.println(a);
        ObjectMapper mapper = new ObjectMapper();
        List<VendorModel> list = mapper.readValue(a, new TypeReference<List<VendorModel>>() {
        });
        System.out.println();
        System.out.println(list);
        System.out.println(list.size());

        for(int i =0;i< list.size();i++)
        {
            List<VendorModel> list1 = new ArrayList<>();
            VendorModel v1 = new VendorModel(list.get(i).getEmailId(),list.get(i).getName(),list.get(i).getAddress(),list.get(i).getCompanyName(),list.get(i).getOfficeContact(),list.get(i).getOfficeAddress(),list.get(i).getCities(),list.get(i).getPrice());
            list1.add(v1);
        }
        return list;
    }
public List<VendorModel> findVendorByCity(String city) throws IOException, InterruptedException {
    System.out.println("hello city name "+city);
List<VendorModel> list1 =getVendors();
    System.out.println("************************************************");
    System.out.println(city);
    System.out.println();
    System.out.println("All list of vendors");
    System.out.println(list1);
    List<VendorModel> list3 = new ArrayList<>();
    System.out.println("***********************");
    for(int i=0;i< list1.size();i++)
    {
        if(list1.get(i).getCities()==null)
        {
            System.out.println("null city ");
        }
        else
        if(list1.get(i).getCities().contains(city))
        {
            System.out.println("**********************************************");
            list3.add(list1.get(i));
            System.out.println(list3);
            System.out.println("***********************************************");
        }
    }
return list3;
}

}
