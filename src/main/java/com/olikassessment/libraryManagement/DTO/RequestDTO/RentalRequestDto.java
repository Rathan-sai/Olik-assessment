package com.olikassessment.libraryManagement.DTO.RequestDTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalRequestDto {

    private String rentalName;
    private String number;
    private int bookId;
//    private String date;
}
