package com.example.leadershipranking.repository;

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
        Query query = entityManager.createQuery("select * from user u where u.user_id = :id")
                .setParameter("id", uuid.toString());

        return !query.getResultList().isEmpty();
    }
}
