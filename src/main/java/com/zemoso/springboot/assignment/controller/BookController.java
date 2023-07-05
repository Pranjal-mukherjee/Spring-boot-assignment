package com.zemoso.springboot.assignment.controller;

import com.zemoso.springboot.assignment.dto.BookDTO;
import com.zemoso.springboot.assignment.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {


    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // add mapping for GET /Books
    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> getAllBook() {
        List<BookDTO> bookDTOS = bookService.getAllBooks();
        return ResponseEntity.ok(bookDTOS);

    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    // add mapping for POST /Books - add new Book
    @PostMapping("/")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        log.info(bookDTO.toString());
        BookDTO createBookDTo = bookService.createBook(bookDTO);
        return ResponseEntity.ok(createBookDTo);
    }

    // add mapping for UPDATE /Books/{BookId} - update existing Books
    @PutMapping("/")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        BookDTO updatedBookDTO = bookService.updateBook(bookDTO);
        return ResponseEntity.ok(updatedBookDTO);
    }

    // add mapping for DELETE /Books/{BookId} - delete Books by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
