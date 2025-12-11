package com.skillstorm.library_sys_inven_mgmt.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.library_sys_inven_mgmt.Dto.BookDto;
import com.skillstorm.library_sys_inven_mgmt.Dto.LibraryDto;
import com.skillstorm.library_sys_inven_mgmt.Model.Book;
import com.skillstorm.library_sys_inven_mgmt.Model.Library;
import com.skillstorm.library_sys_inven_mgmt.Service.BookService;

@RestController
@RequestMapping("books")
@CrossOrigin("http://127.0.0.1:5500")
public class BookController {
    
    private final BookService bookService;
    
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("library/{id}")
    public ResponseEntity<List<BookDto>> findAllBooksInLibrary(@RequestParam int libraryId){
        return new ResponseEntity<>(bookService.findAllBooksInLibrary(libraryDto), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteBook(@RequestBody Book book){
        bookService.deleteBook(book);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
