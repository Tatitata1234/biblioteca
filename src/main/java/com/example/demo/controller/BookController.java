package com.example.demo.controller;

import com.example.demo.controller.response.BookResponse;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public List<BookResponse> listAllBooks(){
        return bookService.listAllBooks();
    }

    @GetMapping("/{bookId}")
    public BookResponse findById(@PathVariable(value = "bookId") Long bookId){
        return bookService.findById(bookId);
    }

    @GetMapping("/titulo/{nome}")
    public BookResponse findByNome(@PathVariable(value = "nome") String nome){
        return bookService.findByNome(nome);
    }

    @GetMapping("/titulos/{nome}")
    public List<BookResponse> findByNomeLike(@PathVariable(value = "nome") String nome){
        return bookService.findByNomeLike(nome);
    }
}
