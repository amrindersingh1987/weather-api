package com.weather.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDetailModel {

	private String cod;

	private double message;

	private Integer cnt;
	
	private CityDataModel city;
	
	private List<ForcastDetailModel> list;

	public CityDataModel getCity() {
		return city;
	}

	public void setCity(CityDataModel city) {
		this.city = city;
	}

	public List<ForcastDetailModel> getList() {
		return list;
	}

	public void setList(List<ForcastDetailModel> list) {
		this.list = list;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public double getMessage() {
		return message;
	}

	public void setMessage(double message) {
		this.message = message;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
}
