package com.example.leadershipranking.service;

import com.example.leadershipranking.models.UserProfile;

import java.util.UUID;


public interface UserService
{
    void saveUser(UserProfile user);

    void loadUser(UUID userId);

    boolean userExistsWithId(UUID userId);
}
