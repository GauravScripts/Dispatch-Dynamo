package com.stackroute.vendorservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private TransportModel domesticPrice;
    private TransportModelInternational internationalPrice;

}
