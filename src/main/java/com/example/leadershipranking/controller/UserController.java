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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    @PostMapping(path = "/user/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Map> createUser(@RequestBody JsonNode jsonUser)
    {
        UserProfile userProfile;
        if(Arrays.asList(Locale.getISOCountries()).contains(jsonUser.get("country_code").asText()))
        {
            userProfile = new UserProfile(jsonUser.get("display_name").asText(), jsonUser.get("country_code").asText());
            userService.saveUser(userProfile);
            Map<String,Object> map = new HashMap<>();
            map.put("user_id", userProfile.getUuid());
            map.put("display_name", userProfile.getDisplayName());
            map.put("points", userProfile.getPoints());
            map.put("rank", userProfile.getRanking());

            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
