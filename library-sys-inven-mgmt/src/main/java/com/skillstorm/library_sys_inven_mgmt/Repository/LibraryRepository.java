package com.skillstorm.library_sys_inven_mgmt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.library_sys_inven_mgmt.Model.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Integer>{
    
}
