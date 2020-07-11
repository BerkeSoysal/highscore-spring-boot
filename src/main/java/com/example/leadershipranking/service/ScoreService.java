package com.example.leadershipranking.service;

import com.example.leadershipranking.models.Score;

import java.util.UUID;


public interface ScoreService
{
    void saveScore(Score score);

    void updateScore(UUID uuid);

    Iterable<Score> getRankings();

    Object getRankingsByCountry(String countryCode);
}
