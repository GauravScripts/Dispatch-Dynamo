package com.stackroute.feedbackservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Feedback Not submitted on that booking")
public class FeedbackNotFound extends Exception{
}
