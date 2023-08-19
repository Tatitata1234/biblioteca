package com.example.demo.mapper;

import com.example.demo.controller.response.BookResponse;
import com.example.demo.domain.Book;

public class BookMapper {

    private BookMapper(){
    }
    public static BookResponse toResponse(Book entity) {
        return BookResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
