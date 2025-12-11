package com.skillstorm.library_sys_inven_mgmt.Model;

import java.util.Set;

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
    @JsonIgnore
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
    @JsonIgnore
    private Set<Library> libraries;

    /**
     * Below are: 
     *  Constructors - no args, all args except id, all args 
     *  getters, setters, hashCode, equals, toString - all attributes
     *      - except there is no setId method!
     */ 

    public Title() {
    }

    public Title(String title, String genre, int yearPublished, Set<Book> books, Set<Author> authors,
            Set<Library> libraries) {
        this.title = title;
        this.genre = genre;
        this.yearPublished = yearPublished;
        this.books = books;
        this.authors = authors;
        this.libraries = libraries;
    }

    public Title(int id, String title, String genre, int yearPublished, Set<Book> books, Set<Author> authors,
            Set<Library> libraries) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.yearPublished = yearPublished;
        this.books = books;
        this.authors = authors;
        this.libraries = libraries;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(Set<Library> libraries) {
        this.libraries = libraries;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + yearPublished;
        result = prime * result + ((books == null) ? 0 : books.hashCode());
        result = prime * result + ((authors == null) ? 0 : authors.hashCode());
        result = prime * result + ((libraries == null) ? 0 : libraries.hashCode());
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
        Title other = (Title) obj;
        if (id != other.id)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (yearPublished != other.yearPublished)
            return false;
        if (books == null) {
            if (other.books != null)
                return false;
        } else if (!books.equals(other.books))
            return false;
        if (authors == null) {
            if (other.authors != null)
                return false;
        } else if (!authors.equals(other.authors))
            return false;
        if (libraries == null) {
            if (other.libraries != null)
                return false;
        } else if (!libraries.equals(other.libraries))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Title [id=" + id + ", title=" + title + ", genre=" + genre + ", yearPublished=" + yearPublished
                + ", books=" + books + ", authors=" + authors + ", libraries=" + libraries + "]";
    }
}
