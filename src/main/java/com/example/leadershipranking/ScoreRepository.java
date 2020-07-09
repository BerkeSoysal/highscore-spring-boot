package com.example.leadershipranking;

import com.example.leadershipranking.models.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Long>
{

}
