package com.example.userprofile.domain.ModelImpl;


import com.example.userprofile.domain.LocationModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddModel {
  private LocationModel locationModel;
//  private TransportModel transportModel;

}
