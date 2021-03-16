package com.sanvalero.flightsws.controller;

import com.sanvalero.flightsws.domain.Flight;
import com.sanvalero.flightsws.exception.FlightNotFoundException;
import com.sanvalero.flightsws.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.FileOutputStream;
import java.util.Set;

import static com.sanvalero.flightsws.controller.Response.NOT_FOUND;

/**
 * Creado por @author: Javier
 * el 15/03/2021
 */
@RestController
@Tag(name = "Flights", description = "Information World Flights")
public class FlightController {

    private final Logger logger = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;

    @Operation(summary = "Get all flights")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all flights",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Flight.class))))
    })
    @GetMapping(value = "/flights", produces = "application/json")
    public ResponseEntity<Set<Flight>> getAllFlights(){
        logger.info("[init getAllFlights]");
        Set<Flight> flights = flightService.findFlights();
        logger.info("[end getAllFlights]");
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Get flights filtered by origin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List flights filtered by origin",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Flight.class))))
    })
    @GetMapping(value = "/flights/origin/{origin}", produces = "application/json")
    public ResponseEntity<Set<Flight>> getFlightsByOrigin(@PathVariable String origin){
        logger.info("[init getFlightsByOrigin]");
        Set<Flight> flights = flightService.findFlightsByOrigin(origin);
        logger.info("[end getFlightsByOrigin]");
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Get flights filtered by destination")
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "List flights filtered by destination",
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Flight.class))))
    })
    @GetMapping(value = "/flights/destination/{destination}", produces = "application/json")
    public ResponseEntity<Set<Flight>> getFlightsByDestination(@PathVariable String destination){
        logger.info("[init getFlightsByDestination]");
        Set<Flight> flights = flightService.findFlightsByDestination(destination);
        logger.info("[end getFligthsByDestination]");
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Get flights filtered by stopover")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List flights filtered by stopover",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Flight.class))))
    })
    @GetMapping(value = "/flights/stopovers/{stopover}", produces = "application/json")
    public ResponseEntity<Set<Flight>> getFligthsByStopover(@PathVariable int stopover){
        logger.info("[init getFlightsByStopover]");
        Set<Flight> flights = flightService.findFlightsByStopover(stopover);
        logger.info("[end getFlightsByStopover]");
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @Operation(summary = "Get a certain flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight exists",
                            content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "Flight does not exist",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/flights/{id}", produces = "application/json")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id){
        logger.info("[init getFlightById]");
        Flight flight = flightService.findFlightById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @Operation(summary = "Insert a new flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flight registered",
                            content = @Content(schema = @Schema(implementation = Flight.class)))
    })
    @PostMapping(value = "/flights", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        logger.info("[init addFlight]");
        Flight addedFlight = flightService.addFlight(flight);
        logger.info("[end addFlight]");
        return new ResponseEntity<>(addedFlight, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight deleted",
                            content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Flight does not exist",
                            content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/flights/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteFlight(@PathVariable long id){
        logger.info("[init deleteFlight]");
        flightService.deleteFlight(id);
        logger.info("end get");
        return new ResponseEntity<>(Response.notErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "Delete all flights by destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All flights deleted by destination",
                            content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Destination does not exist",
                            content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/flights", produces = "application/json")
    public ResponseEntity<Response> deleteFlightsByDestination(@RequestParam(value = "destination") String destination){
        logger.info("[init deleteFlightsByDestination]");
        flightService.deleteFlightByDestination(destination);
        logger.info("[end deleteFlightsByDestination]");
        return new ResponseEntity<>(Response.notErrorResponse(), HttpStatus.OK);
    }

    @Operation(summary = "Update a certain flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight updated",
                            content = @Content(schema = @Schema(implementation = Flight.class))),
            @ApiResponse(responseCode = "404", description = "Flight does not exist",
                            content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/flights/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Flight> modifyFlight(@PathVariable long id, @RequestBody Flight newFlight){
        logger.info("[init modifyFlight]");
        Flight flight = flightService.modifyFlight(id, newFlight);
        logger.info("[end modifyFlight]");
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(FlightNotFoundException fnfe){
        Response response = Response.errorResponse(NOT_FOUND, fnfe.getMessage());
        logger.error(fnfe.getMessage(), fnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
