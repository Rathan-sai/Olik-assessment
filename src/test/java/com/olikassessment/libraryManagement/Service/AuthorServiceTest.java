//package com.olikassessment.libraryManagement.Service;
//
//public class AuthorServiceTest {
//}

package com.olikassessment.libraryManagement.Service;

import com.olikassessment.libraryManagement.DTO.RequestDTO.AuthorRequestDto;
import com.olikassessment.libraryManagement.DTO.ResponseDTO.AuthorResponseDto;
import com.olikassessment.libraryManagement.Exception.AuthorNotFoundException;
import com.olikassessment.libraryManagement.Model.Author;
import com.olikassessment.libraryManagement.Repository.AuthorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "Rathan", "Biography1"));
        authors.add(new Author(2, "Jeevan", "Biography2"));

        when(authorRepository.findAll()).thenReturn(authors);

        List<AuthorResponseDto> result = authorService.getAllAuthors();

        assertEquals(authors.size(), result.size());
    }

    @Test
    public void testGetAuthorById() throws AuthorNotFoundException {
        Author author = new Author(1, "John Doe", "Biography1");
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Optional<AuthorResponseDto> result = Optional.ofNullable(authorService.getAuthorById(1));

        assertEquals(author, result.get());
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author(1, "John Doe", "Biography1");
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorRequestDto authorRequestDto = new AuthorRequestDto();
        authorRequestDto.setBiography(author.getBiography());
        authorRequestDto.setName(author.getName());

        AuthorResponseDto savedAuthor = authorService.createAuthor(authorRequestDto);

        assertEquals(author, savedAuthor);
    }

    @Test
    public void testDeleteAuthor() throws AuthorNotFoundException {
        doNothing().when(authorRepository).deleteById(1);

        authorService.deleteAuthor(1);

        verify(authorRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteAuthorNotFound() {
        doThrow(new IllegalArgumentException()).when(authorRepository).deleteById(1);

        try {
            authorService.deleteAuthor(1);
        } catch (IllegalArgumentException | AuthorNotFoundException e) {
            // Expected exception
            return;
        }

        assertFalse(true);
    }
}
//

