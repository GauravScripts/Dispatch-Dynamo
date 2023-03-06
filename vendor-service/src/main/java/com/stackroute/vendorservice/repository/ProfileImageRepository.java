package com.stackroute.vendorservice.repository;


import com.stackroute.vendorservice.domain.ProfileImage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImageRepository extends ElasticsearchRepository<ProfileImage, String> {

}

