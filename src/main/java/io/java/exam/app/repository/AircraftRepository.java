package io.java.exam.app.repository;

import io.java.exam.app.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
}
