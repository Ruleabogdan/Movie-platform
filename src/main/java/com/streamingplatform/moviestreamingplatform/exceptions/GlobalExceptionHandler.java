package com.streamingplatform.moviestreamingplatform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                             WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false), new Date());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false), new Date());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false), new Date());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceFoundException.class)
    public ResponseEntity<?> handleResourceFoundException(ResourceFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false), new Date());

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
