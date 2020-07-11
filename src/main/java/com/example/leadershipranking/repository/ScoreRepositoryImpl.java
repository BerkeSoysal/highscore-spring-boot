package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

public class ScoreRepositoryImpl implements ScoreRepositoryCustom
{
    @Autowired
    @Lazy
    ScoreRepository scoreRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long getRanking(long userId)
    {
        //TODO UPDATE SCORE
        return 0;
    }

    @Override
    @Transactional
    public Score updateScore(Score score)
    {
        Score scoreDb = entityManager.find(Score.class , score.getId());
        scoreDb.setScoreWorth(score.getScoreWorth());
        scoreDb.setTimestamp(score.getTimestamp());
        entityManager.persist(scoreDb);
        return score;
    }

}
