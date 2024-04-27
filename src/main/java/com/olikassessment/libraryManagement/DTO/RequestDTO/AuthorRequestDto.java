package com.olikassessment.libraryManagement.DTO.RequestDTO;

import com.olikassessment.libraryManagement.Model.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRequestDto {
    private String name;
    private String Biography;
}
