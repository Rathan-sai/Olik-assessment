package com.olikassessment.libraryManagement.DTO.RequestDTO;

import com.olikassessment.libraryManagement.Model.Author;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestDto {

    private String title;
    private String publicationYear;
//    private String isbn;
    private int authorId;
}
