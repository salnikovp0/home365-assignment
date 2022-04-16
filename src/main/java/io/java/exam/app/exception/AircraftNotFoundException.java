package io.java.exam.app.exception;

public class AircraftNotFoundException extends RuntimeException {
    public AircraftNotFoundException() {
        super("No data found");
    }
}
