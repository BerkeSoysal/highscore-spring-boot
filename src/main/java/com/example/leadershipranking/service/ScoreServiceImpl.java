package com.example.leadershipranking.service;

import com.example.leadershipranking.models.Score;
import com.example.leadershipranking.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ScoreServiceImpl implements ScoreService
{
    private ScoreRepository scoreRepository;

    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository)
    {
        this.scoreRepository = scoreRepository;
    }


    @Override
    public void saveScore(Score score)
    {
        scoreRepository.save(score);
    }
}
