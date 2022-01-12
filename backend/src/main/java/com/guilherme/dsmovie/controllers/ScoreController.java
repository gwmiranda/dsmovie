package com.guilherme.dsmovie.controllers;

import com.guilherme.dsmovie.dto.MovieDTO;
import com.guilherme.dsmovie.dto.ScoreDTO;
import com.guilherme.dsmovie.services.MovieService;
import com.guilherme.dsmovie.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {

    @Autowired
    private ScoreService service;

    @PutMapping
    public MovieDTO saveScore(@RequestBody ScoreDTO dto){
        MovieDTO movieDTO = service.saveScore(dto);
        return movieDTO;
    }
}
