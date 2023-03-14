package com.stackroute.vendorservice.controller;

import com.stackroute.vendorservice.domain.AddressModel;
import com.stackroute.vendorservice.domain.UpdatePrice;
import com.stackroute.vendorservice.domain.UpdatedData;
import com.stackroute.vendorservice.domain.VendorModel;
import com.stackroute.vendorservice.exception.UserNotFoundException;
import com.stackroute.vendorservice.service.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    // http://localhost:8083/api/vendor

    private VendorServiceImpl vendorService;
    @Autowired
    public VendorController(VendorServiceImpl vendorService) {
        this.vendorService = vendorService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> saveVendor(@RequestBody VendorModel vendorModel){
        return ResponseEntity.ok(vendorService.saveVendor(vendorModel));
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAllVendors(){
        return ResponseEntity.ok(vendorService.getAllVendors());
    }
    @GetMapping("/getvendor")
    public ResponseEntity<?> getVendorById(HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        return ResponseEntity.ok(vendorService.getVendorById(email));
    }
    @DeleteMapping("/delete/{emailId}")
    public ResponseEntity<?> deleteVendor(@PathVariable String emailId) {
        return ResponseEntity.ok(vendorService.deleteVendor(emailId));
    }
    @PatchMapping("/patch/name")
    public ResponseEntity<?> patchVendorName(@RequestBody VendorModel vendorModel) throws UserNotFoundException {
        return ResponseEntity.ok(vendorService.patchVendorName(vendorModel));
    }
    @GetMapping("/getallcities")
    public ResponseEntity<List<String>> getAllCitiesOfVendor(HttpServletRequest httpServletRequest) {
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        System.out.println("hello");
        List<String> cities = vendorService.getAllCitiesOfVendor(email);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PutMapping("/city")
    public ResponseEntity<VendorModel> updateCity( @RequestBody String city,HttpServletRequest httpServletRequest) {
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        System.out.println(city);
        return new ResponseEntity<>(vendorService.updateCity(city, email), HttpStatus.OK);
    }

    @DeleteMapping("/deletecity/{city}")
    public ResponseEntity<VendorModel> deleteCity( @PathVariable String city,HttpServletRequest httpServletRequest) {
        System.out.println("hello");
        System.out.println(city);
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        VendorModel updatedVendor = vendorService.deleteCity(city, email);
        return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<VendorModel>> getAllVendors(@RequestBody List<String> list) {
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        List<VendorModel> vendors = vendorService.getAllVendors(list.get(0), list.get(1));
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @PostMapping("/addaddress")
    public ResponseEntity<?> updateAddress(@RequestBody AddressModel addressModel,HttpServletRequest httpServletRequest)
    {
        System.out.println(addressModel);
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        return new ResponseEntity<>(vendorService.updateAddress(email,addressModel),HttpStatus.OK);
    }

    @PostMapping("/addprice")
    public ResponseEntity<?> updatePrice(@RequestBody UpdatePrice price, HttpServletRequest httpServletRequest)
    {
        System.out.println(price);
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        return new ResponseEntity<>(vendorService.updatePrice(email,price),HttpStatus.OK);
    }

    @PostMapping("/addnameandcontact")
    public ResponseEntity<?> updatePrice(@RequestBody List<String> list1,HttpServletRequest httpServletRequest)
    {
        System.out.println(list1);
        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        return new ResponseEntity<>(vendorService.updateNameAndContact(email,list1),HttpStatus.OK);
    }


    @GetMapping("/getDomesticPrice")
    public ResponseEntity<?> getDomesticPrices(HttpServletRequest httpServletRequest)
    {
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        return new ResponseEntity<>(vendorService.getDomesticPrice(email),HttpStatus.OK);
    }

    @GetMapping("/getInternationalPrice")
    public ResponseEntity<?> getInternationalPrices(HttpServletRequest httpServletRequest)
    {
        String email = (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(email);
        return new ResponseEntity<>(vendorService.getInternationalPrice(email),HttpStatus.OK);
    }

    @PostMapping("/patchVendorDetails")
    public ResponseEntity<?> patchVendorDetails(@RequestBody UpdatedData updatedData, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String emailId= (String) httpServletRequest.getAttribute("current_user_emailid");
        System.out.println(emailId);
        System.out.println(updatedData);
        return ResponseEntity.ok(vendorService.patchVendorDetails(emailId,updatedData));
    }


    @GetMapping("/filteredVendorByCity/{city}")
    public ResponseEntity<?> filteredVendorByCity(@PathVariable String city) throws IOException, InterruptedException {
        System.out.println(city);
      return new ResponseEntity<>(vendorService.findVendorByCity(city), HttpStatus.OK);

    }
}
