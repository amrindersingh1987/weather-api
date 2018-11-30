package com.weather.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.exception.WeatherApiException;
import com.weather.model.CityDataModel;
import com.weather.model.ForcastDetailModel;
import com.weather.model.WeatherDetailModel;
import com.weather.model.WeatherFinalReportModel;

@Service
public class WeatherApiService{

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApiService.class.getName());
	private final String WAETHER_API_URL = "https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=896cfa57f8323a5b63cfaa298a2daedb&units=metric";

	@Autowired
	ObjectMapper objectMapper;

	private final RestTemplate restTemplate;

	public WeatherApiService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public List<CityDataModel> fetchProperties() {
		LOGGER.info("Enter in class read file");

		List<CityDataModel> cityDataList = new ArrayList<>();
		try {
			File file = ResourceUtils.getFile("classpath:city.list.json");

			cityDataList = objectMapper.readValue(file, new TypeReference<List<CityDataModel>>() {
			});

		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return cityDataList;
	}

	@Cacheable("cities")
	public List<WeatherFinalReportModel> getCityData(String city) throws WeatherApiException {
		List<WeatherFinalReportModel> result = new ArrayList<WeatherFinalReportModel>();
		try {
			WeatherDetailModel weatherReport = restTemplate.getForObject(String.format(WAETHER_API_URL, city), WeatherDetailModel.class);
			for (LocalDate reference = LocalDate.now().plusDays(1); reference
					.isBefore(LocalDate.now().plusDays(4)); reference = reference.plusDays(1)) {

				final LocalDate localReference = reference;
				List<ForcastDetailModel> list = weatherReport.getList().stream()
						.filter(x -> x.getDateTime().toLocalDate().equals(localReference)).collect(Collectors.toList());
				if (list.size() > 0) {
					result.add(getWeatherAvgData(list));
				}

			}
		} catch (HttpClientErrorException e) {
			throw new WeatherApiException(e.getResponseBodyAsString().toString());
		}catch (Exception e) {
			throw new WeatherApiException("Internal server error",e);
		}
      return result;
	}

	private WeatherFinalReportModel getWeatherAvgData(List<ForcastDetailModel> list) {
		WeatherFinalReportModel finalModel = new WeatherFinalReportModel();
		for (ForcastDetailModel forcastData : list) {
			finalModel.setDate(forcastData.getDateTime().toLocalDate());
			finalModel.addData(forcastData);
		}
		finalModel.setFinalValue();

		return finalModel;
	}

}
