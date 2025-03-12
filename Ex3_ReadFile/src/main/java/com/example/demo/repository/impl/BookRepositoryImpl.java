package com.example.demo.repository.impl;

import com.example.demo.db.BookDB;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> findAll() { // select * from books
        return BookDB.books;
    }

    @Override
    public Book findById(String id) { // select * from books where id=?
        for(Book book : BookDB.books){
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> sortBookByYear() {
        BookDB.books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getYear() - o1.getYear();
            }
        });
        return BookDB.books;
    }

    @Override
    public List<Book> getBookByKeyword(String keyword) {
        List<Book> rs = new ArrayList<>();
         for (Book book: BookDB.books){
            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                rs.add(book);
            }
        }
         return rs;
    }
}
