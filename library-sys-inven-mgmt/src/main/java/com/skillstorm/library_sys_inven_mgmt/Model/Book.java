package com.skillstorm.library_sys_inven_mgmt.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    
    /**
     * A book, here, refers to a single copy of a title 
     *      which can only have one title and exist in one library at a time
     */

    /**
     * Relationships:
     *  ManyToOne - Title (many copies of a title in circulation)
     *  ManyToOne - Library (many copies of a title in a library)
     */
    
    @Id
    @Column(name = "book_id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @Column(name = "book_status")
    private String status;
}
