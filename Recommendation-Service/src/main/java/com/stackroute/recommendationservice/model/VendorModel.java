package com.stackroute.recommendationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Node
public class VendorModel {
    @Id
    private String emailId;
    private String name;
    private AddressModel address;
    private String companyName;
    private String officeContact;
    private List<String> officeAddress;
    private List<String> cities;
}
