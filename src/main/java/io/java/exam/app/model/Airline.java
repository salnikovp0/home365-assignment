package io.java.exam.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "airlines")
@EqualsAndHashCode(of = {"airline_id"})
@ToString(of = {"name", "budget"})
@NoArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    @JsonIgnore
    private int airlineId;

    @Column
    private String name;

    @Column
    private double budget;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "locationName", column = @Column(name = "location_name")),
            @AttributeOverride(name = "lat", column = @Column(name = "lat")),
            @AttributeOverride(name = "lon", column = @Column(name = "lon"))
    })
    private HomeLocation homeLocation;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_airline_aircrafts",
            joinColumns = {@JoinColumn(name = "airline_id")},
            inverseJoinColumns = {@JoinColumn(name = "aircraft_id")}
    )
    private Set<Aircraft> aircrafts;

    @JsonIgnore
    @JsonProperty(value = "homeLocation")
    public HomeLocation getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(HomeLocation homeLocation) {
        this.homeLocation = homeLocation;
    }

    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
        aircraft.setAirline(this);
    }

    public void removeAircraft(Aircraft aircraft) {
        aircrafts.remove(aircraft);
        aircraft.setAirline(null);
    }
}
