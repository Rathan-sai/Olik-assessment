package com.olikassessment.libraryManagement.Service;

import com.olikassessment.libraryManagement.Convertor.BookConvertor;
import com.olikassessment.libraryManagement.Convertor.RentalConvertor;
import com.olikassessment.libraryManagement.DTO.MessageResponseDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.RentalRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.BookResponseDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.RentalResponseDto;
import com.olikassessment.libraryManagement.Exception.BookNotFoundException;
import com.olikassessment.libraryManagement.Model.Book;
import com.olikassessment.libraryManagement.Model.Rental;
import com.olikassessment.libraryManagement.Repository.BookRepository;
import com.olikassessment.libraryManagement.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;

    public RentalService(RentalRepository rentalRepository,
                         BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
    }

    public RentalResponseDto rentBook(RentalRequestDto rentalRequestDto) throws BookNotFoundException {
         Rental rental = RentalConvertor.rentalRequestDtoToRental(rentalRequestDto);
//         if(rentalRepository.existsByRentalName(rentalRequestDto.getRentalName())){
//             rental = rentalRepository.findByRentalName(rentalRequestDto.getRentalName());
//         }else{
//             rental = RentalConvertor.rentalRequestDtoToRental(rentalRequestDto);
//         }

         Optional<Book> optionalBook;
         Book book;
         try{
             optionalBook = bookRepository.findById(rentalRequestDto.getBookId());
             book = optionalBook.get();
         }catch (Exception e){
             throw new BookNotFoundException("Invalid BookId");
         }

         if(book.isRented()){
            throw new BookNotFoundException("Book is Already Rented");
         }

         rental.setRentalDate(LocalDate.now());
         rental.setReturnDate(LocalDate.now().plusDays(14));
//        (rental.getBook()).add(book);
        List<Book> books = new ArrayList<>();
        books = rental.getBooks();
        books.add(book);
        rental.setBook(books);
        rentalRepository.save(rental);

        book.setRented(true);
        book.setRental(rental);
        bookRepository.save(book);

         System.out.println(rental.getRentalName()+" "+rental.getNumber()+" "+rental.getBooks());

         RentalResponseDto rentalResponseDto = RentalConvertor.rentalToRentalResponseDto(rental);

         return rentalResponseDto;
    }

    public RentalResponseDto checkOverdue(int bookId) throws BookNotFoundException {
        Rental rental;
        Optional<Book> optionalBook;
        Book book;
        try{
            optionalBook = bookRepository.findById(bookId);
            book = optionalBook.get();
        }catch (Exception e){
            throw new BookNotFoundException("Invalid BookId");
        }
        rental = book.getRental();

        RentalResponseDto rentalResponseDto = RentalConvertor.rentalToRentalResponseDto(rental);

        return rentalResponseDto;
    }

    public List<RentalResponseDto> getOverdueRentals() {
        List<RentalResponseDto> rentalResponseDtos = new ArrayList<>();
        List<Rental> rentals = rentalRepository.findByReturnDateBeforeAndBookIsRented(LocalDate.now(), true);

        for (Rental rental : rentals){
            rentalResponseDtos.add(RentalConvertor.rentalToRentalResponseDto(rental));
        }

        return rentalResponseDtos;
    }

    public MessageResponseDto returnBook(int id) throws Exception {
        Optional<Book> optionalBook;
        Book book;
        try{
            optionalBook = bookRepository.findById(id);
            book = optionalBook.get();
        }catch (Exception e){
            throw new BookNotFoundException("Invalid BookId");
        }
        if(!book.isRented()){
            throw new Exception("Invalid bookId. This book is not rented");
        }
        book.setRented(false);
        Rental rental = book.getRental();
        List<Book> books = new ArrayList<>();
        books = rental.getBooks();
        books.remove(book);
        rental.setBook(books);
        rentalRepository.save(rental);
        bookRepository.save(book);

        MessageResponseDto messageResponseDto = new MessageResponseDto();
        messageResponseDto.setMessage("Book Returned Successfully \n Thank You for reading our book.");
        return messageResponseDto;
    }

    public List<BookResponseDto> findBooksAvailableForRent() {
        List<Book> books = bookRepository.findAllByIsRentedFalse();
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for(Book book : books){
            bookResponseDtos.add(BookConvertor.BookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }

    public List<BookResponseDto> findBooksCurrentlyRented() {
        List<Book> books = bookRepository.findAllByIsRentedTrue();
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for(Book book : books){
            bookResponseDtos.add(BookConvertor.BookToBookResponseDto(book));
        }
        return bookResponseDtos;
    }
}
