package com.board.api.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.board.api.dtos.RentalDTO;
import com.board.api.exceptions.clientExceptions.ClientNotFoundException;
import com.board.api.exceptions.gameExceptions.GameNotFoundException;
import com.board.api.exceptions.rentalExceptions.RentalAlreadyCompletedException;
import com.board.api.exceptions.rentalExceptions.RentalStockNotAvailableException;
import com.board.api.exceptions.rentalExceptions.RentalNotFoundException;
import com.board.api.models.ClientModel;
import com.board.api.models.GameModel;
import com.board.api.models.RentalModel;
import com.board.api.repositories.ClientRepository;
import com.board.api.repositories.GameRepository;
import com.board.api.repositories.RentalRepository;

@Service
public class RentalService {
    final ClientRepository clientRepository;
    final GameRepository gameRepository;
    final RentalRepository rentalRepository;

    RentalService(ClientRepository clientRepository, GameRepository gameRepository, RentalRepository rentalRepository){
        this.clientRepository = clientRepository;
        this.gameRepository = gameRepository;        
        this.rentalRepository = rentalRepository;
    }

    public List<RentalModel> findAll(){
        return rentalRepository.findAllOrderById();
    }

    public RentalModel save (RentalDTO dto){
        ClientModel client = clientRepository.findById(dto.getCustomerId()).orElseThrow(
            ()-> new ClientNotFoundException("Client not found.")
        );

        GameModel game = gameRepository.findById(dto.getGameId()).orElseThrow(
            () -> new GameNotFoundException("Game not found.")
        );

        int rentedStock = rentalRepository.countByGame(game.getId());
        int availableStock = game.getStockTotal();

        if(rentedStock >= availableStock){
            throw new RentalStockNotAvailableException("Insufficient stock.");
        }

        int originalPrice = dto.getDaysRented() * game.getPricePerDay();

        RentalModel rental = new RentalModel(dto, client, game, originalPrice);
        return rentalRepository.save(rental);
    }

    private int calculateDelayFee (LocalDate rentDate, int daysRented, int pricePerDay){
        LocalDate currentDate = LocalDate.now();
        Long periodRental = ChronoUnit.DAYS.between(rentDate, currentDate); 

        if(periodRental <= daysRented){
            return 0;
        } else{
            return (int) ((periodRental - daysRented) * pricePerDay);  
        }
    }

    public RentalModel update (Long id){
        RentalModel rental = rentalRepository.findById(id).orElseThrow(
            () -> new RentalNotFoundException("Rental not found.")
        );

        if(rental.getReturnDate() != null){
            throw new RentalAlreadyCompletedException("This rent is already completed.");
        }

        int delayFee = calculateDelayFee(rental.getRentDate(),rental.getDaysRented(), rental.getOriginalPrice());

        RentalModel newRental = new RentalModel(rental, delayFee);
        newRental.setId(id);
        return rentalRepository.save(newRental);
    }
}