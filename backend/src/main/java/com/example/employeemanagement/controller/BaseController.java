package com.example.employeemanagement.controller;

import com.example.employeemanagement.exception.ErrorResponse;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/** Base controller to provide common functionality for all REST controllers. */
@CrossOrigin(origins = "http://localhost:3000")
public abstract class BaseController {

  /**
   * Global exception handler for ResourceNotFoundException.
   *
   * @param ex The exception
   * @param request The web request
   * @return Formatted error response
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        LocalDateTime.now(),
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        ex.getMessage(),
        request.getDescription(false)
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Global exception handler for general exceptions.
   *
   * @param ex The exception
   * @param request The web request
   * @return Formatted error response
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(
      Exception ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        LocalDateTime.now(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        ex.getMessage(),
        request.getDescription(false)
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  /**
   * Helper method to build a success response with a message.
   * 
   * @param message Success message
   * @return ResponseEntity with message
   */
  protected ResponseEntity<String> successResponse(String message) {
    return ResponseEntity.ok(message);
  }
}
