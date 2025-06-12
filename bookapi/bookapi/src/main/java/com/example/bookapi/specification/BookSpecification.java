package com.example.bookapi.specification;

import com.example.bookapi.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, builder) ->
                author == null ? null : builder.like(builder.lower(root.get("author")), "%" + author.toLowerCase() + "%");
    }

    public static Specification<Book> hasGenre(String genre) {
        return (root, query, builder) ->
                genre == null ? null : builder.equal(builder.lower(root.get("genre")), genre.toLowerCase());
    }

    public static Specification<Book> hasPublishedYear(Integer publishedYear) {
        return (root, query, builder) ->
                publishedYear == null ? null : builder.equal(root.get("publishedYear"), publishedYear);
    }
}
