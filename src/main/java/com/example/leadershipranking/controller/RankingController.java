package com.example.leadershipranking.controller;

import com.example.leadershipranking.models.UserProfile;
import com.example.leadershipranking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
public class RankingController
{

	private final UserService userService;

	@Autowired
	private RankingController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping("/")
	public String welcomePage()
	{
		return "Welcome to the leadership ranking";
	}

	@GetMapping("/leaderboard")
	public ResponseEntity<List<UserProfile>> scoreBoard()
	{
		List<UserProfile> userProfiles = userService.getUsersOrderByRank();

		return new ResponseEntity<>(userProfiles, HttpStatus.OK);
	}

	@GetMapping("/leaderboard/{countryCode}")
	public ResponseEntity<List<UserProfile>> scores(@PathVariable String countryCode)
	{
		boolean result = Arrays.asList(Locale.getISOCountries()).contains(countryCode);
		if(result)
		{
			List<UserProfile> userProfiles = userService.getUsersOrderByRank();
			return new ResponseEntity<>(userProfiles, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
 		}
	}
}
