package com.example.leadershipranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.management.Query;

public class ScoreRepositoryImpl implements ScoreRepositoryCustom
{
    @Autowired
    @Lazy
    ScoreRepository scoreRepository;

    @Override
    public long getRanking(long userId)
    {
        return 0;
    }

    /*
    @Override
    @org.springframework.data.jpa.repository.Query("SELECT count(*) from score where  ")
    public long getRanking(long userId)
    {
        Query query = new Query();
        scoreRepository.count();
    }
     */
}
