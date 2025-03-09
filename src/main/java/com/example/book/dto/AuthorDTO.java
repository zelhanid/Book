package com.example.book.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String name;
    private String age;
    private double followers;
}
