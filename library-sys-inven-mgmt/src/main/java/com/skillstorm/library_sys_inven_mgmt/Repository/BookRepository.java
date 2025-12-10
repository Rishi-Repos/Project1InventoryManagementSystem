package com.skillstorm.library_sys_inven_mgmt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.library_sys_inven_mgmt.Model.Book;
import com.skillstorm.library_sys_inven_mgmt.Model.Title;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,String>{

    List<Book> findByTitle(Title title);
    
}
