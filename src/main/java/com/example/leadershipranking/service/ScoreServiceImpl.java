package com.example.leadershipranking.service;

import com.example.leadershipranking.models.Score;
import com.example.leadershipranking.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService
{
    private final ScoreRepository scoreRepository;

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

    @Override
    public Iterable<Score> getRankings()
    {
        return scoreRepository.findAll();
    }

    @Override
    public Object getRankingsByCountry(String countryCode)
    {
        return scoreRepository.findAll();
    }
}
