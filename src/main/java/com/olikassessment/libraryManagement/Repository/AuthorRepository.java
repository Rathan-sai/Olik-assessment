package com.olikassessment.libraryManagement.Repository;

import com.olikassessment.libraryManagement.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
