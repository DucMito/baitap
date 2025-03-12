package com.example.demo.db;

import com.example.demo.model.Book;
import com.example.demo.utils.file.IFIleReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Slf4j
@Configuration


public class InitDB implements CommandLineRunner {

    private final IFIleReader ifIleReader;

    public InitDB(@Qualifier("JsonFileReader") IFIleReader ifIleReader) {
        this.ifIleReader = ifIleReader;
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("Start init data");
        List<Book> books = ifIleReader.readFile("books.json");
        log.info("Books size: {}", books.size());

        BookDB.books = books;
    }
}
