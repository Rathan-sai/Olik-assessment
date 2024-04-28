//package com.olikassessment.libraryManagement.Service;
//
//public class BookService {
//}
package com.olikassessment.libraryManagement.Service;

import com.olikassessment.libraryManagement.DTO.RequestDTO.BookRequestDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.UpdateBookRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.BookResponseDto;
import com.olikassessment.libraryManagement.Exception.AuthorNotFoundException;
import com.olikassessment.libraryManagement.Exception.BookNotFoundException;
import com.olikassessment.libraryManagement.Model.Author;
import com.olikassessment.libraryManagement.Model.*;
import com.olikassessment.libraryManagement.Repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testFindAllBooks() {
        // Mock the behavior of the bookRepository.findAll() method
        List<Book> books = new ArrayList<>();
        Author author = new Author();
        Rental rental = new Rental();
        books.add(new Book(1, "Title", false, author, "ISBN123", "2022", rental));
        books.add(new Book(2, "Title1", false, author, "ISBN1553", "2023", rental));


        Mockito.when(bookRepository.findAll()).thenReturn(books);

        // Call the method under test
        List<BookResponseDto> foundBooks = bookService.getAllBooks();

        // Assert that the correct list of books is returned
        assertEquals(2, foundBooks.size());
    }

    @Test
    public void testFindBookById() throws BookNotFoundException {
        // Mock the behavior of the bookRepository.findById() method
        int bookId = 1;
//        Book book = new Book(bookId, "Title", new Author(), "ISBN123", 2022);
        Author author = new Author();
        Rental rental = new Rental();
        Book book = new Book(1, "Title", true, author, "ISBN123", "2022", rental);

        Optional<Book> optionalBook = Optional.of(book);

        Mockito.when(bookRepository.findById(bookId)).thenReturn(optionalBook);

        // Call the method under test
        Optional<BookResponseDto> foundBook = Optional.ofNullable(bookService.getBookById(bookId));

        // Assert that the correct book is returned
        assertTrue(foundBook.isPresent());
        assertEquals(bookId, foundBook.get().getId());
    }

    @Test
    public void testSaveBook() throws Exception {
        // Mock the behavior of the bookRepository.save() method
        Author author = new Author();
        Rental rental = new Rental();
        Book bookToSave = new Book(1, "Title", false, author, "ISBN123", "2022", rental);
        Book savedBook =  new Book(1, "Title", false, author, "ISBN123", "2022", rental);


        Mockito.when(bookRepository.save(bookToSave)).thenReturn(savedBook);

        BookRequestDto bookRequestDto = new BookRequestDto("Title", "2022","9785300866860", 2);

        // Call the method under test
        BookResponseDto returnedBook = bookService.addBook(bookRequestDto);

        // Assert that the correct book is saved and returned
        assertNotNull(returnedBook);
        assertEquals(savedBook.getId(), returnedBook.getId());
        assertEquals(savedBook.getTitle(), returnedBook.getTitle());
    }

    @Test
    public void testUpdateBook() throws Exception {
        // Mock the behavior of the bookRepository.save() method
        int bookId = 1;
        Book existingBook = new Book(bookId, "Title", false, new Author(), "ISBN123", "2022",new Rental());
        Book updatedBookData = new Book(1, "Updated Title", false, new Author(),"ISBN123", "2022",new Rental());
        Book updatedBook = new Book(1, "Updated Title", false, new Author(),"ISBN123", "2022",new Rental());

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        Mockito.when(bookRepository.save(updatedBookData)).thenReturn(updatedBook);

        UpdateBookRequestDto updateBookRequestDto = new UpdateBookRequestDto();
        updateBookRequestDto.setTitle("updatedTitle");
        // Call the method under test
        BookResponseDto returnedBook = bookService.updateBook(updateBookRequestDto);

        // Assert that the correct book is updated and returned
        assertNotNull(returnedBook);
        assertEquals(updatedBook.getId(), returnedBook.getId());
        assertEquals(updatedBook.getTitle(), returnedBook.getTitle());
    }

    @Test
    public void testDeleteBook() throws Exception {
        // Mock the behavior of the bookRepository.deleteById() method
        int bookId = 1;

        // Call the method under test
        bookService.removeBook(bookId);

        // Verify that the bookRepository.deleteById() method is called with the correct bookId
        Mockito.verify(bookRepository).deleteById(bookId);
    }
}
