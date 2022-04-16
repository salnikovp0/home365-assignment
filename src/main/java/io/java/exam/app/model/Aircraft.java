package io.java.exam.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "aircrafts")
@EqualsAndHashCode(of = {"aircraft_id"})
@ToString(of = {"price"})
@NoArgsConstructor
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private int aircraftId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @Column
    private double price;

    @Column
    private long distance;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();
}
