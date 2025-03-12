package com.example.demo.db;

import com.example.demo.model.Person;
import com.example.demo.utils.file.IFileReader;
import com.example.demo.utils.file.JsonFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@Slf4j
public class InitDB implements CommandLineRunner {

    private final IFileReader iFileReader;

    public InitDB(IFileReader iFileReader) {
        this.iFileReader = iFileReader;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Start init data: ");
        List<Person> people = iFileReader.readFile("people.json");
        log.info("Person size: {}", people.size());

        PersonDB.people = people;
    }
}
