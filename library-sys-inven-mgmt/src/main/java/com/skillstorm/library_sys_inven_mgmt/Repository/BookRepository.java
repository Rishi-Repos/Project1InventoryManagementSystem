package com.skillstorm.library_sys_inven_mgmt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.library_sys_inven_mgmt.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{
    
}
