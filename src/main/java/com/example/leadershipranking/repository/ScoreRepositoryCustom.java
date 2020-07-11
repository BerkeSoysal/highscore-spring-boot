package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.Score;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface ScoreRepositoryCustom
{
    long getRanking(long userId);

    @Transactional
    Score updateScore(Score score);
}
