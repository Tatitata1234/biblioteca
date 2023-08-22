package com.example.demo.service;

import com.example.demo.controller.response.BookResponse;
import com.example.demo.domain.Book;
import com.example.demo.factory.BookFactory;
import com.example.demo.repository.BookRepository;
import com.example.demo.util.GeneratedNumbers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @InjectMocks
    private BookService tested;

    @Mock
    private BookRepository bookRepository;

    @Test
    void shouldListAllBooks(){
        List<Book> books = new ArrayList<>();
        books.add(BookFactory.getNormalBook(GeneratedNumbers.getLong()));

        when(bookRepository.findAll()).thenReturn(books);

        List<BookResponse> responses = tested.listAllBooks();

        assertEquals(books.size(),responses.size());
        assertEquals(books.get(0).getId(),responses.get(0).getId());
        assertEquals(books.get(0).getName(),responses.get(0).getName());

    }

    @Test
    void shouldFindBookWhenReceiveValidId(){
        Long id = GeneratedNumbers.getLong();
        Book book = BookFactory.getNormalBook(id);

        when(bookRepository.findByIdAndActiveIsTrue(id)).thenReturn(Optional.ofNullable(book));

        BookResponse response = tested.findById(id);

        assertEquals(book.getId(),response.getId());
        assertEquals(book.getName(),response.getName());
    }

    @Test
    void shouldThrowExceptionWhenIdDoesntExist(){
        Long id = GeneratedNumbers.getLong();

        when(bookRepository.findByIdAndActiveIsTrue(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () ->tested.findById(id));
    }

    @Test
    void shouldFindBookWhenReceiveValidName(){
        Long id = GeneratedNumbers.getLong();
        Book book = BookFactory.getNormalBook(id);
        String name = book.getName();

        when(bookRepository.findByNameAndActiveIsTrue(name)).thenReturn(book);

        BookResponse response = tested.findByName(name);

        assertEquals(book.getId(),response.getId());
        assertEquals(book.getName(),response.getName());
    }

    @Test
    void shouldThrowExceptionWhenNameDoesntExist(){
        String name = "name not exists";

        when(bookRepository.findByNameAndActiveIsTrue(name)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () ->tested.findByName(name));
    }

    @Test
    void shouldFindBookWhenReceivePartOfName(){
        Long id = GeneratedNumbers.getLong();
        List<Book> books = new ArrayList<>();
        books.add(BookFactory.getNormalBook(id));
        String subName = books.get(0).getName().substring(0,1);


        when(bookRepository.findAllByNameContainingIgnoreCaseAndActiveIsTrue(subName)).thenReturn(books);

        List<BookResponse> responses = tested.findByNameLike(subName);

        assertEquals(books.size(),responses.size());
        assertEquals(books.get(0).getId(),responses.get(0).getId());
        assertEquals(books.get(0).getName(),responses.get(0).getName());
    }

    @Test
    void ThrowExceptionWhenSubNameDoesntExist(){
        List<Book> books = new ArrayList<>();
        String subName = "subname not exists";


        when(bookRepository.findAllByNameContainingIgnoreCaseAndActiveIsTrue(subName)).thenReturn(books);

        assertThrows(ResponseStatusException.class, () ->tested.findByNameLike(subName));
    }
}
