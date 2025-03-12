package com.example.demo.model;

import lombok.*;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private String id;
    private String fullName;
    private String job;
    private String gender;
    private String city;
    private double salary;
    private String birthday;
}
