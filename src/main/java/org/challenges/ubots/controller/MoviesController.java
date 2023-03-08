package org.challenges.ubots.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.challenges.ubots.dto.MoviesDto;
import org.challenges.ubots.model.Movies;
import org.challenges.ubots.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity create(@RequestBody @Valid MoviesDto dto, UriComponentsBuilder uriBuilder){
        var movies = moviesService.create(dto);
        var uriComponents = uriBuilder.path("/movies/{id}").buildAndExpand(movies.getId()).toUri();
        return ResponseEntity.created(uriComponents).body(new Movies());
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    void edit(@PathVariable Long id, @RequestBody @Valid MoviesDto dto){
        moviesService.edit(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable Long id){
        moviesService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(FOUND)
    ResponseEntity findById(@PathVariable Long id){
        var movies = moviesService.findById(id);
        return ResponseEntity.ok(movies);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<MoviesDto> listAll(@PageableDefault(size = 20, sort = {"title"}) Pageable pageable){
        var movies = moviesService.findAll(pageable);
        return movies.map(Movies::convertToDto);
    }

    @GetMapping("/unrated")
    @ResponseStatus(OK)
    public List<MoviesDto> findAllUnrated(){
        var movies = moviesService.findAllUnrated();
        return movies.stream().map(Movies::convertToDto).toList();
    }

}
