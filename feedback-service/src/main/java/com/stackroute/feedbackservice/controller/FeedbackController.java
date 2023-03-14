package com.stackroute.feedbackservice.controller;

import com.stackroute.feedbackservice.exceptions.FeedbackAlreadySubmitted;
import com.stackroute.feedbackservice.exceptions.FeedbackNotFound;
import com.stackroute.feedbackservice.model.Feedback;
import com.stackroute.feedbackservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackServiceI;

    @PostMapping("/post")
    public ResponseEntity<?> addNewFeedback(@RequestBody Feedback feedback) throws FeedbackAlreadySubmitted {
        return new ResponseEntity<>(feedbackServiceI.addFeedbackToRepo(feedback), HttpStatus.CREATED);
    }

    @GetMapping("/email/{userEmailId}")
    public ResponseEntity<?> findAllFeedbacksByEmailId(@PathVariable String userEmailId){
        return new ResponseEntity<>(feedbackServiceI.getAllFeedbackByEmailId(userEmailId),HttpStatus.OK);
    }

    @GetMapping("/booking/{serviceBookingId}")
    public ResponseEntity<?> findFeedBackOfService(@PathVariable String serviceBookingId) throws FeedbackNotFound {
        return new ResponseEntity<>(feedbackServiceI.getFeedbackOfService(serviceBookingId),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllFeedBacks(){
        return new ResponseEntity<>(feedbackServiceI.getAllFeedbacks(),HttpStatus.OK);
    }
}
