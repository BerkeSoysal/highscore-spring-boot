package com.example.leadershipranking;

import com.example.leadershipranking.models.Score;
import com.example.leadershipranking.models.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RankingContoller
{
	private final ScoreRepository scoreRepository;
	private final UserRepository userRepository;

	@GetMapping("/")
	String hello() {
		return "hello world";
	}

	@GetMapping("/leaderboard")
	Iterable<Score> greetings() {
		return scoreRepository.findAll();
	}

	@PostMapping(path = "/score/submit",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Score> postScore(@RequestBody JsonNode score)
	{
		String guid = score.get("user_id").asText();
		UUID uuid = UUID.fromString(guid);
		//TODO if no userid with uuid, return false
		Score newScore = new Score(uuid, score.get("timestamp").asLong(), score.get("score_worth").asDouble());
		scoreRepository.save(newScore);
		return new ResponseEntity<>(newScore, HttpStatus.OK);
	}

	@PostMapping(path = "/user/create",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<User> createUser(@RequestBody JsonNode user)
	{
		User newUser = new User(user.get("display_name").asText());
		userRepository.save(newUser);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	RankingContoller(ScoreRepository scoreRepository, UserRepository userRepository)
	{
		this.scoreRepository = scoreRepository;
		this.userRepository = userRepository;
	}
}
