package com.example.pmdb.service;

import com.example.pmdb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieValidatorService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieValidatorService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean checkIfAlreadyExists(String title) {
        return movieRepository.findByTitleIgnoreCase(title.trim()) != null;
    }
}
