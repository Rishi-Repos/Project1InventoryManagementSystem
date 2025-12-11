package com.skillstorm.library_sys_inven_mgmt.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillstorm.library_sys_inven_mgmt.Model.Library;
import com.skillstorm.library_sys_inven_mgmt.Repository.LibraryRepository;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository){
        this.libraryRepository = libraryRepository;
    }

    //Persist a new library or update an existing one
    public Library saveLibrary(Library library){
        return libraryRepository.save(library);
    }

    //List all persisted libraries
    public List<Library> findAllLibraries(){
        return libraryRepository.findAll();
    }

    public void deleteLibrary(Library library){
        libraryRepository.delete(library);
    }

}
