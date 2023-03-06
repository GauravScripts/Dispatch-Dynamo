package com.stackroute.vendorservice.repository;
import com.stackroute.vendorservice.domain.VendorModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends ElasticsearchRepository<VendorModel, String> {
  List<VendorModel> findByNameContainingIgnoreCase(String name);
}
