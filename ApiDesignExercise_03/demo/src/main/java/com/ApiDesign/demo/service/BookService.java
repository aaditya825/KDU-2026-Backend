package com.ApiDesign.demo.service;

import com.ApiDesign.demo.exception.BookNotFoundException;
import com.ApiDesign.demo.model.Book;
import com.ApiDesign.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        boolean checkArgs =(( book.getAuthor()!= null) && (!book.getAuthor().isEmpty())&&
                ( book.getTitle()!= null) && ( !book.getTitle().isEmpty()));

        if(!checkArgs)
        {
            throw new IllegalArgumentException("Invalid Argument");
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, Book book) {

        Book isPresent = bookRepository.findById(id);
        if(isPresent == null) {
            throw new BookNotFoundException("Book not Found with Id: " + id);
        }
        return bookRepository.updateBook(id,book);
    }

    public void deleteBook(Long id) {
        boolean checkDelete = bookRepository.deleteById(id);
        if(!checkDelete) {
            throw new BookNotFoundException("Book not Found with Id: " + id);
        }else {
            return;
        }
    }

    public List<Book> getBooks(String author, String sort) {
        return  bookRepository.findBooks(author, sort);
    }



}
