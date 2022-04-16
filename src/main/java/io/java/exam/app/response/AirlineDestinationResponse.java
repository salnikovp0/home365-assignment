package io.java.exam.app.response;

import lombok.Data;

import java.util.Set;

@Data
public class AirlineDestinationResponse {
    private Set<String> destinations;
}
