package org.challenges.ubots.service;

import java.util.List;
import org.challenges.ubots.dto.MoviesDto;
import org.challenges.ubots.model.Movies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MoviesService {

    Movies create(MoviesDto moviesDto);

    void edit(MoviesDto moviesDto, Long movieId);

    void delete(Long movieId);

    Movies findById(Long movieId);

    Page<Movies> findAll(Pageable pageable);

    List<Movies> findAllUnrated();

}
