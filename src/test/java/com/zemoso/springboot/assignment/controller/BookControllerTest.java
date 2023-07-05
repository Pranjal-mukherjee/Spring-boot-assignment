package com.zemoso.springboot.assignment.controller;

import com.zemoso.springboot.assignment.dto.BookDTO;
import com.zemoso.springboot.assignment.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBook() {
        // Prepare test data
        BookDTO book1 = new BookDTO();
        book1.setId(1L);
        book1.setBookName("Book 1");

        BookDTO book2 = new BookDTO();
        book2.setId(2L);
        book2.setBookName("Book 2");

        List<BookDTO> expectedBooks = Arrays.asList(book1, book2);

        // Mock the bookService.getAllBooks() method
        when(bookService.getAllBooks()).thenReturn(expectedBooks);

        // Call the controller method
        ResponseEntity<List<BookDTO>> response = bookController.getAllBook();

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBooks, response.getBody());

        // Verify that bookService.getAllBooks() was called
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testGetBookById() {
        // Prepare test data
        Long bookId = 1L;
        BookDTO expectedBook = new BookDTO();
        expectedBook.setId(bookId);
        expectedBook.setBookName("Book 1");

        // Mock the bookService.getBookById() method
        when(bookService.getBookById(bookId)).thenReturn(expectedBook);

        // Call the controller method
        ResponseEntity<BookDTO> response = bookController.getBookById(bookId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBook, response.getBody());

        // Verify that bookService.getBookById() was called
        verify(bookService, times(1)).getBookById(bookId);
    }

    // Similarly, write tests for createBook, updateBook, and deleteBook methods


    @Test
   void testCreateBook() {
        // Prepare test data
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookName("Book 1");

        BookDTO createdBook = new BookDTO();
        createdBook.setId(1L);
        createdBook.setBookName("Book 1");

        // Mock the bookService.createBook() method
        when(bookService.createBook(bookDTO)).thenReturn(createdBook);

        // Call the controller method
        ResponseEntity<BookDTO> response = bookController.createBook(bookDTO);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdBook, response.getBody());

        // Verify that bookService.createBook() was called
        verify(bookService, times(1)).createBook(bookDTO);
    }

    @Test
    void testUpdateBook() {
        // Prepare test data
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setBookName("Updated Book 1");

        BookDTO updatedBook = new BookDTO();
        updatedBook.setId(1L);
        updatedBook.setBookName("Updated Book 1");

        // Mock the bookService.updateBook() method
        when(bookService.updateBook(bookDTO)).thenReturn(updatedBook);

        // Call the controller method
        ResponseEntity<BookDTO> response = bookController.updateBook(bookDTO);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBook, response.getBody());

        // Verify that bookService.updateBook() was called
        verify(bookService, times(1)).updateBook(bookDTO);
    }

    @Test
     void testDeleteBook() {
        // Prepare test data
        Long bookId = 1L;

        // Call the controller method
        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that bookService.deleteBook() was called
        verify(bookService, times(1)).deleteBook(bookId);
    }

}


