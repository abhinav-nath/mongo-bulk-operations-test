package com.codecafe.mongo.repository;

import com.codecafe.mongo.entity.Book;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

public interface BookRepository {

    void saveAll(List<Book> books);

    List<Book> findAll();

    UpdateResult updateBook(Book book);

    void updateBooks(List<Book> books);

    void deleteAll(List<Book> books);

}
