package com.example.demo.repository;

import com.example.demo.model.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPerson();

    Person getPersonById(String id);
}
