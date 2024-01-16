package com.project.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.project.demo.model.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.project.demo.model.Book;
import com.project.demo.model.BookStatus;
import com.project.demo.service.BookService;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        book = new Book("Brave New World", "Aldous Huxley", 1932, Genre.FICTION, BookStatus.AVAILABLE);
    }

    @Test
    void addBook() {
        when(bookService.addBook(any(Book.class))).thenReturn(book);

        ResponseEntity<Book> response = bookController.addBook(book);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

}

