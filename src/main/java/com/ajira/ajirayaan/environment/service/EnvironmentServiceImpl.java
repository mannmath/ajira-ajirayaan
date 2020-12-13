package com.ajira.ajirayaan.environment.service;

import java.util.List;
import java.util.Map;

import com.ajira.ajirayaan.environment.dao.EnvironmentDAO;
import com.ajira.ajirayaan.environment.dao.EnvironmentDAO.Elements;

public class EnvironmentServiceImpl implements EnvironmentService {
	private EnvironmentDAO currentEnv;

	@Override
	public void configureEnvironment(EnvironmentDAO env) {
		this.currentEnv = env;
	}

	@Override
	public void modifyEnvironment(Map<String, Object> modifier) {
		try {
			for (String envProperty : modifier.keySet()) {
				switch (envProperty.toLowerCase()) {
				case "temperature":
					int newTemprature = Integer.parseInt(modifier.get(envProperty).toString());
					this.currentEnv.setTemprature(newTemprature);
					break;

				case "humidity":
					int newHumidity = Integer.parseInt(modifier.get(envProperty).toString());
					this.currentEnv.setHumidity(newHumidity);
					break;

				case "solar-flare":
					boolean isSolarFlare = Boolean.parseBoolean(modifier.get(envProperty).toString());
					this.currentEnv.setSolarFlare(isSolarFlare);
					break;

				case "storm":
					boolean isStorm = Boolean.parseBoolean(modifier.get(envProperty).toString());
					this.currentEnv.setStorm(isStorm);
					break;

				case "area-map":
					List<List<Elements>> newTerrain = (List<List<Elements>>) modifier.get(envProperty);
					this.currentEnv.setTerrain(newTerrain);
					break;

				default:
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	@Override
	public String getTerrainForPosition(int row, int col) {
		List<List<Elements>> currentTerrain = this.currentEnv.getTerrain();
		try {
			Elements onTerrian = currentTerrain.get(row).get(col);
			return onTerrian.toString();
		} catch (Exception e) {
			return null;
		}
	}

}
