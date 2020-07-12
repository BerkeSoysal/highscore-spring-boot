package com.example.leadershipranking.service;

import com.example.leadershipranking.models.UserProfile;
import com.example.leadershipranking.repository.UserProfileRepository;
import com.example.leadershipranking.repository.UserProfileRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public UserProfile loadUser(UUID userId)
    {
        return userProfileRepositoryCustom.findUserById(userId);
    }


    @Override
    public UserProfile updateUserScore(UUID uuid, double points)
    {
        return userProfileRepositoryCustom.updateUserPointsAndRanking(uuid, points);
    }

    @Override
    public void updateRankingsLowerThan(Double points, Double oldPoints)
    {
        userProfileRepositoryCustom.updateRankingsLowerThan(points, oldPoints);
    }

    @Override
    public List<UserProfile> getUsersOrderByRank()
    {
        return userProfileRepositoryCustom.getUsersOrderByRank();
    }
}
