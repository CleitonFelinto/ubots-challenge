package org.challenges.ubots.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.challenges.ubots.dto.MoviesDto;
import org.challenges.ubots.model.Movies;
import org.challenges.ubots.repository.MoviesRepository;
import org.challenges.ubots.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MoviesServiceImpl implements MoviesService {

    @Autowired
    private final MoviesRepository repository;

    @Override
    @Transactional
    public Movies create(MoviesDto moviesDto) {
        var movies = Movies.builder()
                .title(moviesDto.getTitle())
                .synopsis(moviesDto.getSynopsis())
                .date(moviesDto.getDate())
                .build();
        return repository.save(movies);
    }

    @Override
    @Transactional
    public void edit(MoviesDto moviesDto, Long movieId) {
        var movies = findById(movieId);
        movies.setTitle(moviesDto.getTitle());
        movies.setSynopsis(moviesDto.getSynopsis());
        movies.setDate(moviesDto.getDate());
        repository.save(movies);
    }

    @Override
    @Transactional
    public void delete(Long movieId) {
        repository.deleteById(movieId);
    }

    @Override
    public Movies findById(Long movieId) {
        return repository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));
    }

    @Override
    public Page<Movies> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Movies> findAllUnrated() {
        List<Movies> moviesList = repository.findAll();
        return moviesList.stream().filter(movie -> !movie.isRated()).collect(Collectors.toList());
    }



}
