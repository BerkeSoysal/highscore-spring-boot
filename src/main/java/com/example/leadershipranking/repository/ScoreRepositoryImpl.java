package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void updateScore(Score score)
    {

    }

}
