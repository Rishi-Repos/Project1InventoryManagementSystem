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
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class Author {
    
    /**
     * Relationships:
     *  ManyToMany - Author (a title can have multiple authors,
     *                          an author can author multiple titles)
     */

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "title_author",
        joinColumns=
            @JoinColumn(name="author_id"),
        inverseJoinColumns=
            @JoinColumn(name="title_id")
    )
    private Set<Title> titles;
}
