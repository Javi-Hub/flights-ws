package com.sanvalero.flightsws.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Creado por @author: Javier
 * el 15/03/2021
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {

    public static final int NO_ERROR = 0;
    public static final int NOT_FOUND = 101;

    public static final String NO_MESSAGE = "";

    private Error error;

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static class Error{

        @Schema(description = "Number code Error", example = "404")
        private long errorCode;

        @Schema(description = "Message of Error", example = "Flight not found")
        private String errorMessage;
    }

    public static Response notErrorResponse(){
        return new Response(new Error(NO_ERROR, NO_MESSAGE));
    }

    public static Response errorResponse(int errorCode, String errorMessage){
        return new Response(new Error(errorCode, errorMessage));
    }

}
