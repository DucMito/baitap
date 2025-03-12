package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(String id);

    List<Book> sortBookByYear();

    List<Book> getBookByKeyword(String keyword);
}
