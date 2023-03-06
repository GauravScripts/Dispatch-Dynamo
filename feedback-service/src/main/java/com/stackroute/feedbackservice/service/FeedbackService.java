package com.stackroute.feedbackservice.service;

import com.stackroute.feedbackservice.exceptions.FeedbackAlreadySubmitted;
import com.stackroute.feedbackservice.exceptions.FeedbackNotFound;
import com.stackroute.feedbackservice.model.Feedback;

import java.util.List;

public interface FeedbackService {
    boolean addFeedbackToRepo(Feedback feedback) throws FeedbackAlreadySubmitted;
    List<Feedback> getAllFeedbackByEmailId(String userEmailId);
    Feedback getFeedbackOfService(String serviceBookingId) throws FeedbackNotFound;
    List<Feedback> getAllFeedbacks();
}
