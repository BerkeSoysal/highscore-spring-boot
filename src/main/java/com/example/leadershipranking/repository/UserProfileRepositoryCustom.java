package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepositoryCustom
{
    boolean findIsUserExist(UUID uuid);

    void setRanking(UserProfile user);
}
