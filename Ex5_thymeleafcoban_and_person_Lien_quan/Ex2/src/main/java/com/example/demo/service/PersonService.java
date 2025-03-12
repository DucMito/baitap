package com.example.demo.service;

import com.example.demo.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPerson();

    Person getPersonById(String id);
}
