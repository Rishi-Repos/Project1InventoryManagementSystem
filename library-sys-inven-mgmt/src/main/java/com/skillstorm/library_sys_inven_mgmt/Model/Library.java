package com.skillstorm.library_sys_inven_mgmt.Model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Set<Title> titles;

    /* @OneToMany(targetEntity = Book.class, mappedBy = "library")
    @JsonIgnore
    private Set<Book> books; */

    /**
     * Below are: 
     *  Constructors - no args, all args except id, all args 
     *  getters, setters, hashCode, equals, toString - all attributes
     *      - except there is no setId method!
     */
    
    public Library() {
    }

    public Library(String name, String location, int maxCap, Set<Title> titles) {
        this.name = name;
        this.location = location;
        this.maxCap = maxCap;
        this.titles = titles;
        /* this.books = books; */
    }

    public Library(int id, String name, String location, int maxCap, Set<Title> titles) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.maxCap = maxCap;
        this.titles = titles;
        /* this.books = books; */
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
    }

    public Set<Title> getTitles() {
        return titles;
    }

    public void setTitles(Set<Title> titles) {
        this.titles = titles;
    }

    /* public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    } */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + maxCap;
        result = prime * result + ((titles == null) ? 0 : titles.hashCode());
        /* result = prime * result + ((books == null) ? 0 : books.hashCode()); */
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Library other = (Library) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (maxCap != other.maxCap)
            return false;
        if (titles == null) {
            if (other.titles != null)
                return false;
        } else if (!titles.equals(other.titles))
            return false;
        /* if (books == null) {
            if (other.books != null)
                return false;
        } else if (!books.equals(other.books))
            return false; */
        return true;
    }

    @Override
    public String toString() {
        return "Library [id=" + id + ", name=" + name + ", location=" + location + ", maxCap=" + maxCap + ", titles="
                + titles + /* ", books=" + books + */ "]";
    }
}
