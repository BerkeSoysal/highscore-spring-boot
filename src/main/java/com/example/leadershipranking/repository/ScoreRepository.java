package com.example.leadershipranking.repository;

import com.example.leadershipranking.models.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long>
{

}
