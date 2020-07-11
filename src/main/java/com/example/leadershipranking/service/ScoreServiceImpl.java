package com.example.leadershipranking.service;

import com.example.leadershipranking.models.Score;
import com.example.leadershipranking.repository.ScoreRepository;
import com.example.leadershipranking.repository.ScoreRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScoreServiceImpl implements ScoreService
{
    private final ScoreRepository scoreRepository;
    private final ScoreRepositoryCustom scoreRepositoryCustom;

    @Autowired
    public ScoreServiceImpl(ScoreRepository scoreRepository, ScoreRepositoryCustom scoreRepositoryCustom)
    {
        this.scoreRepository = scoreRepository;
        this.scoreRepositoryCustom = scoreRepositoryCustom;
    }

    @Override
    public void saveScore(Score score)
    {
        scoreRepository.save(score);
    }

    @Override
    public Score updateScore(UUID uuid)
    {
        return scoreRepositoryCustom.updateScore(uuid);
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
