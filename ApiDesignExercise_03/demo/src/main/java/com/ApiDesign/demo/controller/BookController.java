package com.ApiDesign.demo.controller;
import com.ApiDesign.demo.model.Book;
import com.ApiDesign.demo.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book added successfully");
        response.put("book id:", savedBook.getId().toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable @Positive(message = "ID must be positive") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateBook(@Positive(message = "ID must be positive") @PathVariable Long id, @Valid @RequestBody Book book)
    {
        Book updatedBook = bookService.updateBook(id,book);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book updated successfully");
        response.put("updated Book", updatedBook.toString());

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "1") int page) {

        return ResponseEntity.ok(bookService.getBooks(author, sort));
    }


}
