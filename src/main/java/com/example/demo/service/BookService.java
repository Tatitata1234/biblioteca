package com.example.demo.service;

import com.example.demo.controller.response.BookResponse;
import com.example.demo.domain.Book;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookResponse> listAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toResponse).toList();
    }

    public BookResponse findById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return BookMapper.toResponse(book.get());
    }

    public BookResponse findByName(String nome) {
        Book book = bookRepository.findByName(nome);
        if (Objects.isNull(book)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return BookMapper.toResponse(book);
    }

    public List<BookResponse> findByNameLike(String nome) {
        List<Book> books = bookRepository.findAllByNameContainingIgnoreCase(nome);
        if (books.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return books.stream().map(BookMapper::toResponse).toList();
    }
}
