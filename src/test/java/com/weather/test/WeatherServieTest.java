package com.weather.test;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.weather.exception.WeatherApiException;
import com.weather.model.WeatherFinalReportModel;
import com.weather.service.WeatherApiService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServieTest {
	
	@Autowired
    private WeatherApiService weatherApiService;
    
	@Test
    public void testGetMangasByTitle() {
         List<WeatherFinalReportModel> finalReport;
		try {
			finalReport = weatherApiService.getCityData("Gurgaon");
	        Assertions.assertThat(finalReport).isNotNull().isNotEmpty();
		} catch (WeatherApiException e) {
		}
    }
}
