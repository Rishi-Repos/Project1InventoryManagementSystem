package com.skillstorm.library_sys_inven_mgmt.Service;

import org.springframework.stereotype.Service;

import com.skillstorm.library_sys_inven_mgmt.Repository.LibraryRepository;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository){
        this.libraryRepository = libraryRepository;
    }
}
