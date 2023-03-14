package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/neo4j")
public class RecommendationController {
  @Autowired
  private UserService userService;

    @GetMapping ("/Users-By-City/{city}")
    public ResponseEntity<?> getAllProfilesByCity(@PathVariable String city)
    {
        return new ResponseEntity<>(userService.getUsersByCity(city),HttpStatus.OK);
    }
  @GetMapping ("/getalluserdetails")
  public ResponseEntity<?> getalldetails()
  {
    return new ResponseEntity<>(userService.getalldetails(),HttpStatus.OK);
  }

}
