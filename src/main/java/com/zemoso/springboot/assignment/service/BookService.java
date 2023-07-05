package com.zemoso.springboot.assignment.service;

import com.zemoso.springboot.assignment.dto.BookDTO;
import com.zemoso.springboot.assignment.entity.Book;
import com.zemoso.springboot.assignment.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToDto)
                .toList();
        //.stream(): The .stream() method is used to convert the List<Book> into a stream of elements
        //.map(this::convertToDto) is used to convert each Book object to its corresponding
        // BookDTO object by invoking the convertToDto method.
        //.collect(Collectors.toList()) is used to collect the stream elements into a new List<BookDTO>.
        // It takes a Collector parameter,
        // which specifies the type of collection to be created and how the elements should be accumulated.

        //OVERALL FLOW


        //.stream Retrieves a list of Book objects from the BookRepository using
        // BookRepository.findAll().
        //Converts each Book object to its corresponding BookDTO object
        // using .map(this::convertToDto).
        //Collects the resulting BookDTO objects into a new
        // List<BookDTO> using .collect(Collectors.toList()).
        //Finally, returns the list of BookDTO objects.
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id " + id));

        return convertToDto(book);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);

        //In summary, the createBook method takes a BookDTO object, converts it to a Book
        // entity, saves the entity using the BookRepository
        // , and then converts the saved entity back to a BookDTO object before returning it.
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new NoSuchElementException("Book not found with id " + bookDTO.getId()));

        existingBook.setBookName(bookDTO.getBookName());
        existingBook.setBookAuthor(bookDTO.getBookAuthor());
        existingBook.setBookPrice(bookDTO.getBookPrice());
        Book updatedBook = bookRepository.save(existingBook);
        return convertToDto(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookDTO convertToDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setBookName(book.getBookName());
        bookDTO.setBookAuthor(book.getBookAuthor());
        bookDTO.setBookPrice(book.getBookPrice());
        return bookDTO;
    }
    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setBookName(bookDTO.getBookName());
        book.setBookAuthor(bookDTO.getBookAuthor());
        book.setBookPrice(bookDTO.getBookPrice());
        return book;
    }
}