package com.sanvalero.flightsws.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Creado por @author: Javier
 * el 15/03/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "flights")
public class Flight {

    @Schema(description = "Flight Identification", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Flight origin", example = "Madrid", required = true)
    @NotBlank
    @Column
    private String origin;

    @Schema(description = "Flight destination", example = "Zaragoza")
    @NotBlank
    @Column
    private String destination;

    @Schema(description = "Flight price", example = "210.99", defaultValue = "0")
    @Column
    private float price;

    @Schema(description = "Number of stopovers", example = "2", defaultValue = "0")
    @Column
    private int stopover;

    @Schema(description = "Flight company", example = "British Airways")
    @Column
    private String company;

}
