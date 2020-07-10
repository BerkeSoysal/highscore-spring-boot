package com.example.leadershipranking.service;

import com.example.leadershipranking.models.Score;
import org.springframework.stereotype.Service;

@Service
public interface ScoreService
{
    public void saveScore(Score score);
}
