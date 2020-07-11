package com.example.leadershipranking.repository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScoreRepositoryCustom
{
    long getRanking(long userId);

    void updateScore(UUID uuid);
}
