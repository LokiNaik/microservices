package com.microservices.api_gateway;

import com.microservices.api_gateway.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {
    ErrorResponse errorResponseHelper;

    public ErrorResponse errorResponseHelper() {
        errorResponseHelper = new ErrorResponse();
        errorResponseHelper.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        errorResponseHelper.setErrorCode(errorResponseHelper.getStatus().value());
        return errorResponseHelper;
    }

    @GetMapping("/users")
    public ResponseEntity<ErrorResponse> userControllerFallBack() {
        ErrorResponse errorResponse = errorResponseHelper();
        errorResponse.setMessage("User Service is currently unavailable. Please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorResponseHelper);
    }

    @GetMapping("/ratings")
    public ResponseEntity<ErrorResponse> ratingsFallBack() {
        ErrorResponse errorResponse = errorResponseHelper();
        errorResponse.setMessage("Rating Service is currently unavailable. Please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorResponse);
    }

    @GetMapping("/hotel")
    public ResponseEntity<ErrorResponse> hotelServiceFallBack() {
        ErrorResponse errorResponse = errorResponseHelper();
        errorResponse.setMessage("Hotel Service is currently unavailable. Please try again later.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(errorResponse);
    }
}
