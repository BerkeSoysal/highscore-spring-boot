package com.example.leadershipranking.controller;

import com.example.leadershipranking.models.UserProfile;
import com.example.leadershipranking.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

@RestController
public class UserController
{
    private final UserService userService;

    @Autowired
    private UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/user/profile/{guid}")
    public ResponseEntity<UserProfile> getUser(@PathVariable("guid")UUID uuid)
    {
        return new ResponseEntity<>(userService.loadUser(uuid), HttpStatus.OK);
    }


    @PostMapping(path = "/user/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserProfile> createUser(@RequestBody JsonNode jsonUser)
    {
        UserProfile userProfile;
        if(Arrays.asList(Locale.getISOCountries()).contains(jsonUser.get("country_code").asText()))
        {
            userProfile = new UserProfile(jsonUser.get("display_name").asText(), jsonUser.get("country_code").asText());
            userService.saveUser(userProfile);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
