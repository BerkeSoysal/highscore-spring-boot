package com.example.leadershipranking.repository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepositoryCustom
{
    boolean findIsUserExist(UUID uuid);
}
