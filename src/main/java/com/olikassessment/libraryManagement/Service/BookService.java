package com.olikassessment.libraryManagement.Service;

import com.olikassessment.libraryManagement.Convertor.BookConvertor;
import com.olikassessment.libraryManagement.DTO.MessageResponseDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.BookRequestDto;
import com.olikassessment.libraryManagement.DTO.RequestDTO.UpdateBookRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.BookResponseDto;
import com.olikassessment.libraryManagement.Exception.AuthorNotFoundException;
import com.olikassessment.libraryManagement.Exception.BookNotFoundException;
import com.olikassessment.libraryManagement.Model.Author;
import com.olikassessment.libraryManagement.Model.Book;
import com.olikassessment.libraryManagement.Repository.AuthorRepository;
import com.olikassessment.libraryManagement.Repository.BookRepository;
import com.olikassessment.libraryManagement.Service.Validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final ValidationService validationService;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, ValidationService validationService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.validationService = validationService;
    }

    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws Exception {
        if(!validationService.isValidISBN(bookRequestDto.getIsbn())){
            throw new Exception("Invalid ISBN number");
        }
        Book book = BookConvertor.bookReqeustDtoToBook(bookRequestDto);
//        bookRepository.save(book);
        Optional<Author> optionalAuthor;
        Author author;
        try{
            optionalAuthor  = authorRepository.findById(bookRequestDto.getAuthorId());
            author = optionalAuthor.get();
        }catch (Exception e){
            throw new AuthorNotFoundException("Invalid Author ID");
        }

        book.setIsbn(validationService.generateISBN());
        book.setAuthor(author);
        book.setRented(false);
        bookRepository.save(book);

        author.getBooks().add(book);
        authorRepository.save(author);

        BookResponseDto bookResponseDto = BookConvertor.BookToBookResponseDto(book);
        return bookResponseDto;
    }

    public BookResponseDto updateBook(UpdateBookRequestDto updateBookRequestDto) throws Exception{
        Optional<Book> optionalBook;
        Book book = new Book();
        try{
            optionalBook = bookRepository.findById(updateBookRequestDto.getId());
            book = optionalBook.get();
        }catch (Exception e){
            throw new BookNotFoundException("Invalid book id");
        }

        if(!updateBookRequestDto.getTitle().isEmpty()){
            book.setTitle(updateBookRequestDto.getTitle());
        }
        if(!updateBookRequestDto.getIsbn().isEmpty()){
            book.setIsbn(updateBookRequestDto.getIsbn());
        }
        if(!updateBookRequestDto.getPublicationYear().isEmpty()){
            book.setPublicationYear(updateBookRequestDto.getPublicationYear());
        }
//        System.out.println(updateBookRequestDto.getAuthorId());
        Optional<Author> optionalAuthor;
        Author author = new Author();
        if(updateBookRequestDto.getAuthorId() > 0){
            try{
                optionalAuthor  = authorRepository.findById(updateBookRequestDto.getAuthorId());
                author = optionalAuthor.get();
            }catch (Exception e){
                throw new AuthorNotFoundException("Invalid Author ID");
            }
            System.out.println(author.getName());
            Author author1 = book.getAuthor();
            System.out.println(author1.getName());
            author1.getBooks().remove(book);

            author.getBooks().add(book);

            authorRepository.save(author);
            authorRepository.save(author1);

            book.setAuthor(author);
        }

        BookResponseDto bookResponseDto = BookConvertor.BookToBookResponseDto(book);
        bookRepository.save(book);

        return bookResponseDto;
    }

    public BookResponseDto getBookById(int id) throws BookNotFoundException {
        Optional<Book> optionalBook;
        Book book;
        try{
            optionalBook = bookRepository.findById(id);
            book = optionalBook.get();
        }catch (Exception e){
            throw new BookNotFoundException("Invalid book Id");
        }

        BookResponseDto bookResponseDto = BookConvertor.BookToBookResponseDto(book);

        return bookResponseDto;
    }

    public List<BookResponseDto> getAllBooks() {
        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        List<Book> books = bookRepository.findAll();

        for(Book book : books){
            bookResponseDtos.add(BookConvertor.BookToBookResponseDto(book));
        }

        return bookResponseDtos;
    }

    public MessageResponseDto removeBook(int id) throws Exception {
        Optional<Book> optionalBook;
        Book book;
        try{
            optionalBook = bookRepository.findById(id);
            book = optionalBook.get();
        }catch (Exception e){
            throw new Exception("Invalid book Id");
        }

        bookRepository.delete(book);

        MessageResponseDto messageResponseDto = new MessageResponseDto();
        messageResponseDto.setMessage("Book removed from the database");

        return messageResponseDto;
    }

    public List<BookResponseDto> getBooksByAuthor(int id) throws AuthorNotFoundException {
        List<Book> books;
        Optional<Author> optionalAuthor;
        Author author;
        try{
            optionalAuthor = authorRepository.findById(id);
            author = optionalAuthor.get();
        }catch (Exception e){
            throw new AuthorNotFoundException("Invalid Author Id");
        }
        books = bookRepository.findByAuthorId(id);

        List<BookResponseDto> bookResponseDtos = new ArrayList<>();
        for(Book book : books){
            bookResponseDtos.add(BookConvertor.BookToBookResponseDto(book));
        }

        return bookResponseDtos;
    }

//    public MessageResponseDto checkISBNValidation(int id) {
//    }
}
