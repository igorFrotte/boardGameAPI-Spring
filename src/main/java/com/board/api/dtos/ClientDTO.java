package com.board.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDTO {
    
    @NotBlank
    @Size(max = 150, message = "Max 150 characters for name.")
    private String name;

    @NotBlank
    @Size(min = 11, max = 11, message = "CPF must be a string with 11 characters.")
    private String cpf;
}
