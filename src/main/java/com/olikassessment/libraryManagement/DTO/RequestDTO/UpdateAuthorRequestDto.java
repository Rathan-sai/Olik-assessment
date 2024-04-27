package com.olikassessment.libraryManagement.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAuthorRequestDto {

    private int id;
    private String name;
    private String Biography;
}
