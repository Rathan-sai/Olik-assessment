package com.olikassessment.libraryManagement.Convertor;

import com.olikassessment.libraryManagement.DTO.RequestDTO.AuthorRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.AuthorResponseDto;
import com.olikassessment.libraryManagement.Model.Author;

public class AuthorConvertor {
    public static Author AuthorReqestDtoToAuthor(AuthorRequestDto authorRequestDto) {
        return Author.builder()
                .name(authorRequestDto.getName())
                .biography(authorRequestDto.getBiography())
                .build();
    }

    public static AuthorResponseDto authorToAuthorResponseDto(Author author){
        return AuthorResponseDto.builder()
                .id(author.getId())
                .biography(author.getBiography())
                .name(author.getName())
                .books(author.getBooks())
                .build();
    }
}
