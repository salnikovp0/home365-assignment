package io.java.exam.app.repository;

import io.java.exam.app.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {

}
