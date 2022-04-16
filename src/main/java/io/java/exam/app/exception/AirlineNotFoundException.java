package io.java.exam.app.exception;

public class AirlineNotFoundException extends RuntimeException {
    public AirlineNotFoundException() {
        super("No data found");
    }
}
