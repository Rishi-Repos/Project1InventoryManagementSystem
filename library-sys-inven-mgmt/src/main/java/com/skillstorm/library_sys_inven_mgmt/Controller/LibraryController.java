package com.skillstorm.library_sys_inven_mgmt.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.library_sys_inven_mgmt.Dto.LibraryDto;
import com.skillstorm.library_sys_inven_mgmt.Model.Library;
import com.skillstorm.library_sys_inven_mgmt.Service.LibraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("libraries")
@CrossOrigin("http://127.0.0.1:5500")
public class LibraryController {
    
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @GetMapping()
    public ResponseEntity<List<Library>> getAllLibraries() {
        try {
            return new ResponseEntity<>(libraryService.findAllLibraries(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("dtos")
    public ResponseEntity<List<LibraryDto>> getAllLibraryDtos() {
        try {
            return new ResponseEntity<>(libraryService.findAllLibraryDtos(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping()
    public ResponseEntity<Library> saveLibrary(@RequestBody Library library){
        return new ResponseEntity<>(libraryService.saveLibrary(library), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Library> updateLibrary(@RequestBody Library library){
        return new ResponseEntity<>(libraryService.saveLibrary(library), HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteLibrary(@RequestBody Library library){
        libraryService.deleteLibrary(library);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
