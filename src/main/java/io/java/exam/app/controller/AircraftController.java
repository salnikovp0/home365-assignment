package io.java.exam.app.controller;

import io.java.exam.app.model.Aircraft;
import io.java.exam.app.request.AircraftCreateRequest;
import io.java.exam.app.service.AircraftService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Data
@RestController
public class AircraftController {

    private final AircraftService aircraftService;

    @PostMapping("/aircraft")
    @ResponseBody
    public ResponseEntity<Aircraft> createAircraft(@RequestBody AircraftCreateRequest aircraft){
        return ResponseEntity.status(HttpStatus.CREATED).body(aircraftService.createAircraft(aircraft));
    }

    @PostMapping("/aircraft/{id}/sell")
    @ResponseBody
    public ResponseEntity<Aircraft> sellAircraft(@PathVariable(value="id") Integer aircraftId){
        return ResponseEntity.status(HttpStatus.CREATED).body(aircraftService.sellAircraft(aircraftId));
    }
}
