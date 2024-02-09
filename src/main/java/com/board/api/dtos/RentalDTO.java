package com.board.api.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalDTO {

    @NotNull
    private Long customerId;

    @NotNull
    private Long gameId;
    
    @NotNull
    @Min(value = 1, message = "Days must be greater than 1.")
    private int daysRented;
}
