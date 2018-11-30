package com.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.service.WeatherApiService;

import springfox.documentation.spring.web.json.Json;

/**
 * This is main Controller for Weather Rest API 
 * @author Amrinder Singh
 */

@RestController
public class WeatherApiController {

	@Autowired
	WeatherApiService weatherApiService;

	/**
	 * This is to check if api server up
	 * @return ResponseEntity
	 */
	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<String>("welcome to weather api", HttpStatus.OK);
	}

	/**
	 * Retrieve the weather information of specific city
	 * @param @RequestParam city
	 * @return ResponseEntity
	 */
	@GetMapping("/data")
	public ResponseEntity<?> getWeatherReport(@RequestParam(required = true) String city) {
		try {
			if (city == null || city.equals("")) {
				return new ResponseEntity<>(new Json("{'message' : 'Please enter the city name', 'cod',: '"+HttpStatus.BAD_GATEWAY+"'}"), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(weatherApiService.getCityData(city), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Json(ex.getMessage()),HttpStatus.NOT_FOUND);
		}
	}

}
