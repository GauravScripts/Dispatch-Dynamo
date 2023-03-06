package com.stackroute.vendorservice.repository;

import com.stackroute.vendorservice.domain.AddressModel;
import com.stackroute.vendorservice.domain.Price;
import com.stackroute.vendorservice.domain.TransportModel;
import com.stackroute.vendorservice.domain.TransportModelInternational;
import com.stackroute.vendorservice.domain.VendorModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;

import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@DataElasticsearchTest
@ActiveProfiles("test")
public class VendorRepositoryTest {

    @Autowired
    private VendorRepository vendorRepository;

    private AddressModel addressModel;

    private TransportModel transportModel;

    private TransportModelInternational transportModelInternational;

    private Price price;

    private VendorModel vendorModel;

    @BeforeEach
    public void setUp() throws Exception {

        transportModel = new TransportModel();
        transportModel.setByAir(1000);
        transportModel.setByRailway(500);
        transportModel.setByRoad(200);

        transportModelInternational = new TransportModelInternational();
        transportModelInternational.setByAir(2000);
        transportModelInternational.setByRailway(1000);
        transportModelInternational.setByRoad(500);

        price = new Price();
        price.setDomesticPrice(transportModel);
        price.setInternationalPrice(transportModelInternational);

        addressModel = new AddressModel();
        addressModel.setAddressLine1("Some address line 1");
        addressModel.setCity("Some city");
        addressModel.setState("Some state");
        addressModel.setCountry("Some country");
        addressModel.setZipcode("12345");

        vendorModel = new VendorModel();
        vendorModel.setEmailId("test@test.com");
        vendorModel.setName("Test Vendor");
        vendorModel.setAddress(addressModel);
        vendorModel.setCompanyName("Test Company");
        vendorModel.setOfficeContact("9876543210");
        vendorModel.setOfficeAddress(Arrays.asList("Address line 1", "Address line 2"));
        vendorModel.setCities(Arrays.asList("City 1", "City 2"));
        vendorModel.setPrice(price);
    }

    @Test
    public void givenName_whenFindByNameContainingIgnoreCase_thenListVendors() {
        vendorRepository.save(vendorModel);
        List<VendorModel> vendors = vendorRepository.findByNameContainingIgnoreCase("test");
        Assertions.assertEquals(1, vendors.size());
    }
}
