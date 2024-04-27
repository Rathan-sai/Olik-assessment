package com.olikassessment.libraryManagement.DTO.ResponseDTO;

import com.olikassessment.libraryManagement.Model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto {
    private int id;
    private String title;
    private String isbn;
    private String publicationYear;
    private Author author;
}
