package com.sanvalero.flightsws.repository;

import com.sanvalero.flightsws.domain.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Creado por @author: Javier
 * el 15/03/2021
 */
@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    Set<Flight> findAll();
    Set<Flight> findByOrigin(String origin);
    Set<Flight> findByDestination(String destination);
    Set<Flight> findByStopover(int stopover);

}
