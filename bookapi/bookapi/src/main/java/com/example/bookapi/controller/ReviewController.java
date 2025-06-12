package com.example.bookapi.controller;

import com.example.bookapi.model.Book;
import com.example.bookapi.model.Review;
import com.example.bookapi.repository.BookRepository;
import com.example.bookapi.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/api/reviews")
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/api/books/{bookId}/reviews")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    @PostMapping("/api/books/{bookId}/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Long bookId, @RequestBody Review review) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        review.setBook(optionalBook.get());
        Review saved = reviewRepository.save(review);
        return ResponseEntity.created(URI.create("/api/books/" + bookId + "/reviews/" + saved.getId())).body(saved);
    }
}
