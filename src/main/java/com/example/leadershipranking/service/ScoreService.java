package com.example.leadershipranking.service;

import com.example.leadershipranking.models.Score;


public interface ScoreService
{
    void saveScore(Score score);

    void updateScore(Score score);

    Iterable<Score> getRankings();

    Object getRankingsByCountry(String countryCode);
}
