package com.board.api.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {

    @NotBlank
    @Size(max = 150, message = "Max 150 characters for name.")
    private String name;

    @NotBlank
    private String image;

    @NotNull
    @Min(value = 1, message = "Stock Total must be a number and greater than 0.")
    private int stockTotal;

    @NotNull
    @Min(value = 1, message = "Price per day must be a number and greater than 0.")
    private int pricePerDay;
}