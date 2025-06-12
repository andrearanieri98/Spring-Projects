package com.example.bookapi.config;

import com.example.bookapi.model.Book;
import com.example.bookapi.model.Review;
import com.example.bookapi.repository.BookRepository;
import com.example.bookapi.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class ReviewDataInitializer {

    private final Random random = new Random();

    @Bean
    CommandLineRunner initReviews(BookRepository bookRepository, ReviewRepository reviewRepository) {
        return args -> {
            if (reviewRepository.count() == 0) {
                List<Book> allBooks = bookRepository.findAll();
                List<Review> reviews = new ArrayList<>();

                for (Book book : allBooks) {
                    int reviewCount = 1 + random.nextInt(3); // 1 to 3 reviews
                    for (int i = 1; i <= reviewCount; i++) {
                        String reviewer = "User" + (random.nextInt(900) + 100);
                        String comment = generateComment(book.getTitle());
                        int rating = 3 + random.nextInt(3); // rating from 3 to 5
                        reviews.add(new Review(null, book ,rating , comment , reviewer));
                    }
                }

                reviewRepository.saveAll(reviews);
                System.out.println( reviews.size() + " recensioni generate e salvate.");
            }
        };
    }

    private String generateComment(String bookTitle) {
        String[] templates = {
                "Un capolavoro! Mi è piaciuto tantissimo \"%s\".",
                "\"%s\" è stato coinvolgente dall'inizio alla fine.",
                "Consiglio a tutti di leggere \"%s\".",
                "Un po’ lento all’inizio, ma \"%s\" migliora molto.",
                "\"%s\" ha una scrittura davvero affascinante.",
                "Mi aspettavo di più da \"%s\", ma comunque valido.",
                "\"%s\" è diventato uno dei miei preferiti.",
                "Letto tutto d’un fiato: \"%s\"."
        };
        return String.format(templates[random.nextInt(templates.length)], bookTitle);
    }
}
