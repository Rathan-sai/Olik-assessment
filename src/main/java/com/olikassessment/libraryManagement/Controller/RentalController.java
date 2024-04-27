package com.olikassessment.libraryManagement.Controller;

import com.olikassessment.libraryManagement.DTO.MessageResponseDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.RentalRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.BookResponseDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.RentalResponseDto;
import com.olikassessment.libraryManagement.Model.Book;
import com.olikassessment.libraryManagement.Service.RentalService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
//@Log4j2
@RequestMapping("/Rental")
public class RentalController {

//    Logger logger = (Logger) LoggerFactory.getLogger(RentalController.class);
    @Autowired
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rentBook")
    public ResponseEntity rentBook(@RequestBody RentalRequestDto rentalRequestDto){
        RentalResponseDto responseDto;
        try{
            responseDto = rentalService.rentBook(rentalRequestDto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/returnBook/{id}")
    public ResponseEntity returnBook(@PathVariable("id") int id){
        MessageResponseDto messageResponseDto;
        try{
            messageResponseDto = rentalService.returnBook(id);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(messageResponseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/overdueRentals")
    public ResponseEntity getOverdueRentals(){
        List<RentalResponseDto> rentalResponseDtos = rentalService.getOverdueRentals();
        return new ResponseEntity(rentalResponseDtos, HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkOverdue/{bookId}")
    public ResponseEntity checkOverdue(@PathVariable("bookId") int bookId){
        RentalResponseDto responseDto;
        try{
            responseDto = rentalService.checkOverdue(bookId);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/findBooksAvailableForRent")
    public ResponseEntity findBooksAvailableForRent(){
        List<BookResponseDto> Books = rentalService.findBooksAvailableForRent();

        return new ResponseEntity(Books, HttpStatus.ACCEPTED);
    }

    @GetMapping("/findBooksCurrentlyRented")
    public ResponseEntity findBooksCurrentlyRented(){
        List<BookResponseDto> Books = rentalService.findBooksCurrentlyRented();

        return new ResponseEntity(Books, HttpStatus.ACCEPTED);
    }
}
