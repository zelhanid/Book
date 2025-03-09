package com.example.book.controller;

import com.example.book.dto.BookDTO;
import com.example.book.dto.MessageDTO;
import com.example.book.entity.Book;
import com.example.book.serviceImpl.AuthorServiceImpl;
import com.example.book.serviceImpl.BookServiceImpl;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/books")
public class BookController {
    public static String TOPIC_NAME = "testTopic";
    public static String KAFKA_BROKER_SERVER = "localhost:9092";

    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public BookController(BookServiceImpl bookService, AuthorServiceImpl authorService, KafkaTemplate<String, String> kafkaTemplate) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/title")
    public ResponseEntity<BookDTO> getBookByTitle(@RequestParam String name) {
        BookDTO bookData = bookService.getBookByTitle(name);
        return ResponseEntity.ok(bookData);

    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book createdBook = bookService.addBook(bookDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }


    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    @GetMapping("/authors")
    public Map<Long, String> getAuthorsByBooks(@RequestBody List<Long> booksIds) {
        Map<Long, String> authorsMap;
        authorsMap = authorService.getAuthorsByBooks(booksIds);
        return authorsMap;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/getBookISBN")
    public String getBookISBN(@RequestParam String bibkeys) {
        return bookService.getBookISBN(bibkeys);
    }

    @GetMapping("/rate")
    public int calculateRating(@RequestParam Long bookId) {
        int rating;
        rating = bookService.calculateRating(bookId);
        return rating;
    }

    @GetMapping("/sendMessage")
    public void sendAuthorKafkaProducer() throws IOException {
        List<MessageDTO> messagesList;
        messagesList = bookService.getMessagesFromFile("src/main/resources/messages.json");
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_SERVER);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "client-producer-1");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        Random random = new Random();
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() ->
        {
            for (MessageDTO messageToBeSent : messagesList) {
                String key = String.valueOf(random.nextInt(1000));
                String value = (messageToBeSent.getMessage() + bookService.addDateToMessage());
                kafkaProducer.send(new ProducerRecord<>(TOPIC_NAME, key, value), (metadata, exception) -> {
                    System.out.println("Sending message => key " + key + " Value => " + value
                            + " =>" + metadata.offset());
                });
            }
        }, 1000, 100, TimeUnit.MICROSECONDS);
    }
}
