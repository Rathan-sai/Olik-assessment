package com.olikassessment.libraryManagement.Service;

import com.olikassessment.libraryManagement.DTO.MessageResponseDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.BookResponseDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.RentalResponseDto;
import com.olikassessment.libraryManagement.Exception.BookNotFoundException;
import com.olikassessment.libraryManagement.Model.Book;
import com.olikassessment.libraryManagement.Model.Rental;
import com.olikassessment.libraryManagement.Repository.BookRepository;
import com.olikassessment.libraryManagement.Repository.RentalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private RentalService rentalService;

    // Tests for getOverdueRentals method
    @Test
    void getOverdueRentals_Success() {
        // Mock rental data
        Rental rental1 = new Rental();
        rental1.setId(1);
        rental1.setBookId(1);
        rental1.setRentalDate(LocalDate.now().minusDays(15));
        rental1.setReturnDate(LocalDate.now().minusDays(1));

        Rental rental2 = new Rental();
        rental2.setId(2);
        rental2.setBookId(2);
        rental2.setRentalDate(LocalDate.now().minusDays(10));
        rental2.setReturnDate(LocalDate.now().plusDays(5));

        List<Rental> rentals = new ArrayList<>();
        rentals.add(rental1);
        rentals.add(rental2);

        // Mock repository behavior
        Mockito.when(rentalRepository.findByReturnDateBeforeAndBookIsRented(any(LocalDate.class), any(Boolean.class)))
                .thenReturn(rentals);

        // Call the method under test
        List<RentalResponseDto> result = rentalService.getOverdueRentals();

        // Verify the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(rental1.getBookId(), result.get(0).getBookId());
    }

    // Add similar tests for other scenarios

    // Tests for returnBook method
    @Test
    void returnBook_Success() throws Exception {
        // Mock book data
        int bookId = 1;
        Book book = new Book();
        book.setId(bookId);
        book.setRented(true);

        // Mock rental data
        Rental rental = new Rental();
        rental.setId(1);
        rental.setBookId(bookId);
        rental.setBook(List.of(book));

        // Mock repository behavior
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Mockito.when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        // Call the method under test
        MessageResponseDto result = rentalService.returnBook(bookId);

        // Verify the result
        assertNotNull(result);
        assertEquals("Book Returned Successfully \n Thank You for reading our book.", result.getMessage());
        assertFalse(book.isRented());
    }

    // Add similar tests for other scenarios

    // Tests for findBooksAvailableForRent method
    @Test
    void findBooksAvailableForRent_Success() {
        // Mock book data
        Book book1 = new Book();
        book1.setId(1);
        book1.setRented(false);

        Book book2 = new Book();
        book2.setId(2);
        book2.setRented(true);

        List<Book> books = List.of(book1, book2);

        // Mock repository behavior
        Mockito.when(bookRepository.findAllByIsRentedFalse()).thenReturn(books);

        // Call the method under test
        List<BookResponseDto> result = rentalService.findBooksAvailableForRent();

        // Verify the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(book1.getId(), result.get(0).getId());
    }

    // Add similar tests for other scenarios

    // Tests for findBooksCurrentlyRented method
    @Test
    void findBooksCurrentlyRented_Success() {
        // Mock book data
        Book book1 = new Book();
        book1.setId(1);
        book1.setRented(false);

        Book book2 = new Book();
        book2.setId(2);
        book2.setRented(true);

        List<Book> books = List.of(book1, book2);

        // Mock repository behavior
        Mockito.when(bookRepository.findAllByIsRentedTrue()).thenReturn(books);

        // Call the method under test
        List<BookResponseDto> result = rentalService.findBooksCurrentlyRented();

        // Verify the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(book2.getId(), result.get(0).getId());
    }

    // Add similar tests for other scenarios
}

