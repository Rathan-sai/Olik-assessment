package com.olikassessment.libraryManagement.Convertor;

import com.olikassessment.libraryManagement.DTO.RequestDTO.BookRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.BookResponseDto;
import com.olikassessment.libraryManagement.Model.Book;

public class BookConvertor {

    public static Book bookReqeustDtoToBook(BookRequestDto bookRequestDto){
        return Book.builder()
                .title(bookRequestDto.getTitle())
                .publicationYear(bookRequestDto.getPublicationYear())
                .build();
    }

    public static BookResponseDto BookToBookResponseDto(Book book){
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .publicationYear(book.getPublicationYear())
                .author(book.getAuthor())
                .build();
    }
}
