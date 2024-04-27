package com.olikassessment.libraryManagement.Service;

import com.olikassessment.libraryManagement.Convertor.AuthorConvertor;
import com.olikassessment.libraryManagement.DTO.MessageResponseDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.AuthorRequestDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.UpdateAuthorRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.AuthorResponseDto;
import com.olikassessment.libraryManagement.Exception.AuthorNotFoundException;
import com.olikassessment.libraryManagement.Model.Author;
import com.olikassessment.libraryManagement.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto) {

        Author author = AuthorConvertor.AuthorReqestDtoToAuthor(authorRequestDto);
        authorRepository.save(author);
        AuthorResponseDto authorResponseDto = AuthorConvertor.authorToAuthorResponseDto(author);

        return authorResponseDto;
    }

    public AuthorResponseDto updateAuthor(UpdateAuthorRequestDto authorRequestDto) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor;
        Author author;
        try{
            optionalAuthor = authorRepository.findById(authorRequestDto.getId());
            author = optionalAuthor.get();
        }catch (Exception e){
            throw new AuthorNotFoundException("Invalid Author Id");
        }

        if(!authorRequestDto.getName().isEmpty()){
            author.setName(authorRequestDto.getName());
        }
        if(!authorRequestDto.getBiography().isEmpty()){
            author.setBiography(authorRequestDto.getBiography());
        }

        authorRepository.save(author);

        AuthorResponseDto authorResponseDto = AuthorConvertor.authorToAuthorResponseDto(author);
        return authorResponseDto;
    }

    public AuthorResponseDto getAuthorById(int id) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor;
        Author author;
        try{
            optionalAuthor = authorRepository.findById(id);
            author = optionalAuthor.get();
        }catch (Exception e){
            throw new AuthorNotFoundException("Invalid Author Id");
        }
        AuthorResponseDto authorResponseDto = AuthorConvertor.authorToAuthorResponseDto(author);
        return authorResponseDto;
    }

    public List<AuthorResponseDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorResponseDto> authorResponseDtos = new ArrayList<>();
        for (Author author : authors){
            authorResponseDtos.add(AuthorConvertor.authorToAuthorResponseDto(author));
        }
        return authorResponseDtos;
    }

    public MessageResponseDto deleteAuthor(int id) throws AuthorNotFoundException {
        Optional<Author> optionalAuthor;
        Author author;
        try{
            optionalAuthor = authorRepository.findById(id);
            author = optionalAuthor.get();
        }catch (Exception e){
            throw new AuthorNotFoundException("Invalid Author Id");
        }
        authorRepository.delete(author);
        MessageResponseDto messageResponseDto = new MessageResponseDto();
        messageResponseDto.setMessage("Author removed from the Database");
        return messageResponseDto;
    }
}
