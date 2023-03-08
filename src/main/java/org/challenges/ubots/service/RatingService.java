package org.challenges.ubots.service;

import org.challenges.ubots.dto.RatingDto;
import org.challenges.ubots.model.Rating;

public interface RatingService {

    void addOrUpdateRating(Long movieId, RatingDto ratingDto);

}
