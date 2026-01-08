package com.ApiDesign.demo.repository;


import com.ApiDesign.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookRepository {
    private final Map<Long, Book> bookStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Book save(Book book) {
        book.setId(idGenerator.getAndIncrement());
        bookStore.put(book.getId(), book);
        return book;
    }

    public Book updateBook(Long id,Book book) {
        Book existingBook = bookStore.get(id);

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        return existingBook;
    }

    public Book findById(Long id) {
        return bookStore.getOrDefault(id,null);
    }

    public List<Book> findAll() {
        return new ArrayList<>(bookStore.values());
    }

    public boolean deleteById(Long id) {
        return( bookStore.remove(id) != null);
    }


    public List<Book> findBooks(String author, String sort) {
        List<Book> books = new ArrayList<>(bookStore.values());

        // Filter by author (if provided)
        if (author != null && !author.isEmpty()) {
            books.removeIf(book ->
                    book.getAuthor() == null ||
                            !book.getAuthor().equalsIgnoreCase(author)
            );
        }

        // Sort by title (if provided)
        if (sort != null && !sort.isEmpty()) {
            boolean ascending = !"desc".equalsIgnoreCase(sort);
            books.sort((b1, b2) -> {
                if (ascending) {
                    return b1.getTitle().compareToIgnoreCase(b2.getTitle());
                } else {
                    return b2.getTitle().compareToIgnoreCase(b1.getTitle());
                }
            });
        }

        return books;
    }




}
