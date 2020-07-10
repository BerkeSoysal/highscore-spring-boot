package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.Score;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepositoryCustom
{
    public long getRanking(long userId);

    void updateScore(Score score);
}
