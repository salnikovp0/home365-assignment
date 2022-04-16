package io.java.exam.app.request;

import lombok.Data;

@Data
public class AircraftCreateRequest {
    private long distance;
    private int airlineId;
    private long price;
}
