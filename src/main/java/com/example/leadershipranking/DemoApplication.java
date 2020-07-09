package com.example.leadershipranking;

import com.example.leadershipranking.models.Score;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(GreetingRepository greetingRepository){
		return args -> {
			greetingRepository.save(new Score("hello"));
			greetingRepository.save(new Score("hi"));
		};
	}

}

@RestController
class HelloContoller
{
	private final GreetingRepository greetingRepository;

	@GetMapping("/")
	String hello() {
		return "hello world";
	}

	@GetMapping("/leaderboard")
	Iterable<Score> greetings() {
		return greetingRepository.findAll();
	}

	@PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	ResponseEntity<Score> postScore(@RequestBody Score score)
	{
			Score newScore = new Score(score.getUserId(), score.getTimestamp(), score.getScore());

			return new ResponseEntity<Score>(newScore, HttpStatus.OK);
	}

	HelloContoller(GreetingRepository greetingRepository)
	{
		this.greetingRepository = greetingRepository;
	}
}

interface GreetingRepository extends CrudRepository<Score, Long>
{

}
