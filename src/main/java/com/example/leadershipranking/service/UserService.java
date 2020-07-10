package com.example.leadershipranking.service;

import com.example.leadershipranking.models.UserProfile;
import org.springframework.stereotype.Service;

@Service
public interface UserService
{
    void saveUser(UserProfile user);
}
