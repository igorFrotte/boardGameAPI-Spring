package com.board.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.board.api.dtos.RentalDTO;
import com.board.api.models.RentalModel;
import com.board.api.services.RentalService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping
public class RentalController {
    
    final RentalService rentalService;

    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping("/rentals")
    public ResponseEntity<List<RentalModel>> getAllRentals() {
        List<RentalModel> rentals = rentalService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }
    
    @PostMapping("/rentals")
    public ResponseEntity<RentalModel> createNewRentail (@RequestBody @Valid RentalDTO body) {
        RentalModel rental = rentalService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }
    
    @PutMapping("/rentals/{id}/return")
    public ResponseEntity<RentalModel> returnRental (@PathVariable Long id) {
        RentalModel rental = rentalService.update(id);      
        return ResponseEntity.status(HttpStatus.OK).body(rental);
    }
}
