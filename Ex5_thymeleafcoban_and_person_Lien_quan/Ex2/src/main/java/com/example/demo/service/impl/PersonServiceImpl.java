package com.example.demo.service.impl;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Override
    public List<Person> getAllPerson() {
        return personRepository.getAllPerson();
    }

    @Override
    public Person getPersonById(String id) {
        return personRepository.getPersonById(id);
    }
}
