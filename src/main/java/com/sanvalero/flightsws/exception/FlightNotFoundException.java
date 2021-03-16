package com.sanvalero.flightsws.exception;

/**
 * Creado por @author: Javier
 * el 15/03/2021
 */
public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(){
        super();
    }

    public FlightNotFoundException(String message){
        super(message);
    }

    public FlightNotFoundException(long id){
        super("Flight not found: " + id);
    }

}
