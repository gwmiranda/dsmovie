package com.guilherme.dsmovie.repositories;

import com.guilherme.dsmovie.entities.Score;
import com.guilherme.dsmovie.entities.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {
}
