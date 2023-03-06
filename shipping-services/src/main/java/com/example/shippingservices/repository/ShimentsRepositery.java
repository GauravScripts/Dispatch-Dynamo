package com.example.shippingservices.repository;

import com.example.shippingservices.domain.Shipments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShimentsRepositery extends MongoRepository<Shipments,String> {

    public List<Shipments> findByUserId(String userId);
    public List<Shipments> findByVendorId(String vendorId);
}
