package com.ajira.ajirayaan.environment.dao;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvironmentDAO {
	@NotNull
	@JsonProperty
	private int temperature;
	@NotNull
	@JsonProperty
	private int humidity;
	@NotNull
	@JsonProperty("solar-flare")
	private boolean solarFlare;
	@NotNull
	@JsonProperty
	private boolean storm;

	public enum Elements {
		dirt, water, rock, sand
	}

	@NotNull
	@NotEmpty
	@JsonProperty("area-map")
	private List<List<Elements>> terrain;

	public int getTemprature() {
		return temperature;
	}

	public void setTemprature(int temprature) {
		this.temperature = temprature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public boolean isSolarFlare() {
		return solarFlare;
	}

	public void setSolarFlare(boolean solarFlare) {
		this.solarFlare = solarFlare;
	}

	public boolean isStorm() {
		return storm;
	}

	public void setStorm(boolean storm) {
		this.storm = storm;
	}

	public List<List<Elements>> getTerrain() {
		return terrain;
	}

	public void setTerrain(List<List<Elements>> terrain) {
		this.terrain = terrain;
	}

	public EnvironmentDAO() {
		super();
	}

	public EnvironmentDAO(@NotNull @NotEmpty int temprature, @NotNull @NotEmpty int humidity,
			@NotNull @NotEmpty boolean solarFlare, @NotNull @NotEmpty boolean storm,
			@NotNull @NotEmpty List<List<Elements>> terrain) {
		super();
		this.temperature = temprature;
		this.humidity = humidity;
		this.solarFlare = solarFlare;
		this.storm = storm;
		this.terrain = terrain;
	}

	@Override
	public String toString() {
		return "EnvironmentDAO [temperature=" + temperature + ", humidity=" + humidity + ", solarFlare=" + solarFlare
				+ ", storm=" + storm + ", terrain=" + terrain + "]";
	}
}
