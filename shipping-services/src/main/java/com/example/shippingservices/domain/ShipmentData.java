package com.example.shippingservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentData {

    private String trackId;
    private int length;
    private int breadth;
    private int height;
    private String userId;
    private String vendorId;
    private String servicetype;
    private String paymentType;
    private String modeOfTransport;
    private String price;
    private AddressModel fromAddress;
    private AddressModel toAddress;
}
