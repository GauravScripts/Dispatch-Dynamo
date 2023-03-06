package com.example.shippingservices.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportModelInternational {
    private int byRoad;
    private int byAir;
    private int byRailway;

}
