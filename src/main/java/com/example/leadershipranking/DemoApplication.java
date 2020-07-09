package com.example.leadershipranking;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Controller
@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(GreetingRepository greetingRepository){
		return args -> {
			greetingRepository.save(new Greeting("hello"));
			greetingRepository.save(new Greeting("hi"));
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

	@GetMapping("/greetings")
	Iterable<Greeting> greetings() {
		return greetingRepository.findAll();
	}

	HelloContoller(GreetingRepository greetingRepository)
	{
		this.greetingRepository = greetingRepository;
	}
}

@Entity
class Greeting {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String message;

	public Greeting(){
	}

	public Greeting(String message) {
		this.message = message;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}

interface GreetingRepository extends CrudRepository<Greeting, Long>
{

}
