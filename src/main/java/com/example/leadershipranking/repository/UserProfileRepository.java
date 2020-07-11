package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>
{
}
