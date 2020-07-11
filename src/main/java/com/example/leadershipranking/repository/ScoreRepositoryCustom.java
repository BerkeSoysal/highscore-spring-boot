package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.Score;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScoreRepositoryCustom
{
    long getRanking(long userId);

    Score updateScore(UUID uuid);
}
