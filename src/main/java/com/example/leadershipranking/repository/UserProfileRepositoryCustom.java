package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/*
    A class to implement crud operations
 */
@Repository
public interface UserProfileRepositoryCustom
{
    void setRanking(UserProfile user);

    UserProfile findUserById(UUID userId);

    UserProfile updateUserPointsAndRanking(UUID uuid, double points);

    void updateRankingsLowerThan(Double newPoints, Double oldPoints);

    List<UserProfile> getUsersOrderByRank(String countryCode);
}
