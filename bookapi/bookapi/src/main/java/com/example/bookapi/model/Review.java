package com.example.bookapi.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reviewer;
    private String comment;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public void setId(Long id) {
        this.id = id;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public Book getBook() {
        return book;
    }

    public Review() {
    }

    public Review(Long id, Book book, int rating, String comment, String reviewer) {
        this.id = id;
        this.book = book;
        this.rating = rating;
        this.comment = comment;
        this.reviewer = reviewer;
    }
}
