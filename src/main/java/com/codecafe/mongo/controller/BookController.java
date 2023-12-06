package com.codecafe.mongo.controller;

import com.codecafe.mongo.service.BookService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public void insertDummyBooks() {
        bookService.insertDummyBooks(10000);
    }

    @PutMapping("/single")
    public void performSingleUpdate() {
        bookService.singleUpdate();
    }

    @PutMapping("/bulk")
    public void performBulkUpdate() {
        bookService.bulkUpdate();
    }

    @DeleteMapping()
    public void deleteBooks() {
        bookService.deleteBooks();
    }

}
