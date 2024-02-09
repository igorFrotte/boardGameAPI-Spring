package com.board.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.board.api.models.RentalModel;

public interface RentalRepository extends JpaRepository<RentalModel, Long>{
    
    @Query(value = "SELECT * FROM rentals ORDER BY id DESC", nativeQuery = true)
    List<RentalModel> findAllOrderById();

    @Query(value = "SELECT COUNT(*) FROM rentals WHERE game = :game_id AND return_date IS NULL", nativeQuery = true)
    int countByGame(@Param("game_id") Long gameId);

}