package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> sortBookByYear() {
        return bookRepository.sortBookByYear();
    }

    @Override
    public List<Book> getBookByKeyword(String keyword) {
        return bookRepository.getBookByKeyword(keyword);
    }


}
