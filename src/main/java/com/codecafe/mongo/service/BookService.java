package com.codecafe.mongo.service;

import com.codecafe.mongo.entity.Book;
import com.codecafe.mongo.repository.BookRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void insertDummyBooks(int count) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Book book = new Book();
            book.setTitle("Book " + i);
            book.setAuthor("Author " + i);
            books.add(book);
        }
        bookRepository.saveAll(books);
        System.out.println("Books inserted");
    }

    public void singleUpdate() {
        List<Book> books = bookRepository.findAll();
        long startTime = System.currentTimeMillis();
        for (Book book : books) {
            UpdateResult updateResult = bookRepository.updateBook(book);
            if (updateResult.getMatchedCount() != 1 || updateResult.getModifiedCount() != 1) {
                System.out.println("Book " + book.getId() + " could not be updated");
            }
        }
        System.out.println("Single updates finished. Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public void bulkUpdate() {
        List<Book> books = bookRepository.findAll();
        long startTime = System.currentTimeMillis();
        bookRepository.updateBooks(books);
        System.out.println("Bulk update finished. Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public void deleteBooks() {
        List<Book> books = bookRepository.findAll();
        if (books != null && !books.isEmpty()) {
            bookRepository.deleteAll(books);
            System.out.println("Books deleted");
        }
    }

}