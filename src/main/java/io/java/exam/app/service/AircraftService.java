package io.java.exam.app.service;

import io.java.exam.app.exception.AircraftNotFoundException;
import io.java.exam.app.model.Aircraft;
import io.java.exam.app.model.Airline;
import io.java.exam.app.repository.AircraftRepository;
import io.java.exam.app.repository.AirlineRepository;
import io.java.exam.app.request.AircraftCreateRequest;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@Transactional
public class AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;

    public Aircraft createAircraft(AircraftCreateRequest aircraft) {
        Aircraft newAircraft = new Aircraft();
        Airline airline = this.airlineRepository.getById(aircraft.getAirlineId());
        newAircraft.setAirline(airline);
        newAircraft.setDistance(aircraft.getDistance());
        newAircraft.setPrice(aircraft.getPrice());

        airline.addAircraft(newAircraft);
        this.airlineRepository.save(airline);

        return this.aircraftRepository.save(newAircraft);
    }

    public Aircraft sellAircraft(int aircraftId) {
        Aircraft aircraft = this.aircraftRepository.findById(aircraftId).orElseThrow(AircraftNotFoundException::new);

        Airline airline = aircraft.getAirline();

        double currentBudget = airline.getBudget();
        long monthInUse = ChronoUnit.MONTHS.between(aircraft.getCreatedAt(), LocalDate.now());
        double currentPrice = aircraft.getPrice() * ( 1 - ( monthInUse * 0.02 ) );

        airline.setBudget(currentBudget + currentPrice);
        airline.removeAircraft(aircraft);
        this.airlineRepository.save(airline);
        this.aircraftRepository.delete(aircraft);

        return aircraft;
    }
}
