package com.codecafe.mongo;

import com.codecafe.mongo.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoBulkOperationsTestApplication implements CommandLineRunner {

    private final BookService bookService;

    public MongoBulkOperationsTestApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoBulkOperationsTestApplication.class, args);
    }

    @Override
    public void run(String... args) {
        bookService.deleteBooks();
        bookService.insertDummyBooks(10000);
    }

}