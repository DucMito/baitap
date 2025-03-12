package com.example.demo.utils.file;

import com.example.demo.model.Book;

import java.util.List;

public interface IFIleReader {
    List<Book> readFile(String filePath);
}
