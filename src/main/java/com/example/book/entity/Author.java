package com.example.book.entity;

import lombok.*;
import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Setter
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String age;
    private double followers;

}
