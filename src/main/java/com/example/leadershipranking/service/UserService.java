package com.example.leadershipranking.service;

import com.example.leadershipranking.models.UserProfile;

import java.util.UUID;


public interface UserService
{
    void saveUser(UserProfile user);

    boolean userExistsWithId(UUID userId);
}
