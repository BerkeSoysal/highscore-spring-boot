package com.example.leadershipranking.service;

import com.example.leadershipranking.models.UserProfile;
import com.example.leadershipranking.repository.ScoreRepository;
import com.example.leadershipranking.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService
{
    private UserProfileRepository userProfileRepository;

    @Autowired
    public UserServiceImpl(UserProfileRepository userProfileRepository)
    {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public void saveUser(UserProfile user)
    {
        userProfileRepository.save(user);
    }
}
