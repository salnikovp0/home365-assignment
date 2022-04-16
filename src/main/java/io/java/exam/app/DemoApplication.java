package io.java.exam.app;

import io.java.exam.app.repository.AircraftRepository;
import io.java.exam.app.repository.AirlineRepository;
import io.java.exam.app.service.AircraftService;
import io.java.exam.app.service.AirlineService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories("io.java.exam.app.repository")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	AirlineService airlineService(AirlineRepository airlineRepository, AircraftRepository aircraftRepository){
		return new AirlineService(airlineRepository, aircraftRepository);
	}

	@Bean
	AircraftService aircraftService(AircraftRepository aircraftRepository, AirlineRepository airlineRepository){
		return new AircraftService(aircraftRepository, airlineRepository);
	}
}
