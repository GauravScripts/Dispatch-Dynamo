package com.example.shippingservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Shipments {
    @Id
    private String trackingId;
    private int length;
    private int breadth;
    private int height;
    private String servicetype;
    private String paymentType;
    private String paymentStatus;
    private String paymentId;
    private String userId;
    private String vendorId;
    private String modeOfTransport;
    private String price;
    private AddressModel fromAddress;
    private AddressModel toAddress;
    private String orderDateAndTime;
    private String orderMonth;
    private String status;
    private List<Transition> transition;
}
