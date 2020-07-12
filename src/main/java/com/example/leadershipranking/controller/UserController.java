package com.example.leadershipranking.controller;

import com.example.leadershipranking.models.UserProfile;
import com.example.leadershipranking.service.ScoreService;
import com.example.leadershipranking.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController
{
    private final UserService userService;
    private final ScoreService scoreService;

    @Autowired
    private UserController(UserService userService, ScoreService scoreService)
    {
        this.userService = userService;
        this.scoreService = scoreService;
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
}
