package com.olikassessment.libraryManagement.Controller;

import com.olikassessment.libraryManagement.DTO.MessageResponseDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.AuthorRequestDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.UpdateAuthorRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.AuthorResponseDto;
import com.olikassessment.libraryManagement.Model.Author;
import com.olikassessment.libraryManagement.Service.AuthorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity createAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        AuthorResponseDto authorResponseDto = authorService.createAuthor(authorRequestDto);

        return new ResponseEntity(authorResponseDto, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateAuthor")
    public ResponseEntity updateAuthor(@RequestBody UpdateAuthorRequestDto updateAuthorRequestDto){
        AuthorResponseDto response;
        try{
            response = authorService.updateAuthor(updateAuthorRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAuthor/{id}")
    public ResponseEntity getAuthorById(@PathVariable("id") int id){
        AuthorResponseDto responseDto;
        try{
            responseDto = authorService.getAuthorById(id);
        }catch(Exception e){
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllAuthors")
    public ResponseEntity getAllAuthors(){
        List<AuthorResponseDto> authorRequestDtos = authorService.getAllAuthors();

        return new ResponseEntity<>(authorRequestDtos, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") int id){
        MessageResponseDto responseDto;
        try{
            responseDto = authorService.deleteAuthor(id);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }
}
