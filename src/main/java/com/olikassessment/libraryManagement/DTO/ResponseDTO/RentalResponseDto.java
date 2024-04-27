package com.olikassessment.libraryManagement.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalResponseDto {

    private int id;
    private String rentalName;
    private String number;
    private int bookId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
}
