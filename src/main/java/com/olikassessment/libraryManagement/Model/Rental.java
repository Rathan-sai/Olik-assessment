package com.olikassessment.libraryManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    int bookId;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
//    @JoinColumn(nullable = false)
    @JsonIgnore
    private List<Book> book;

    public List<Book> getBooks() {
        if (book == null) {
            book = new ArrayList<>();
        }
        return book;
    }
    @Column(name = "rental_name", nullable = true)
    private String rentalName;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
//    private Date rentalDate;
    private LocalDate rentalDate;

//    private Date returnDate;
    private LocalDate returnDate;
}
