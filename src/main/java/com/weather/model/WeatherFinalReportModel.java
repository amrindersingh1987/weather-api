package com.weather.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class WeatherFinalReportModel {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	private double daily;

	private double nightly;

	private double pressure;
	
	@JsonIgnore
	private Double dailyData;

	@JsonIgnore
	private Integer countDailyData;

	@JsonIgnore
	private Double nightlyData;

	@JsonIgnore
	private Integer countNightlyData;

	@JsonIgnore
	private Double pressureData;

	@JsonIgnore
	private Integer countPressureData;
	
	public WeatherFinalReportModel() {
		this.dailyData = new Double(0);
		this.nightlyData = new Double(0);
		this.pressureData = new Double(0);
		this.countDailyData = 0;
		this.countNightlyData = 0;
		this.countPressureData = 0;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getDaily() {
		return daily;
	}

	public void setDaily(double daily) {
		this.daily = daily;
	}

	public double getNightly() {
		return nightly;
	}

	public void setNightly(double nightly) {
		this.nightly = nightly;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public Double getDailyData() {
		return dailyData;
	}

	public void setDailyData(Double dailyData) {
		this.dailyData = dailyData;
	}

	public Double getNightlyData() {
		return nightlyData;
	}

	public void setNightlyData(Double nightlyData) {
		this.nightlyData = nightlyData;
	}

	public Integer getCountNightlyData() {
		return countNightlyData;
	}

	public void setCountNightlyData(Integer countNightlyData) {
		this.countNightlyData = countNightlyData;
	}

	public Double getPressureData() {
		return pressureData;
	}

	public void setPressureData(Double pressureData) {
		this.pressureData = pressureData;
	}

	public Integer getCountPressureData() {
		return countPressureData;
	}

	public void setCountPressureData(Integer countPressureData) {
		this.countPressureData = countPressureData;
	}

	public void addData(ForcastDetailModel model) {
		if (isDaily(model.getDateTime())) {
			this.dailyData = this.dailyData + model.getMain().getTemp();
			this.countDailyData++;
		} else {
			this.nightlyData = this.nightlyData + model.getMain().getTemp();
			this.countNightlyData++;
		}
		this.pressureData = this.pressureData + model.getMain().getTemp();
		this.countPressureData++;
	}
	
    public void setFinalValue() {
    	
    	if(this.dailyData  > 0) {
    		this.daily= dailyData/countDailyData;
    	}
    	if(this.nightlyData  > 0) {
    		this.nightly= nightlyData/countNightlyData;
    	}
    	if(this.pressureData  > 0) {
    		this.pressure= pressureData/countPressureData;
    	}
    	
    }
	public Boolean isDaily(LocalDateTime dateTime) {
		return (dateTime.getHour() >= 6 && dateTime.getHour() < 18);
	}
}
