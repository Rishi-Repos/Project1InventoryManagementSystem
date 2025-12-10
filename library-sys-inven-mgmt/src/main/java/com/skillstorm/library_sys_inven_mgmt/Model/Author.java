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

    /**
     * Below are: 
     *  Constructors - no args, all args except id, all args 
     *  getters, setters, hashCode, equals, toString - all attributes
     *      - except there is no setId method!
     */

    public Author() {
    }

    public Author(String firstName, String middleName, String lastName, Set<Title> titles) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.titles = titles;
    }

    public Author(int id, String firstName, String middleName, String lastName, Set<Title> titles) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.titles = titles;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Title> getTitles() {
        return titles;
    }

    public void setTitles(Set<Title> titles) {
        this.titles = titles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((titles == null) ? 0 : titles.hashCode());
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
        Author other = (Author) obj;
        if (id != other.id)
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (middleName == null) {
            if (other.middleName != null)
                return false;
        } else if (!middleName.equals(other.middleName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (titles == null) {
            if (other.titles != null)
                return false;
        } else if (!titles.equals(other.titles))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Author [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", titles=" + titles + "]";
    }    
}
