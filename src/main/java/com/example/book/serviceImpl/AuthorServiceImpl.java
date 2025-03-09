package com.example.book.serviceImpl;

import com.example.book.repository.AuthorRepository;
import com.example.book.service.*;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Map<Long, String> getAuthorsByBooks(List<Long> booksIds) {
        Map<Long, String> authorsMap = new HashMap<>();
        for (Long bookId : booksIds) {
            authorsMap.put(bookId, authorRepository.findAuthorByBookId(bookId).getName());
        }
        return authorsMap;
    }
}
