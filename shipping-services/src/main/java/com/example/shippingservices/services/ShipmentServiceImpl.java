package com.example.shippingservices.services;

import com.example.shippingservices.RabbitMq.DataProducer;
import com.example.shippingservices.RabbitMq.MailServiceData;
import com.example.shippingservices.domain.ShipmentData;
import com.example.shippingservices.domain.Shipments;
import com.example.shippingservices.domain.Transition;
import com.example.shippingservices.domain.UpdateShipment;
import com.example.shippingservices.repository.ShimentsRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class ShipmentServiceImpl implements ShipmentService{

@Autowired
private ShimentsRepositery shimentsRepositery;
@Autowired
private DataProducer dataProducer;
    int x=2;
    LocalDate currentDate = LocalDate.now();
    Month currentMonth = Month.of(x);

    @Override
    public Shipments addShipment(ShipmentData shipmentData) {
        Shipments s1 = new Shipments();
        s1.setTrackingId(shipmentData.getTrackId());
        s1.setLength(shipmentData.getLength());
        s1.setBreadth(shipmentData.getBreadth());
        s1.setHeight(shipmentData.getHeight());
        s1.setPaymentStatus("Payment Successfully");
        String s2 = "PID"+getrandom()+getrandom()+getrandom();
        s1.setPaymentId(s2);
        s1.setPaymentType(shipmentData.getPaymentType());
        s1.setServicetype(shipmentData.getServicetype());
        s1.setUserId((shipmentData.getUserId()));
        s1.setVendorId((shipmentData.getVendorId()));
        s1.setModeOfTransport(shipmentData.getModeOfTransport());
        s1.setPrice(shipmentData.getPrice());
        s1.setFromAddress(shipmentData.getFromAddress());
        s1.setToAddress(shipmentData.getToAddress());
        s1.setOrderMonth(String.valueOf(currentMonth));
        s1.setOrderDateAndTime(String.valueOf(currentDate));
        s1.setStatus("Booked");
        s1.setTransition(Collections.emptyList());

        String a = "Congratulation : "+s1.getUserId()+" Your Order is Succesfully Placed with the Vendor havind Id : "+s1.getVendorId()+"/nYour Shipment Tracking id is : "+s1.getTrackingId();
        MailServiceData m1 = new MailServiceData(s1.getUserId()," Congratulations Shipment Booked ",a);
        dataProducer.sendMailDataToQueue(m1);
        return shimentsRepositery.save(s1);
    }

    private int getrandom() {
        Random ab = new Random();
        int a = ThreadLocalRandom.current().nextInt(100,999);
        return a ;
    }

    @Override
    public Optional<Shipments> getShipment(String trackId) {
        return shimentsRepositery.findById(trackId);
    }

    @Override
    public Shipments updateShipemnt(UpdateShipment updateShipment) {
        Shipments s1 = shimentsRepositery.findById(updateShipment.getTrackId()).get();
        Transition t1 = new Transition(updateShipment.getLocationCity(),updateShipment.getDescription(),String.valueOf(new java.util.Date()));
        s1.getTransition().add(t1);
        return shimentsRepositery.save(s1) ;
    }

    @Override
    public boolean updateStatus(String trackId , String status)
    {
        Shipments s1 = shimentsRepositery.findById(trackId).get();
        s1.setStatus(status);
        shimentsRepositery.save(s1);
        return true;

    }



    @Override
    public List<Shipments> findbyuser(String userId) {
        return shimentsRepositery.findByUserId(userId);
    }

    @Override
    public List<Shipments> findbyvendor(String vendorId) {
        return shimentsRepositery.findByVendorId(vendorId);
    }

    @Override
    public int countDailyBooking(String vendorId) {
        int count=0;
        List<Shipments> a=shimentsRepositery.findByVendorId(vendorId);
        for (Shipments obj: a) {
            if(obj.getOrderDateAndTime().equalsIgnoreCase(String.valueOf(currentDate))){
                count = count+1;
                System.out.println(count);
            }
        }
        return count;
    }

    @Override
    public int countMonthlyBooking(String vendorId) {
        int count=0;
        List<Shipments> b=shimentsRepositery.findByVendorId(vendorId);
        for(Shipments obj: b){
            System.out.println(obj);
            System.out.println(obj.getOrderMonth());
            System.out.println(String.valueOf(currentMonth));
            if(obj.getOrderMonth().equalsIgnoreCase(String.valueOf(currentMonth)));
            count=count+1;
            System.out.println(count);
        }
        return count;
    }


}
