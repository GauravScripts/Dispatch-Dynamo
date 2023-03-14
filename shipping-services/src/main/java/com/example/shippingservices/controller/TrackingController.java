package com.example.shippingservices.controller;


import com.example.shippingservices.domain.*;
import com.example.shippingservices.services.ShipmentService;
import com.example.shippingservices.services.TrackingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping
@RestController
@CrossOrigin(origins = "*")
public class TrackingController {


    private final TrackingServices trackingServices;
    @Autowired
    private ShipmentService shipmentService;


    @Autowired
    public TrackingController(TrackingServices trackingServices, ShipmentService shipmentService) {
        this.trackingServices = trackingServices;

    }

    @PostMapping("/add")
    public ResponseEntity<?> addDetails(@RequestBody List<CityData> city) {
        return new ResponseEntity(trackingServices.addData(city), HttpStatus.OK);
    }


    @PostMapping("getvendors")
    public ResponseEntity<?> getvendors(@RequestBody List<String> list) throws IOException, InterruptedException {
        System.out.println(list);
        String email;
        String city1 = list.get(0);
        String city2 = list.get(1);
        return new ResponseEntity<>(trackingServices.filteredCities(city1, city2), HttpStatus.OK);
    }

    @PostMapping("getpricelist")
    public ResponseEntity<?> getprice(@RequestBody BookingDetails bookingDetails) throws IOException, InterruptedException {

        System.out.println("*********************************" + bookingDetails);
        String city1 = bookingDetails.getFrom().getCity();
        String city2 = bookingDetails.getTo().getCity();
        String mode = bookingDetails.getModeOfTransport();
        System.out.println(city1 + "   " + city2 + "      " + mode);
        return new ResponseEntity<>(trackingServices.getPrice(bookingDetails), HttpStatus.OK);
    }

    @PostMapping("getdistance")
    public ResponseEntity<?> getdistance(@RequestBody List<String> list) throws IOException, InterruptedException {
        String city1 = list.get(0);
        String city2 = list.get(1);
        return new ResponseEntity<>(trackingServices.calculateDistance(city1, city2), HttpStatus.OK);
    }


    @PostMapping("/addShipment")
    public ResponseEntity<?> addShipment(@RequestBody ShipmentData shipmentData) {
        System.out.println(shipmentData);
//        MailServiceData m3 =new MailServiceData(shipmentData.getVendorId(),"add Shipment","Your Shipment is Successfully Added ....");
//        DataProducer dataProducer=new DataProducer();
//        dataProducer.sendMailDataToQueue(m3);
        return new ResponseEntity<>(shipmentService.addShipment(shipmentData), HttpStatus.OK);
    }


    @GetMapping("/getshipment/{traciId}")
    public ResponseEntity<?> getShipment(@PathVariable String traciId) {
        System.out.println(traciId);
        return new ResponseEntity<>(shipmentService.getShipment(traciId), HttpStatus.OK);
    }

    @PostMapping("/updateTransition")
    public ResponseEntity<?> addShipment(@RequestBody UpdateShipment updateShipment) {
        System.out.println(updateShipment);
        return new ResponseEntity<>(shipmentService.updateShipemnt(updateShipment), HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{trackId}")
    public ResponseEntity<?> updateStatus(@RequestBody String orderstatus ,@PathVariable String trackId) {
        System.out.println(orderstatus + "   " + trackId);
        return new ResponseEntity<>(shipmentService.updateStatus(trackId, orderstatus), HttpStatus.OK);
    }

    @GetMapping("/getAllShipmentOfUser/{userid}")
    public ResponseEntity<?> getShipmentOfUser(@PathVariable String userid) {
        System.out.println(userid);
        return new ResponseEntity<>(shipmentService.findbyuser(userid), HttpStatus.OK);
    }

    @GetMapping("/getAllShipmentOfVendor/{vendorid}")
    public ResponseEntity<?> getShipmentOfVendor(@PathVariable String vendorid) {
        System.out.println(vendorid);
        return new ResponseEntity<>(shipmentService.findbyvendor(vendorid), HttpStatus.OK);
    }

    @GetMapping("/getcitydetails/{traciId}")
    public ResponseEntity<?> Citydetails(@PathVariable String traciId) {
        System.out.println(traciId);

        return new ResponseEntity<>(trackingServices.returnCityData(traciId), HttpStatus.OK);
    }

    @GetMapping("/countDailyShipmentOfVendor/{vendorId}")
    public ResponseEntity<?> countDailyShipmentOfVendor(@PathVariable String vendorId) {
        System.out.println(vendorId);
        ResponseEntity<Integer> a = new ResponseEntity<>(shipmentService.countDailyBooking(vendorId), HttpStatus.OK);
        return a;
    }

    @GetMapping("/countMonthlyShipmentOfVendor/{vendorId}")
    public ResponseEntity<?> countMonthlyShipmentOfVendor(@PathVariable String vendorId) {
        System.out.println(vendorId);
        ResponseEntity<Integer> a = new ResponseEntity<>(shipmentService.countMonthlyBooking(vendorId), HttpStatus.OK);
        return a;
    }







}
