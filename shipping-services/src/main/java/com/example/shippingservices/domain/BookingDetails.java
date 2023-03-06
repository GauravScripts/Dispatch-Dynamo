package com.example.shippingservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
    private int weight;
    private int length;
    private int breadth;
    private int height;
    private AddressModel from;
    private AddressModel to;
    private String modeOfTransport;
    private String serviceType;
    private String shipmentType;
}
