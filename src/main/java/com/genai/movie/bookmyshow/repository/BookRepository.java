package com.genai.movie.bookmyshow.repository;

import com.genai.movie.bookmyshow.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}

