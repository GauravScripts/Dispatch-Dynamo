package com.example.userprofile.repository;

import com.example.userprofile.domain.ModelImpl.UserModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends MongoRepository<UserModel,String> {
//    @Query("{ emailId:?o }")
//    public abstract List<OrdersList> findByUserEmail(String email);
//    public abstract OrdersList addOrders(OrdersList ordersList);
}
