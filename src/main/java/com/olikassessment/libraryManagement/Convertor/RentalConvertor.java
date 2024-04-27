package com.olikassessment.libraryManagement.Convertor;

import com.olikassessment.libraryManagement.DTO.RequestDTO.RentalRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.RentalResponseDto;
import com.olikassessment.libraryManagement.Model.Rental;

public class RentalConvertor {
    public static Rental rentalRequestDtoToRental(RentalRequestDto rentalRequestDto){
        return Rental.builder()
                .rentalName(rentalRequestDto.getRentalName())
                .number(rentalRequestDto.getNumber())
                .bookId(rentalRequestDto.getBookId())
                .build();
    }

    public static RentalResponseDto rentalToRentalResponseDto(Rental rental){
        return RentalResponseDto.builder()
                .id(rental.getId())
                .rentalDate(rental.getRentalDate())
                .rentalName(rental.getRentalName())
                .returnDate(rental.getReturnDate())
                .bookId(rental.getBookId())
                .build();
    }
}
