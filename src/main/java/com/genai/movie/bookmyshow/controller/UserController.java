package com.genai.movie.bookmyshow.controller;

import com.genai.movie.bookmyshow.model.User;
import com.genai.movie.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        User existingUser = userRepository.findByUsername(newUser.getUsername());
        if (existingUser != null) {
            // User already exists
            return ResponseEntity.badRequest().build();
        }
        User savedUser = userRepository.save(newUser);
        return ResponseEntity.ok(savedUser);
    }
    //get all registered users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
        User existingUser = userRepository.findByUsername(loginUser.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(loginUser.getPassword())) {
            // User exists and password is correct
            return ResponseEntity.ok(existingUser);
        }
        // User does not exist or password is incorrect
        return ResponseEntity.badRequest().build();
    }
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/popular-movies")
    public String getPopularMovies() {
        String apiKey = "your_api_key"; // replace with your actual API key
        String url = "https://api.themoviedb.org/movie/popular?api_key=" + apiKey + "&language=en-US&page=1";
        //String url = "https://api.themoviedb.org/movie";

        return restTemplate.getForObject(url, String.class);
    }
}
