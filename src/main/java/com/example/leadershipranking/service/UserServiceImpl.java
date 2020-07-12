package com.example.leadershipranking.service;

import com.example.leadershipranking.models.UserProfile;
import com.example.leadershipranking.repository.UserProfileRepository;
import com.example.leadershipranking.repository.UserProfileRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService
{
    private final UserProfileRepositoryCustom userProfileRepositoryCustom;
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserServiceImpl(UserProfileRepository userProfileRepository,
                            UserProfileRepositoryCustom userProfileRepositoryCustom)
    {
        this.userProfileRepository = userProfileRepository;
        this.userProfileRepositoryCustom = userProfileRepositoryCustom;
    }

    @Override
    public void saveUser(UserProfile user)
    {
        userProfileRepositoryCustom.setRanking(user);
        userProfileRepository.save(user);
    }

    @Override
    public boolean userExistsWithId(UUID userId)
    {
        return userProfileRepositoryCustom.findIsUserExist(userId);
    }
}
