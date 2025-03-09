package com.example.book.serviceImpl;

import com.example.book.dto.BookDTO;
import com.example.book.dto.MessageDTO;
import com.example.book.entity.Author;
import com.example.book.entity.Book;
import com.example.book.mapper.BookMapper;
import com.example.book.repository.AuthorRepository;
import com.example.book.repository.BookRepository;
import com.example.book.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Calendar.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private static final String API_URL = "https://openlibrary.org/api/books";

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, RestTemplate restTemplate, ObjectMapper objectMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.authorRepository = authorRepository;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id).map(bookMapper::toDTO).orElse(null);
    }


    public BookDTO getBookByTitle(String title) {
        Book book = bookRepository.findBookByTitle(title);
        return bookMapper.toDTO(book);
    }

    public Book addBook(BookDTO bookRequest) {
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = bookMapper.toEntity(bookRequest);
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElse(null);
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        if (book != null) {
            book.setTitle(bookDTO.getTitle());
            book.setType(bookDTO.getType());
            book.setPublicationDate(bookDTO.getPublicationDate());
            book.setAuthor(author);

        }
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public String getBookISBN(String bibKeys) {
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .queryParam("bibkeys", bibKeys)
                .queryParam("format", "json")
                .toUriString();
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            return "Error fetching data: " + e.getStatusCode() + " " + e.getResponseBodyAsString();
        }
    }

    @Override
    public int calculateRating(Long bookId) {
        double rating = 0;
        Book book = bookRepository.findById(bookId).get();
        double authorFollowers = book.getAuthor().getFollowers();
        long yearsSincePublication = getDiffYears(book.getPublicationDate(), new Date());

        if (yearsSincePublication <= 1) {
            rating += 3;
        } else if (yearsSincePublication <= 5) {
            rating += 2;
        } else {
            rating += 1;
        }

        if (authorFollowers >= 1000000) {
            rating += 3;
        } else if (authorFollowers >= 100000) {
            rating += 2;
        } else if (authorFollowers >= 10000) {
            rating += 1;
        } else {
            rating += 0;
        }

        if (rating > 10) {
            rating = 10;
        }

        return (int) rating;
    }


    public List<MessageDTO> getMessagesFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        return objectMapper.readValue(file, new TypeReference<List<MessageDTO>>() {
        });
    }

    public String addDateToMessage() {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormatted = currentDate.format(formatter);
        return dateFormatted;
    }

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }


}
