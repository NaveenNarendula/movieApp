package com.genai.movie.bookmyshow.repository;

import com.genai.movie.bookmyshow.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
