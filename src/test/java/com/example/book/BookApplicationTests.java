
package com.example.book;

import com.example.book.entity.Author;
import com.example.book.entity.Book;
import com.example.book.repository.AuthorRepository;
import com.example.book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = new Author();
        testAuthor.setName("John Doe");
        testAuthor.setAge("35");
        testAuthor.setFollowers(100909L);
        authorRepository.save(testAuthor);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("Java Programming Basics");
        book.setType("Programming");
        book.setAuthor(testAuthor);

        Book savedBook = bookRepository.save(book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isGreaterThan(0);
        assertThat(savedBook.getTitle()).isEqualTo("Java Programming Basics");
        assertThat(savedBook.getAuthor()).isEqualTo(testAuthor);
    }

    @Test
    void testFindBookById() {
        Book book = new Book();
        book.setTitle("Spring Boot Essentials");
        book.setType("Programming");
        book.setAuthor(testAuthor);
        Book savedBook = bookRepository.save(book);

        Book foundBook = bookRepository.findById(savedBook.getId()).orElse(null);

        assertThat(foundBook).isNotNull();
        assertThat(foundBook.getId()).isEqualTo(savedBook.getId());
        assertThat(foundBook.getTitle()).isEqualTo("Spring Boot Essentials");
    }

    @Test
    void testUpdateBook() {
        Book book = new Book();
        book.setTitle("Learn Hibernate");
        book.setType("Programming");
        book.setAuthor(testAuthor);
        Book savedBook = bookRepository.save(book);

        savedBook.setTitle("Learn Advanced Hibernate");
        Book updatedBook = bookRepository.save(savedBook);

        assertThat(updatedBook.getTitle()).isEqualTo("Learn Advanced Hibernate");
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setTitle("Mastering Spring");
        book.setType("Programming");
        book.setAuthor(testAuthor);
        Book savedBook = bookRepository.save(book);

        bookRepository.delete(savedBook);

        Book deletedBook = bookRepository.findById(savedBook.getId()).orElse(null);
        assertThat(deletedBook).isNull();
    }

}

