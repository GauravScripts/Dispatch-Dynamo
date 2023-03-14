package com.stackroute.vendorservice.controller;

import com.stackroute.vendorservice.domain.VendorModel;
import com.stackroute.vendorservice.exception.UserNotFoundException;
import com.stackroute.vendorservice.repository.VendorRepository;
import com.stackroute.vendorservice.service.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SearchController {

    private VendorRepository vendorRepository;
    private VendorServiceImpl vendorService;
    @Autowired
    public SearchController(VendorRepository vendorRepository, VendorServiceImpl vendorService) {
        this.vendorRepository = vendorRepository;
        this.vendorService = vendorService;
    }

    //search handler
    @GetMapping("/search/{name}")
    public ResponseEntity<?> search(@PathVariable("name") String name) throws UserNotFoundException {

        List<VendorModel> vendorModelList = vendorRepository.findByNameContainingIgnoreCase(name);
        if(vendorModelList.isEmpty()){
            throw new UserNotFoundException();
        }
        return ResponseEntity.ok(vendorModelList);
    }
}
