package io.java.exam.app.service;

import io.java.exam.app.exception.AircraftNotFoundException;
import io.java.exam.app.exception.AirlineNotFoundException;
import io.java.exam.app.model.Aircraft;
import io.java.exam.app.model.Airline;
import io.java.exam.app.model.AirlineDestination;
import io.java.exam.app.repository.AircraftRepository;
import io.java.exam.app.repository.AirlineRepository;
import io.java.exam.app.request.AircraftBuyRequest;
import io.java.exam.app.response.AirlineDestinationDistanceResponse;
import io.java.exam.app.response.AirlineDestinationResponse;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Transactional
public class AirlineService {

    private final AirlineRepository airlineRepository;
    private final AircraftRepository aircraftRepository;

    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public AirlineDestinationDistanceResponse getAllAirlinesDistances(Integer id) {
        Airline currentAirline = this.airlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);

        List<AirlineDestination> airlineDestinationList = this.generateAllAirlinesDistances(currentAirline);

        AirlineDestinationDistanceResponse airlineDestinationDistanceResponse = new AirlineDestinationDistanceResponse();
        airlineDestinationDistanceResponse.setDestinations(airlineDestinationList);

        return airlineDestinationDistanceResponse;
    }

    public AirlineDestinationResponse getAllAirlinesDestinations(Integer id) {
        Airline currentAirline = this.airlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);

        List<AirlineDestination> airlineDestinationList = this.generateAllAirlinesDistances(currentAirline);
        AirlineDestinationResponse response = new AirlineDestinationResponse();
        Set<String> validDestinations = new HashSet<>();

        for (Aircraft aircraft: currentAirline.getAircrafts()) {
            List<String> filteredDestinations = airlineDestinationList
                    .stream()
                    .filter(airlineDestination -> airlineDestination.getDistance() < aircraft.getDistance())
                    .map(AirlineDestination::getLocationName)
                    .toList();

            validDestinations.addAll(filteredDestinations);
        }

        response.setDestinations(validDestinations);

        return response;
    }

    private List<AirlineDestination> generateAllAirlinesDistances(Airline currentAirline) {
        List<Airline> allAirlines = this.getAllAirlines();
        List<AirlineDestination> airlineDestinationList = new ArrayList<>();

        for (Airline airline : allAirlines) {
            if (airline.getAirlineId() == currentAirline.getAirlineId()) continue;

            AirlineDestination airlineDestination = new AirlineDestination();
            double distance = Haversine.haversine(
                    currentAirline.getHomeLocation().getLat(),
                    currentAirline.getHomeLocation().getLon(),
                    airline.getHomeLocation().getLat(),
                    airline.getHomeLocation().getLon());
            String name = airline.getHomeLocation().getLocationName();

            airlineDestination.setDistance(distance);
            airlineDestination.setLocationName(name);

            airlineDestinationList.add(airlineDestination);
        }

        return airlineDestinationList;
    }

    public Airline buyAircraft(AircraftBuyRequest req) {
        Airline buyerAirline = this.airlineRepository.findById(req.getAirlineBuyerId()).orElseThrow(AirlineNotFoundException::new);
        Airline sellerAirline = this.airlineRepository.findById(req.getAirlineSellerId()).orElseThrow(AirlineNotFoundException::new);
        Aircraft aircraft = this.aircraftRepository.findById(req.getAircraftId()).orElseThrow(AircraftNotFoundException::new);

        // Validate that the aircraft belongs to the seller airline
        if (sellerAirline.getAircrafts().stream().noneMatch(a -> a.getAircraftId() == aircraft.getAircraftId())) {
            throw new AircraftNotFoundException();
        }

        sellerAirline.setBudget(sellerAirline.getBudget() + aircraft.getPrice());
        sellerAirline.removeAircraft(aircraft);

        buyerAirline.setBudget(buyerAirline.getBudget() - aircraft.getPrice());
        buyerAirline.addAircraft(aircraft);

        aircraft.setAirline(buyerAirline);

        this.airlineRepository.save(sellerAirline);
        this.airlineRepository.save(buyerAirline);
        this.aircraftRepository.save(aircraft);

        return buyerAirline;
    }
}
