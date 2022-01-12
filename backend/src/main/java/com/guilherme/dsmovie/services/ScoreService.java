package com.guilherme.dsmovie.services;

import com.guilherme.dsmovie.dto.MovieDTO;
import com.guilherme.dsmovie.dto.ScoreDTO;
import com.guilherme.dsmovie.entities.Movie;
import com.guilherme.dsmovie.entities.Score;
import com.guilherme.dsmovie.entities.User;
import com.guilherme.dsmovie.repositories.MovieRepository;
import com.guilherme.dsmovie.repositories.ScoreRepository;
import com.guilherme.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto){

        var user = userRepository.findByEmail(dto.getEmail());
        if(user == null){
            user = new User();
            user.setEmail(dto.getEmail());
            user = userRepository.saveAndFlush(user);
        }

        var movie = movieRepository.findById(dto.getMovieId()).get();

        var score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());

        score = scoreRepository.saveAndFlush(score);

        var sum = 0;
        for (Score s: movie.getScores()){
            sum+= s.getValue();
        }

        var avg = (double) sum / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());

        movie = movieRepository.save(movie);
        return new MovieDTO(movie);
    }
}
