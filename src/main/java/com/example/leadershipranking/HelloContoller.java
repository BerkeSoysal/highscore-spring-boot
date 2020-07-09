package com.example.leadershipranking;

import com.example.leadershipranking.models.Score;
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
public class HelloContoller
{
	private final ScoreRepository scoreRepository;

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
		String guid = "72adc49a-e016-4c62-96f7-08c872a19c6d";
		System.out.println(guid);
		UUID uuid = UUID.fromString(guid);
		Score newScore = new Score(uuid, score.get("timestamp").asLong(), score.get("score_worth").asDouble());
		scoreRepository.save(newScore);
		return new ResponseEntity<>(newScore, HttpStatus.OK);
	}

	HelloContoller(ScoreRepository scoreRepository)
	{
		this.scoreRepository = scoreRepository;
	}
}
