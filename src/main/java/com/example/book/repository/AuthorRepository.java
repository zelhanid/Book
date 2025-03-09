package com.example.book.repository;

import com.example.book.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select distinct a from Book b inner join  Author a on b.author.id = a.id and b.id = :id")
    Author findAuthorByBookId(@Param("id") Long id);
}
