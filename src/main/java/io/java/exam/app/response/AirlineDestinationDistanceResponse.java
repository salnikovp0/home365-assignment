package io.java.exam.app.response;

import io.java.exam.app.model.AirlineDestination;
import lombok.Data;

import java.util.List;

@Data
public class AirlineDestinationDistanceResponse {
    private List<AirlineDestination> destinations;
}
