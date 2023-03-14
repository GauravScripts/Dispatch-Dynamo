package com.stackroute.vendorservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "profileimage")
public class ProfileImage {
    @Id
    private String emailId;
    private String file;

}

