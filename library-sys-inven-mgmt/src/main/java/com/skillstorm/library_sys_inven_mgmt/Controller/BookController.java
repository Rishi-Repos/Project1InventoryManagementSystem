package com.skillstorm.library_sys_inven_mgmt.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.library_sys_inven_mgmt.Dto.BookDto;
import com.skillstorm.library_sys_inven_mgmt.Dto.LibraryDto;
import com.skillstorm.library_sys_inven_mgmt.Exception.LibraryNotFoundException;
import com.skillstorm.library_sys_inven_mgmt.Model.Book;
import com.skillstorm.library_sys_inven_mgmt.Model.Library;
import com.skillstorm.library_sys_inven_mgmt.Service.BookService;
import com.skillstorm.library_sys_inven_mgmt.Service.LibraryService;

@RestController
@RequestMapping("books")
@CrossOrigin("http://127.0.0.1:5500")
public class BookController {
    
    private final BookService bookService;
    private final LibraryService libraryService;
    
    public BookController(BookService bookService, LibraryService libraryService){
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @GetMapping("library/{id}")
    public ResponseEntity<List<BookDto>> findAllBooksInLibrary(@PathVariable("id") int libraryId){
        try {
            return new ResponseEntity<>(bookService.findAllBooksInLibrary(libraryService.findLibraryById(libraryId)), HttpStatus.OK);
        } catch(LibraryNotFoundException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
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
