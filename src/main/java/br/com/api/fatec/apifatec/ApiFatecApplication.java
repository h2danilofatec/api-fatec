package br.com.api.fatec.apifatec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@SpringBootApplication
public class ApiFatecApplication {
	
	@RequestMapping("/")
	String home() {
		return "hello world";
	}
	
	@RequestMapping("/hello")
	String home2() {
		return "hello world 2";
	}
	
	@RequestMapping("/numero")
	Integer numero() {
		return 2;
	}
	
	@RequestMapping("/numero/{num}")
	Integer numero2(@PathVariable Integer num) {
		return num;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApiFatecApplication.class, args);
	}

}
