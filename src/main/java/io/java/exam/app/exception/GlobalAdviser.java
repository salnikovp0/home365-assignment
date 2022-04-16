package io.java.exam.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalAdviser extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AircraftNotFoundException.class)
    public ResponseEntity<Object> handleAircraftNotFoundException() {

        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AirlineNotFoundException.class)
    public ResponseEntity<Object> handleAirlineNotFoundException() {

        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
}
