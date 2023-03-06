package com.stackroute.vendorservice.configuration;

import com.stackroute.vendorservice.domain.*;
import com.stackroute.vendorservice.service.VendorServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Vendor {
    private final VendorServiceImpl vendorService;
    @Autowired
    public Vendor(VendorServiceImpl vendorService) {
        this.vendorService = vendorService;
    }


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void recievedMessage(VendorDTO vendorDTO) {
        System.out.println(vendorDTO);
        VendorModel vendorModel = new VendorModel();
        vendorModel.setEmailId(vendorDTO.getEmailId());
        vendorModel.setName(vendorDTO.getName());
        TransportModel t1 = new TransportModel(0,0,0);
        TransportModelInternational t2 = new TransportModelInternational(0,0,0);
        Price p1 = new Price(t1,t2);
        vendorModel.setPrice(p1);
//        vendorModel.getPrice().setDomesticPrice(t1);
//        vendorModel.getPrice().setInternationalPrice(t2);
//        Address address = vendorDTO.getAddress();
//        AddressModel addressModel = new AddressModel(address.getAddressLine1(), address.getCity(), address.getState(), address.getCountry(), address.getZipcode());
//        vendorModel.setAddress(addressModel);
//        vendorModel.setCompanyName(vendorDTO.getCompanyName());
//        vendorModel.setOfficeContact(vendorDTO.getOfficeContact());
        vendorService.saveVendor(vendorModel);
    }
}
