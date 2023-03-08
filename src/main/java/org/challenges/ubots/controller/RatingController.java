package org.challenges.ubots.controller;

import jakarta.validation.Valid;
import org.challenges.ubots.dto.RatingDto;
import org.challenges.ubots.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/{id}")
    @ResponseStatus(OK)
    void addOrUpdateRating(@PathVariable Long id, @RequestBody @Valid RatingDto dto){
        ratingService.addOrUpdateRating(id, dto);
    }

}
