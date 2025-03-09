package com.example.book.service;

import com.example.book.dto.BookDTO;
import com.example.book.dto.MessageDTO;
import com.example.book.entity.Book;
import com.example.book.exception.BookException;

import java.io.IOException;
import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks() throws BookException;

    BookDTO getBookById(Long id) throws BookException;

    BookDTO getBookByTitle(String title) throws BookException;

    Book updateBook(Long id, BookDTO bookDTO) throws BookException;

    String getBookISBN(String bibKeys) throws BookException;

    int calculateRating(Long bookId) throws BookException;

    List<MessageDTO> getMessagesFromFile(String filePath) throws IOException;

    String addDateToMessage() throws BookException;

    Book addBook(BookDTO bookDTO) throws BookException;

}
