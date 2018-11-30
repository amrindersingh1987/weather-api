package com.weather.test;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.weather.controller.WeatherApiController;
import com.weather.exception.WeatherApiException;
import com.weather.model.WeatherFinalReportModel;
import com.weather.service.WeatherApiService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeatherApiControllerJunitTest {

	MockMvc mockMvc;

	@Autowired
	WeatherApiController weatherApiController;

	@MockBean
	WeatherApiService weatherApiService;

	private List<WeatherFinalReportModel> finalReportList;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(this.weatherApiController).build();

		// [{"date":"2018-12-01","daily":20.835,"nightly":10.945,"pressure":15.89},{"date":"2018-12-02","daily":19.8725,"nightly":9.0525,"pressure":14.4625},{"date":"2018-12-03","daily":19.98,"nightly":8.8975,"pressure":14.438749999999999}]
		WeatherFinalReportModel finalReport = new WeatherFinalReportModel();
		finalReport.setDaily(20.835);
		WeatherFinalReportModel finalReport1 = new WeatherFinalReportModel();
		finalReport1.setDaily(19.8725);

		finalReportList = new ArrayList<WeatherFinalReportModel>();
		finalReportList.add(finalReport);
		finalReportList.add(finalReport1);
	}

	@Test
	public void testGetCityResult() throws Exception {
		when(weatherApiService.getCityData(any(String.class))).thenReturn(finalReportList);
		mockMvc.perform(get("/data").contentType(MediaType.APPLICATION_JSON).param("city", "Gurgaon"))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].daily", is(20.835)))
				.andExpect(jsonPath("$[1].daily", is(19.8725)));
	}

	@Test
	public void testGetCityNotFound() throws Exception {
		when(weatherApiService.getCityData(any(String.class)))
				.thenThrow(new WeatherApiException("{\"cod\":\"404\",\"message\":\"city not found\"}"));
		mockMvc.perform(get("/data").contentType(MediaType.APPLICATION_JSON).param("city", "xyz"))
				.andExpect(status().isNotFound());
	}

}