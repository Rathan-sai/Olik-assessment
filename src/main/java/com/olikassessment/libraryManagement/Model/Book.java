package com.olikassessment.libraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String title;

    private boolean isRented;

    @ManyToOne
    @JsonIgnore
    Author author;

    @Column(unique = true, nullable = false)
    private String isbn;

    private String publicationYear;

    @ManyToOne(optional = true)
    @JoinColumn
    @JsonIgnore
    Rental rental;
}
