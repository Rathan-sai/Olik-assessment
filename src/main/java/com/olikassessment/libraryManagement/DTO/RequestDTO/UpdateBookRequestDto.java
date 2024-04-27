package com.olikassessment.libraryManagement.DTO.RequestDTO;

import com.olikassessment.libraryManagement.Model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookRequestDto {

    private int id;
    private String title;
    private String isbn;
    private String publicationYear;
    private int authorId;
}
