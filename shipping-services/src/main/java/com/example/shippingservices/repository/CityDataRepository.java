package com.example.shippingservices.repository;

import com.example.shippingservices.domain.CityData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDataRepository extends MongoRepository<CityData,String> {
}
