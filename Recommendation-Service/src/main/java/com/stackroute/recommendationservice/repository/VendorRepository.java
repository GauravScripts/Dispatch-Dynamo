package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.VendorModel;
import com.stackroute.recommendationservice.model.user.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface VendorRepository extends Neo4jRepository<VendorModel,String> {
    @Query("MATCH(post:VendorModel) WHERE post.cities =~ $city RETURN post")
    public abstract List<VendorModel> getUsersByCity(String city);

    @Query("MATCH (n:VendorModel ) RETURN n")
    public abstract List<VendorModel> getalldetails();
}
