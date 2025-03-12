package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
@ToString
@Component

public class Shop {
    // C1: field injection
//    @Autowired
//    private Book book;

//    C2: Constructor injection

        private Book book;

        public Shop(Book book) {
            this.book = book;
        }


}
