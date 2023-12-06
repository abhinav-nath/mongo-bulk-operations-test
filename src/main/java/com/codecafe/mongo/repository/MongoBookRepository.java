package com.codecafe.mongo.repository;

import com.codecafe.mongo.entity.Book;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static org.springframework.data.mongodb.core.BulkOperations.BulkMode.UNORDERED;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.util.CollectionUtils.isEmpty;

@Repository
public class MongoBookRepository implements BookRepository {

    private final MongoTemplate mongoTemplate;

    public MongoBookRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void saveAll(List<Book> books) {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(UNORDERED, Book.class);

        for (Book book : books) {
            bulkOperations.insert(book);
        }

        bulkOperations.execute();
    }

    @Override
    public List<Book> findAll() {
        List<Book> enrichedOrders = mongoTemplate.findAll(Book.class);
        if (isEmpty(enrichedOrders)) {
            return Collections.emptyList();
        }
        return enrichedOrders;
    }

    @Override
    public UpdateResult updateBook(Book book) {
        Query query = new Query(where("_id").is(book.getId()));
        Update update = new Update().set("title", book.getTitle() + " - Updated");
        return mongoTemplate.updateFirst(query, update, Book.class);
    }

    @Override
    public void updateBooks(List<Book> books) {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(UNORDERED, Book.class);

        for (Book book : books) {
            Query query = new Query(where("_id").is(book.getId()));

            bulkOperations.updateOne(query,
                    new Update()
                            .set("title", book.getTitle() + " - Updated in Bulk"));
        }

        BulkWriteResult bulkWriteResult = bulkOperations.execute();
        System.out.println("matchedCount: " + bulkWriteResult.getMatchedCount() + " modifiedCount: " + bulkWriteResult.getModifiedCount());
    }

    @Override
    public void deleteAll(List<Book> books) {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(UNORDERED, Book.class);

        for (Book book : books) {
            Query deleteQuery = Query.query(where("_id").is(book.getId()));
            bulkOperations.remove(deleteQuery);
        }

        BulkWriteResult bulkWriteResult = bulkOperations.execute();
        System.out.println("deletedCount: " + bulkWriteResult.getDeletedCount());
    }

}
