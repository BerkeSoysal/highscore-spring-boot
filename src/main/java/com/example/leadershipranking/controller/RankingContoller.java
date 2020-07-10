package com.example.leadershipranking.controller;

import com.example.leadershipranking.models.Score;
import com.example.leadershipranking.models.UserProfile;
import com.example.leadershipranking.repository.ScoreRepository;
import com.example.leadershipranking.repository.UserProfileRepository;
import com.example.leadershipranking.service.ScoreService;
import com.example.leadershipranking.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
public class RankingContoller
{

	private final UserService userService;
	private final ScoreService scoreService;

	@Autowired
	private RankingContoller(UserService userService, ScoreService scoreService)
	{
		this.userService = userService;
		this.scoreService = scoreService;
	}

	@GetMapping("/")
	String hello() {
		return "Welcome to the leadership ranking";
	}

	@GetMapping("/leaderboard")
	Iterable<Score> greetings() {
		return scoreRepository.findAll();
	}

	@GetMapping("/leaderboard/{countryCode}")
	ResponseEntity<List<Score>> scores(@PathVariable String countryCode) {
		boolean result = Arrays.asList(Locale.getISOCountries()).contains(countryCode);
		if(result)
		{
			List<Score>  scores = (List<Score>) scoreRepository.findAll();
			return new ResponseEntity<>(scores, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
 		}
	}

	@PostMapping(path = "/score/submit",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Score> postScore(@RequestBody JsonNode jsonScore)
	{
		String guid = jsonScore.get("user_id").asText();
		UUID uuid = UUID.fromString(guid);
		//TODO if no userid with uuid, return false
		Score score = new Score(uuid, jsonScore.get("timestamp").asLong(), jsonScore.get("score_worth").asDouble());
		scoreService.saveScore(score);
		return new ResponseEntity<>(score, HttpStatus.OK);
	}

	@PostMapping(path = "/user/create",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<UserProfile> createUser(@RequestBody JsonNode jsonUser)
	{
		UserProfile newUserProfile;
		if(Arrays.asList(Locale.getISOCountries()).contains(jsonUser.get("country_code").asText()))
		{
			newUserProfile = new UserProfile(jsonUser.get("display_name").asText(), jsonUser.get("country_code").asText());
			userService.saveUser(newUserProfile);
			scoreService.saveScore(newUserProfile.getScore());
			return new ResponseEntity<>(newUserProfile, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
