package com.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherApiApp {
	 public static void main(String[] args) {
			System.setProperty("server.servlet.context-path", "/api/v1");
			System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
	        SpringApplication.run(WeatherApiApp.class, args);
	    }
}
