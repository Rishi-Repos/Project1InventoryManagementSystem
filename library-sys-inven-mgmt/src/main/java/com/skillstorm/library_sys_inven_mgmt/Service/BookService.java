package com.skillstorm.library_sys_inven_mgmt.Service;

import org.springframework.stereotype.Service;

import com.skillstorm.library_sys_inven_mgmt.Repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
}
