package com.example.demo;

import com.example.demo.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitBean {
    public InitBean() {
        System.out.println("This is bean");
    }

    @Bean
    public Book book1() {
        return Book.builder()
                .title("Lập trình")
                .year(2021)
                .id("1")
                .build();


    }
}
