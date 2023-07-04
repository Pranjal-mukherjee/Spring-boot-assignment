package com.zemoso.springboot.assignment.service;

import com.zemoso.springboot.assignment.DTO.BookDTO;
import com.zemoso.springboot.assignment.entity.Book;
import com.zemoso.springboot.assignment.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        // Prepare test data
        List<Book> books = Arrays.asList(
                new Book(1L, "Book1", "Author1", "10"),
                new Book(2L, "Book2", "Author2", "15")
        );

        // Mock the bookRepository.findAll() method
        when(bookRepository.findAll()).thenReturn(books);

        // Call the service method
        List<BookDTO> bookDTOs = bookService.getAllBooks();

        // Verify the result
        assertEquals(books.size(), bookDTOs.size());
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            BookDTO bookDTO = bookDTOs.get(i);
            assertEquals(book.getId(), bookDTO.getId());
            assertEquals(book.getBookName(), bookDTO.getBookName());
            assertEquals(book.getBookAuthor(), bookDTO.getBookAuthor());
            assertEquals(book.getBookPrice(), bookDTO.getBookPrice());
        }

        // Verify that bookRepository.findAll() was called
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        // Prepare test data
        Long bookId = 1L;
        Book book = new Book(bookId, "Book1", "Author1", "16");

        // Mock the bookRepository.findById() method
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Call the service method
        BookDTO bookDTO = bookService.getBookById(bookId);

        // Verify the result
        assertNotNull(bookDTO);
        assertEquals(book.getId(), bookDTO.getId());
        assertEquals(book.getBookName(), bookDTO.getBookName());
        assertEquals(book.getBookAuthor(), bookDTO.getBookAuthor());
        assertEquals(book.getBookPrice(), bookDTO.getBookPrice());

        // Verify that bookRepository.findById() was called
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    public void testCreateBook() {
        // Prepare test data
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookName("Book1");
        bookDTO.setBookAuthor("Author1");
        bookDTO.setBookPrice("646");

        Book book = new Book();
        book.setId(1L);
        book.setBookName("Book1");
        book.setBookAuthor("Author1");
        book.setBookPrice("363");

        // Mock the bookRepository.save() method
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Call the service method
        BookDTO createdBookDTO = bookService.createBook(bookDTO);

        // Verify the result
        assertNotNull(createdBookDTO);
        assertEquals(book.getId(), createdBookDTO.getId());
        assertEquals(book.getBookName(), createdBookDTO.getBookName());
        assertEquals(book.getBookAuthor(), createdBookDTO.getBookAuthor());
        assertEquals(book.getBookPrice(), createdBookDTO.getBookPrice());

        // Verify that bookRepository.save() was called
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testUpdateBook() {
        // Prepare test data
        Long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookId);
        bookDTO.setBookName("UpdatedBook");
        bookDTO.setBookAuthor("UpdatedAuthor");
        bookDTO.setBookPrice("47");

        Book existingBook = new Book(bookId, "Book1", "Author1", "73");
        Book updatedBook = new Book(bookId, "UpdatedBook", "UpdatedAuthor", "37");

        // Mock the bookRepository.findById() and bookRepository.save() methods
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        // Call the service method
        BookDTO updatedBookDTO = bookService.updateBook(bookDTO);

        // Verify the result
        assertNotNull(updatedBookDTO);
        assertEquals(updatedBook.getId(), updatedBookDTO.getId());
        assertEquals(updatedBook.getBookName(), updatedBookDTO.getBookName());
        assertEquals(updatedBook.getBookAuthor(), updatedBookDTO.getBookAuthor());
        assertEquals(updatedBook.getBookPrice(), updatedBookDTO.getBookPrice());

        // Verify that bookRepository.findById() and bookRepository.save() were called
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testDeleteBook() {
        // Prepare test data
        Long bookId = 1L;

        // Call the service method
        bookService.deleteBook(bookId);

        // Verify that bookRepository.deleteById() was called
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
