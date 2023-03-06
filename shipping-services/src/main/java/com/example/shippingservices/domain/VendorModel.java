package com.example.shippingservices.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendorModel {
    @Id
    private String emailId;
    private String name;
    private AddressModel address;
    private String companyName;
    private String officeContact;
    private List<String> officeAddress;
    private List<String> cities;
   private Price price;


}
