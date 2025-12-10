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
@Table(name = "title")
public class Title { 
    
    /**
     * Relationships:
     *  OneToMany - Book (books in circulation by this title)
     *  ManyToMany - Author (a title can have multiple authors,
     *                          an author can author multiple titles)
     *  ManyToMany - Library (a library has multiple titles, 
     *                          a title in multiple libraries)
     */
    
    @Id
    @Column(name = "title_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String genre;

    @Column(name = "year_published")
    private int yearPublished;

    @OneToMany(targetEntity = Book.class, mappedBy = "title")
    private Set<Book> books;    

    @ManyToMany
    @JoinTable(name = "title_author",
        joinColumns=
            @JoinColumn(name="title_id"),
        inverseJoinColumns=
            @JoinColumn(name="author_id")
    )
    private Set<Author> authors;

    @ManyToMany
    @JoinTable(name = "book",
        joinColumns=
            @JoinColumn(name="title_id"),
        inverseJoinColumns=
            @JoinColumn(name="library_id")
    )
    private Set<Library> libraries;
}
