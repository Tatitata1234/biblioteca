package com.example.demo.factory;

import com.example.demo.domain.Book;

public class BookFactory {

    private static Book.BookBuilder getBookBuilder(){
        return Book.builder().name("Teste");
    }
    public static Book getNormalBook(Long id){
        return getBookBuilder().id(id).build();
    }
}
