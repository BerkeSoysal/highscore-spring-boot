package com.example.leadershipranking.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepositoryCustom
{
    public long getRanking(long userId);
}
