package io.java.exam.app.controller;

import io.java.exam.app.model.Airline;
import io.java.exam.app.request.AircraftBuyRequest;
import io.java.exam.app.response.AirlineDestinationDistanceResponse;
import io.java.exam.app.response.AirlineDestinationResponse;
import io.java.exam.app.service.AirlineService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping("/airline")
    @ResponseBody
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline){
        return ResponseEntity.status(HttpStatus.CREATED).body(airlineService.createAirline(airline));
    }

    @GetMapping("/airline")
    @ResponseBody
    public ResponseEntity<List<Airline>> getAllAirlines(){
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @GetMapping("/airline/{id}/distances")
    @ResponseBody
    public ResponseEntity<AirlineDestinationDistanceResponse> getAllAirlinesDistances(@PathVariable(value="id") Integer id){
        return ResponseEntity.ok(airlineService.getAllAirlinesDistances(id));
    }

    @GetMapping("/airline/{id}/destinations")
    @ResponseBody
    public ResponseEntity<AirlineDestinationResponse> getAllAirlinesDestinations(@PathVariable(value="id") Integer id){
        return ResponseEntity.ok(airlineService.getAllAirlinesDestinations(id));
    }

    @PostMapping("/airline/buy")
    @ResponseBody
    public ResponseEntity<Airline> buyAircraft(@RequestBody AircraftBuyRequest req){
        return ResponseEntity.ok(airlineService.buyAircraft(req));
    }
}
