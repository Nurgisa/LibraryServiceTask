package com.nurgisa.libraryservice.model;


import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import org.hibernate.envers.Audited;


@Entity
@Table(name = "books")
@Audited
public class Book extends BaseEntity {

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;


    @Column(name = "date_published")
    private Date datePublished;

    @Column(name="genre")
    private String genre;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    private Author author;

    public void setAuthor(Author author) {
        this.author = author;
    }

}

