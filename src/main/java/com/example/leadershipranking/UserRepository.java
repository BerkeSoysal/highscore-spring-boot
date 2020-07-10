package com.example.leadershipranking;

import com.example.leadershipranking.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
}
