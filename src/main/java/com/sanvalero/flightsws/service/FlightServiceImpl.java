package com.sanvalero.flightsws.service;

import com.sanvalero.flightsws.domain.Flight;
import com.sanvalero.flightsws.exception.FlightNotFoundException;
import com.sanvalero.flightsws.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Creado por @author: Javier
 * el 15/03/2021
 */
@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Optional<Flight> findFlightById(long id) {
        return flightRepository.findById(id);
    }

    @Override
    public Set<Flight> findFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Set<Flight> findFlightsByOrigin(String origin) {
        return flightRepository.findByOrigin(origin);
    }

    @Override
    public Set<Flight> findFlightsByDestination(String destination) {
        return flightRepository.findByDestination(destination);
    }

    @Override
    public Set<Flight> findFlightsByStopover(int stopover) {
        return flightRepository.findByStopover(stopover);
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight modifyFlight(long id, Flight newFlight) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        newFlight.setId(flight.getId());
        return flightRepository.save(newFlight);
    }

    @Override
    public void deleteFlight(long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        flightRepository.deleteById(id);
    }

    @Override
    public void deleteFlightByDestination(String destination) {
        Set<Flight> flights = flightRepository.findByDestination(destination);
        flightRepository.deleteAll(flights);
    }
}
