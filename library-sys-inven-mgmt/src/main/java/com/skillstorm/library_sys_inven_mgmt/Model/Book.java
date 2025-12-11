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
    

    /**
     * genre + author_last + year_published + a serial# 
     * = unique identifier for every single physical book
     *      assigned by service layer!
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

    /**
     * Below are: 
     *  Constructors - no args, all args except id, all args 
     *  getters, setters, hashCode, equals, toString - all attributes
     *      - there IS a setId method!
     */

    public Book() {
    }

    public Book(Title title, Library library, String status) {
        this.title = title;
        this.library = library;
        this.status = status;
    }

    public Book(String id, Title title, Library library, String status) {
        this.id = id;
        this.title = title;
        this.library = library;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((library == null) ? 0 : library.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        Book other = (Book) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (library == null) {
            if (other.library != null)
                return false;
        } else if (!library.equals(other.library))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", library=" + library + ", status=" + status + "]";
    }
}
