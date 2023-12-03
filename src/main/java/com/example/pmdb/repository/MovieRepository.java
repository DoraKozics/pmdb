package com.example.pmdb.repository;

import com.example.pmdb.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m where m.title LIKE :title")
    Movie findByTitleIgnoreCase(@Param("title") String title);
}
