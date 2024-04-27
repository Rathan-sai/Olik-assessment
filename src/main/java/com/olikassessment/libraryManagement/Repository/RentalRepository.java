package com.olikassessment.libraryManagement.Repository;

import com.olikassessment.libraryManagement.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    Rental findByRentalName(String rentalName);

    boolean existsByRentalName(String rentalName);

    // Method to find rentals where the return date has passed and the book hasn't been returned
    List<Rental> findByReturnDateBeforeAndBookIsRented(LocalDate currentDate, boolean isRented);

}
