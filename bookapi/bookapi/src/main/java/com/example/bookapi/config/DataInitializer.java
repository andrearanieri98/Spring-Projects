package com.example.bookapi.config;

import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Book> books = List.of(
                        new Book(null, "1984", "George Orwell", "Dystopian", 1949),
                        new Book(null, "To Kill a Mockingbird", "Harper Lee", "Classic", 1960),
                        new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925),
                        new Book(null, "Moby-Dick", "Herman Melville", "Adventure", 1851),
                        new Book(null, "War and Peace", "Leo Tolstoy", "Historical", 1869),
                        new Book(null, "Pride and Prejudice", "Jane Austen", "Romance", 1813),
                        new Book(null, "The Catcher in the Rye", "J.D. Salinger", "Coming-of-age", 1951),
                        new Book(null, "Brave New World", "Aldous Huxley", "Science Fiction", 1932),
                        new Book(null, "Crime and Punishment", "Fyodor Dostoevsky", "Psychological", 1866),
                        new Book(null, "The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 1954),
                        new Book(null, "Jane Eyre", "Charlotte Brontë", "Gothic", 1847),
                        new Book(null, "Wuthering Heights", "Emily Brontë", "Gothic", 1847),
                        new Book(null, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937),
                        new Book(null, "The Divine Comedy", "Dante Alighieri", "Epic", 1320),
                        new Book(null, "Les Misérables", "Victor Hugo", "Historical", 1862),
                        new Book(null, "Anna Karenina", "Leo Tolstoy", "Tragedy", 1877),
                        new Book(null, "The Brothers Karamazov", "Fyodor Dostoevsky", "Philosophical", 1880),
                        new Book(null, "Frankenstein", "Mary Shelley", "Horror", 1818),
                        new Book(null, "Dracula", "Bram Stoker", "Horror", 1897),
                        new Book(null, "The Odyssey", "Homer", "Epic", -800)
                );
                repository.saveAll(books);
                System.out.println("✅ Database popolato con 20 libri.");
            }
        };
    }
}
