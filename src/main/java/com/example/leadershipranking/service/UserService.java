package com.example.leadershipranking.service;

import com.example.leadershipranking.models.UserProfile;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;
import java.util.UUID;


public interface UserService
{
    void saveUser(UserProfile user);

    UserProfile loadUser(UUID userId);

    boolean userExistsWithId(UUID userId);

    UserProfile updateUserScore(UUID uuid, double points);

    void updateRankingsLowerThan(Double points);

    List<UserProfile> getUsersOrderByRank();
}
