package com.skillstorm.library_sys_inven_mgmt.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.skillstorm.library_sys_inven_mgmt.Dto.BookDto;
import com.skillstorm.library_sys_inven_mgmt.Model.Author;
import com.skillstorm.library_sys_inven_mgmt.Model.Book;
import com.skillstorm.library_sys_inven_mgmt.Model.Library;
import com.skillstorm.library_sys_inven_mgmt.Repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    /**
     * a book's id is not auto-generated but mimics a real-life library system!
     */
    
    public Book addBook(Book book){

        //this book parameter does not have an id yet! 
        //It needs to be assigned before being persisted
        return bookRepository.save(assignBookId(book));

    }

    public Book updateBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public List<BookDto> findAllBooksInLibrary(Library library){
        //convert list of books to list of bookDto's
        return bookRepository.findByLibrary(library).stream().map(BookDto::convertToDto).collect(Collectors.toList());
    }
    
    //assign id before persisting to db
    public Book assignBookId(Book book){
        book.setId(
            book.getTitle().getGenre().substring(0,3)
            + "-" + concatLastNames(book.getTitle().getAuthors())
            + "-" + book.getTitle().getYearPublished()

            //append unique serial number for each copy of the title starting from 1
            + "-" + (bookRepository.findByTitle(book.getTitle()).size() + 1) 
        );
        return book;
    }

    //helper method for assignBookId
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
