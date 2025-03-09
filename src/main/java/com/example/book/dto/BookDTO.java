package com.example.book.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private String type;
    private Date publicationDate;
    private Long authorId;

}