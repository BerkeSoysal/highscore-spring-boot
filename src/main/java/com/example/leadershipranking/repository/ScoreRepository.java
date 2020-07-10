package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long>
{

}
