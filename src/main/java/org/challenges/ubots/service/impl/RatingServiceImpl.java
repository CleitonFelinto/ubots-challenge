package org.challenges.ubots.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.challenges.ubots.dto.RatingDto;
import org.challenges.ubots.model.Rating;
import org.challenges.ubots.repository.RatingRepository;
import org.challenges.ubots.service.MoviesService;
import org.challenges.ubots.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;

    private final MoviesService moviesService;

    @Transactional
    public Rating createRating(Long movieId, RatingDto ratingDto) {
        var rating = Rating.builder()
                .rating(ratingDto.getRating())
                .comment(ratingDto.getComment())
                .build();
        return repository.save(rating);
    }

    @Override
    @Transactional
    public void addOrUpdateRating(Long movieId, RatingDto ratingDto) {
        var movies = moviesService.findById(movieId);
        movies.getRating().add(createRating(movieId, ratingDto));
    }

}
