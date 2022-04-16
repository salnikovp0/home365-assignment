package io.java.exam.app.request;

import lombok.Data;

@Data
public class AircraftBuyRequest {
    private int airlineSellerId;
    private int airlineBuyerId;
    private int aircraftId;
}
