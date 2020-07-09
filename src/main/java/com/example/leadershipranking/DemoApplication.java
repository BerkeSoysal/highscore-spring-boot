package com.example.leadershipranking;

import com.example.leadershipranking.models.Score;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Controller
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(ScoreRepository scoreRepository){
		return args -> {
		};
	}

}

@RestController
class HelloContoller
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
			Score newScore = new Score(UUID.fromString(score.get("user_id").toString()), score.get("timestamp").asLong(), score.get("score").asDouble());
			scoreRepository.save(newScore);
			return new ResponseEntity<>(newScore, HttpStatus.OK);
	}

	HelloContoller(ScoreRepository scoreRepository)
	{
		this.scoreRepository = scoreRepository;
	}
}

interface ScoreRepository extends CrudRepository<Score, Long>
{

}
