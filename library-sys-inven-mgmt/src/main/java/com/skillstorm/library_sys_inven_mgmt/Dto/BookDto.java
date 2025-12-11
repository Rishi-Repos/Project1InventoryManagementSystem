package com.skillstorm.library_sys_inven_mgmt.Dto;

import com.skillstorm.library_sys_inven_mgmt.Model.Book;
import com.skillstorm.library_sys_inven_mgmt.Model.Title;

public record BookDto(
    String id,
    Title title,
    LibraryDto libraryDto,
    String status
) {
    public static BookDto convertToDto(Book book){
        return new BookDto(book.getId(), book.getTitle(), LibraryDto.convertToDto(book.getLibrary()), book.getStatus());
    }

    public static Book convertToBook(BookDto bookDto){
        return new Book(bookDto.id, bookDto.title, LibraryDto.convertToLibrary(bookDto.libraryDto), bookDto.status);
    }
}
