package com.olikassessment.libraryManagement.DTO.ResponseDTO;

import com.olikassessment.libraryManagement.Model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponseDto {

    private int id;
    private String name;
    private String biography;
    private List<Book> books;
}
