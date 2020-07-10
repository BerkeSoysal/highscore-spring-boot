package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long>
{
}
