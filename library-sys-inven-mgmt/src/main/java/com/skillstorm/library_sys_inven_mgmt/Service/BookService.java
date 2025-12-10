package com.skillstorm.library_sys_inven_mgmt.Service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.skillstorm.library_sys_inven_mgmt.Model.Author;
import com.skillstorm.library_sys_inven_mgmt.Model.Book;
import com.skillstorm.library_sys_inven_mgmt.Repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //a book's id is not auto-generated but mimics a real-life library system!
    
    public Book assignBookId(Book book){
        book.setId(
            book.getTitle().getGenre().substring(0,3)
            + concatLastNames(book.getTitle().getAuthors())
            + book.getTitle().getYearPublished()

            //add unique serial number for each copy of the title starting from 1
            + (bookRepository.findByTitle(book.getTitle()).size() + 1) 
        );
        return book;
    }

    public String concatLastNames(Set<Author> authors){
        String lastNames = "";
        boolean manyAuthors = false;
        if(authors.size() > 1) manyAuthors = true;

        for (Author author : authors) {
            //if there are many authors, only take first letter of name
            if(manyAuthors){
                lastNames += author.getLastName().substring(0, 1);
            }
            else{
                lastNames += author.getLastName().substring(0, 3);
            }
        }
        return lastNames;
    }
}
