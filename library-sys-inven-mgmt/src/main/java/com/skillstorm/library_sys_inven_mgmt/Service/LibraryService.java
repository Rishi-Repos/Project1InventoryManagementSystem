package com.skillstorm.library_sys_inven_mgmt.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.skillstorm.library_sys_inven_mgmt.Dto.LibraryDto;
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

    public Library findLibraryById(int id){
        Optional<Library> optLib = libraryRepository.findById(id);
        if(optLib.isPresent()){
            return optLib.get();
        }
        else{
            throw new 
        }
        
    }

    //List all persisted libraries
    public List<Library> findAllLibraries(){
        return libraryRepository.findAll();
    }

    //List all persisted libraries without titles
    public List<LibraryDto> findAllLibraryDtos(){
        return libraryRepository.findAll().stream().map(LibraryDto::convertToDto).collect(Collectors.toList());
    }

    public void deleteLibrary(Library library){
        libraryRepository.delete(library);
    }

}
