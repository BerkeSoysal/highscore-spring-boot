package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        Query query = entityManager.createQuery("select u from user_profile u where u.points < :points")
                .setParameter("points", user.getPoints());
        user.setRanking((long) query.getResultList().size() + 1);
    }
}
