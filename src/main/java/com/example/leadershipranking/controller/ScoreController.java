package com.example.leadershipranking.controller;

import com.example.leadershipranking.models.UserProfile;
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
    private final UserService userService;

    @Autowired
    public ScoreController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping(path = "/score/submit",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserProfile> postScore(@RequestBody JsonNode jsonScore)
    {
        UUID userId = UUID.fromString(jsonScore.get("user_id").asText());
        UserProfile userProfile = userService.loadUser(userId);
        double oldPoints = userProfile.getPoints();
        if (null == userProfile)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.updateUserScore(userId, jsonScore.get("score_worth").asDouble());
        userProfile = userService.loadUser(userId);
        userService.updateRankingsLowerThan(userProfile.getPoints(), oldPoints);
        return new ResponseEntity<>(userService.loadUser(userId), HttpStatus.OK);
    }
}
