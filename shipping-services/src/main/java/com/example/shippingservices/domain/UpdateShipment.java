package com.example.shippingservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShipment {
    private String trackId;
    private String locationCity;
    private String description;

}
