package com.wine.to.up.deployment.service.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServiceUnavailableException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundException.class, com.github.dockerjava.api.exception.NotFoundException.class})
    public final ResponseEntity<Object> handleNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public final ResponseEntity<Object> handleServiceUnavailableException() {
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleSpringStatusException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
