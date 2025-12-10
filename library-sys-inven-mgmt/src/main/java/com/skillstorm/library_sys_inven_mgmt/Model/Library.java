package com.skillstorm.library_sys_inven_mgmt.Model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "library_info")
public class Library {
    
    /**
     * Relationships:
     *  OneToMany - Book (a library has many books,
     *                      a copy of a title, a book, is located in one library)
     *  ManyToMany - Title (a library has multiple titles, 
     *                          a title has copies in multiple libraries)
     */

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "library_name")
    private String name;

    @Column(name = "library_location")
    private String location;

    @Column(name = "max_capacity")
    private int maxCap;

    @ManyToMany
    @JoinTable(name = "book",
        joinColumns=
            @JoinColumn(name="library_id"),
        inverseJoinColumns=
            @JoinColumn(name="title_id")
    )

    @OneToMany(targetEntity = Book.class, mappedBy = "library")
    private Set<Book> books;

    
}
