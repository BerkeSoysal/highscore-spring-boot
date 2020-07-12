package com.example.leadershipranking.service;

import com.example.leadershipranking.models.UserProfile;

import java.util.List;
import java.util.UUID;


public interface UserService
{
    void saveUser(UserProfile user);

    UserProfile loadUser(UUID userId);

    UserProfile updateUserScore(UUID uuid, double points);

    void updateRankingsLowerThan(Double points, Double oldPoints);

    List<UserProfile> getUsersOrderByRank(String countryCode);
}
