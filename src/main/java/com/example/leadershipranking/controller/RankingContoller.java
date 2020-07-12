package com.example.leadershipranking.controller;

import com.example.leadershipranking.models.Score;
import com.example.leadershipranking.models.UserProfile;
import com.example.leadershipranking.service.ScoreService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
public class RankingContoller
{

	private final ScoreService scoreService;

	@Autowired
	private RankingContoller(ScoreService scoreService)
	{
		this.scoreService = scoreService;
	}

	@GetMapping("/")
	public String welcomePage()
	{
		return "Welcome to the leadership ranking";
	}

	@GetMapping("/leaderboard")
	public Iterable<Score> greetings()
	{
		return scoreService.getRankings();
	}

	@GetMapping("/leaderboard/{countryCode}")
	public ResponseEntity<List<Score>> scores(@PathVariable String countryCode)
	{
		boolean result = Arrays.asList(Locale.getISOCountries()).contains(countryCode);
		if(result)
		{
			List<Score>  scores = (List<Score>) scoreService.getRankingsByCountry(countryCode);
			return new ResponseEntity<>(scores, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
 		}
	}
}
