package com.olikassessment.libraryManagement.Repository;

import com.olikassessment.libraryManagement.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByIsRentedFalse();

    List<Book> findAllByIsRentedTrue();

    List<Book> findByAuthorId(int id);
}
