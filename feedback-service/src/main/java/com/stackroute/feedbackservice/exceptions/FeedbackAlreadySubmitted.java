package com.stackroute.feedbackservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Feedback Already submitted")
public class FeedbackAlreadySubmitted extends Exception{
}
