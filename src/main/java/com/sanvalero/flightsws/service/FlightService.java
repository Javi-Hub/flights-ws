package com.sanvalero.flightsws.service;

import com.sanvalero.flightsws.domain.Flight;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 15/03/2021
 */
public interface FlightService {

    Optional<Flight> findFlightById(long id);
    Set<Flight> findFlights();
    Set<Flight> findFlightsByOrigin(String origin);
    Set<Flight> findFlightsByDestination(String destination);
    Set<Flight> findFlightsByStopover(int stopover);
    Flight addFlight(Flight flight);
    Flight modifyFlight(long id, Flight flight);
    void deleteFlight(long id);
    void deleteFlightByDestination(String destination);


}
