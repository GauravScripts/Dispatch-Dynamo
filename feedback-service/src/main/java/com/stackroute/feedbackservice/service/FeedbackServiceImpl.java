package com.stackroute.feedbackservice.service;

import com.stackroute.feedbackservice.exceptions.FeedbackAlreadySubmitted;
import com.stackroute.feedbackservice.exceptions.FeedbackNotFound;
import com.stackroute.feedbackservice.model.Feedback;
import com.stackroute.feedbackservice.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
    @Override
    public boolean addFeedbackToRepo(Feedback feedback) throws FeedbackAlreadySubmitted {
        if(feedbackRepository.existsById(feedback.getServiceBookingId())){
            System.out.println("Feedback already submitted");
            throw new FeedbackAlreadySubmitted();
        }
        else{
            System.out.println(feedback);
            feedbackRepository.insert(feedback);
            return true;
        }
    }

    @Override
    public List<Feedback> getAllFeedbackByEmailId(String userEmailId) {
        List<Feedback> feedbacks = feedbackRepository.findFeedbackByUserEmailId(userEmailId);
        return feedbacks;
    }

    @Override
    public Feedback getFeedbackOfService(String serviceBookingId) throws FeedbackNotFound {
        if(feedbackRepository.existsById(serviceBookingId)){
            return feedbackRepository.findById(serviceBookingId).get();
        }
        else{
            System.out.println("Feedback not found on that booking");
            throw new FeedbackNotFound();
        }
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}
