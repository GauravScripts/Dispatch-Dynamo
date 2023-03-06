package com.example.shippingservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transition {
    private String locationCity;
    private String description;
    private String dateAndTime;

}
