package com.stackroute.recommendationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Vendor not found exception")
public class VendorNotFoundException extends Exception {
}

