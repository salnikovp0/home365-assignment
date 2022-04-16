package io.java.exam.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class HomeLocation {
    private String locationName;
    private double lat;
    private double lon;
}

