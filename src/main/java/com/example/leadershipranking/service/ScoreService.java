package com.example.leadershipranking.service;

import com.example.leadershipranking.models.Score;
import org.springframework.stereotype.Service;

@Service
public interface ScoreService
{
    void saveScore(Score score);

    Iterable<Score> getRankings();

    Object getRankingsByCountry(String countryCode);
}
