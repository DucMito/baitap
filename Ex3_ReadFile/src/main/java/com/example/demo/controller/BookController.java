package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * @Controller: Ap dung cho cac controller tra ve view (template). Ngoai ra cung co the tra ve JSON/XML giong @RestController (bo sung them thong)
 * @RestController = @Controller + @ResponseBody
 * @RestController // Danh dau len class -> Class nay se xu ly request va response (controller)
 * Class ResponseEntity<?>: Class dai dien cho 1 doi tuong HTTP Response, co the chua body, header, status code
 * */

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //1. Lấy danh sách book: GET - /books
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
         return ResponseEntity.ok(books);
    }



//     2. Lấy chi tiết book theo id: GET - /books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }


//    3. Viết API để trả về danh sachs book. Sắp xếp theo năm giảm dần
//    GET: /books/sortByYear
    @GetMapping("/sortByYear")
    public ResponseEntity<List<Book>> sortByYear(){
        List<Book> books = bookService.sortBookByYear();
        return ResponseEntity.ok(books);
    }

//    4. Viết API để tìm kiếm các cuốn sách mà trong title có chứa keyword, không phân biệt hoa thường
//    GET: /books/search/{keyword}
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Book>> getByKeyword(@PathVariable String keyword){
        List<Book> books = bookService.getBookByKeyword(keyword);
        return ResponseEntity.ok(books);
    }



    /*
    1. Viết API để trả về danh sachs book. Sắp xếp theo năm giảm dần
    GET: /books/sortByYear


    2. Viết API để tìm kiếm các cuốn sách mà trong title có chứa keyword, không phân biệt hoa thường
    GET: /books/search/{keyword}

    3. Viết API để tìm kiếm các cuốn sách có year được sản xuất từ năm A -> năm B
    GET: /books/startYear/{startYear}/endYear/{endYear}
    */


//    @GetMapping("/sortByYear")
//    public ResponseEntity<List<Book>> sortByYear() {
//        books.sort(new Comparator<Book>() {
//            @Override
//            public int compare(Book o1, Book o2) {
//                return o2.getYear() - o1.getYear();
//            }
//        });
//
//        return ResponseEntity.ok(books);
//    }

//    @GetMapping("/search/{keyword}")
//    public ResponseEntity<List<Book>> getBooksByKeyword(@PathVariable String keyword) {
//        List<Book> rs = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
//                rs.add(book);
//            }
//        }
//        return ResponseEntity.ok(rs);
//    }
//
//    @GetMapping("/startYear/{startYear}/endYear/{endYear}")
//    public ResponseEntity<List<Book>> getBooksByYear(@PathVariable int startYear, @PathVariable int endYear) {
//        List<Book> rs = new ArrayList<>();
//        for (Book book : books) {
//            if (book.getYear() >= startYear && book.getYear() <= endYear) {
//                rs.add(book);
//            }
//        }
//        return ResponseEntity.ok(rs);
//    }
}