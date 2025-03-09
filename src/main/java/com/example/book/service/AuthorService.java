package com.example.book.service;

import java.util.List;
import java.util.Map;


public interface AuthorService {

    Map<Long, String> getAuthorsByBooks(List<Long> booksIds);
}
