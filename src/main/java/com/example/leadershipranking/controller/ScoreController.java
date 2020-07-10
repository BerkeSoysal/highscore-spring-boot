package com.example.leadershipranking.controller;

import com.example.leadershipranking.models.Score;
import com.example.leadershipranking.service.ScoreService;
import com.example.leadershipranking.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ScoreController
{
    private final ScoreService scoreService;
    private final UserService userService;

    @Autowired
    public ScoreController(ScoreService scoreService, UserService userService)
    {
        this.scoreService = scoreService;
        this.userService = userService;
    }

    @PostMapping(path = "/score/submit",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Score> postScore(@RequestBody JsonNode jsonScore)
    {
        UUID userId = UUID.fromString(jsonScore.get("user_id").asText());

        boolean userExists = userService.userExistsWithId(userId);
        //TODO if no userid with uuid, return false
        if(userExists)
        {
            Score score = new Score(userId, jsonScore.get("timestamp").asLong(), jsonScore.get("score_worth").asDouble());
            scoreService.saveScore(score);
            return new ResponseEntity<>(score, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
