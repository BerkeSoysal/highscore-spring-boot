package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserProfileRepositoryImpl implements UserProfileRepositoryCustom
{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Lazy
    UserProfileRepository userProfileRepository;

    @Override
    public boolean findIsUserExist(UUID uuid)
    {
        Query query = entityManager.createQuery("select u from user_profile u where u.uuid = :id")
                .setParameter("id", uuid);
        return !query.getResultList().isEmpty();
    }

    @Override
    public void setRanking(UserProfile user)
    {
        Query query = entityManager.createQuery("select u from user_profile u where u.points > :points")
                .setParameter("points", user.getPoints());
        user.setRanking((long) query.getResultList().size() + 1);
    }

    @Override
    public UserProfile findUserById(UUID userId)
    {
        UserProfile userProfile = null;
        Query query = entityManager.createQuery("select u from user_profile u where u.uuid = :uuid")
                .setParameter("uuid", userId)
                .setMaxResults(1);
        try {
            userProfile = (UserProfile) query.getSingleResult();
        } catch (NoResultException e){
            e.printStackTrace();
        }

        return  userProfile;
    }

    @Override
    @Transactional
    public UserProfile updateUserPointsAndRanking(UUID uuid, double points)
    {
        UserProfile user = entityManager.find(UserProfile.class , uuid);
        user.setPoints(user.getPoints() + points);
        setRanking(user);
        entityManager.persist(user);
        return user;
    }

    @Override
    @Transactional
    public void updateRankingsLowerThan(Double points)
    {
        Query query = entityManager.createQuery("select u from user_profile u where u.points < :points")
                .setParameter("points", points);
        ArrayList<UserProfile> userProfileList = (ArrayList<UserProfile>) query.getResultList();
        for(UserProfile userProfile : userProfileList)
        {
            userProfile.setRanking(userProfile.getRanking() + 1);
            entityManager.persist(userProfile);
        }
    }

    @Override
    public List<UserProfile> getUsersOrderByRank()
    {
        Query query = entityManager.createQuery("select u from user_profile u order by u.ranking");

        return query.getResultList();
    }
}
