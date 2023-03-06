package com.example.userprofile.domain.ModelImpl;


import com.example.userprofile.domain.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class UserModel {
    @Id
    private String emailId;
    private String name;
    private AddressModel address;
    private String mobileNo;

}
