package com.olikassessment.libraryManagement.Controller;

import com.olikassessment.libraryManagement.DTO.MessageResponseDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.BookRequestDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.UpdateAuthorRequestDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.UpdateBookRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.AuthorResponseDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.BookResponseDto;
import com.olikassessment.libraryManagement.Model.Book;
import com.olikassessment.libraryManagement.Repository.BookRepository;
import com.olikassessment.libraryManagement.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody BookRequestDto bookRequestDto) {
        BookResponseDto response;
        try {
            response = bookService.addBook(bookRequestDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateBook")
    public ResponseEntity updateBook(@RequestBody UpdateBookRequestDto bookResponseDto) {
        BookResponseDto response;
        try {
            response = bookService.updateBook(bookResponseDto);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity getBookById(@PathVariable("id") int id) {
        BookResponseDto responseDto;
        try {
            responseDto = bookService.getBookById(id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getBooksByAuthor/{id}")
    public ResponseEntity getBooksByAuthor(@PathVariable("id") int id) {
        List<BookResponseDto> responseDto;
        try {
            responseDto = bookService.getBooksByAuthor(id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity getAllBooks() {
        List<BookResponseDto> bookResponseDtos = bookService.getAllBooks();

        return new ResponseEntity(bookResponseDtos, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/removeBook/{id}")
    public ResponseEntity removeBook(@PathVariable("id") int id) {
        MessageResponseDto responseDto;
        try {
            responseDto = bookService.removeBook(id);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }
}
