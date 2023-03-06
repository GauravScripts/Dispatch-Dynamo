package com.example.authentication.Repositery;

import com.example.authentication.Domain.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthRepositery extends JpaRepository<Authentication,String> {

}
