package com.example.demo.repository.impl;

import com.example.demo.db.PersonDB;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PersionRepositoryImpl implements PersonRepository {
    @Override
    public List<Person> getAllPerson() {
        return PersonDB.people;
    }

    @Override
    public Person getPersonById(String id) {
        return PersonDB.people.stream()
                .filter(p -> p.getId().toLowerCase().contains(id.toLowerCase()))  // Lọc person theo id
                .findFirst()  // Lấy person đầu tiên thỏa mãn điều kiện
                .orElse(null);  // Nếu không tìm thấy, trả về null
    }
}
