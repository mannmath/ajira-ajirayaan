package com.ajira.ajirayaan.environment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ajira.ajirayaan.environment.dao.EnvironmentDAO;
import com.ajira.ajirayaan.environment.dao.EnvironmentDAO.Elements;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {
	private EnvironmentDAO currentEnv;

	@Override
	public void configureEnvironment(EnvironmentDAO env) {
		this.currentEnv = env;
	}

	@Override
	public Map<String, Object> modifyEnvironment(Map<String, Object> modifier) {
		Map<String, Object> response = new HashMap<>();
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
					response.put("statusCode", HttpStatus.BAD_REQUEST);
					response.put("errMsg", "requested property is invalid");
				}
			}
		} catch (NumberFormatException e) {
			response.put("statusCode", HttpStatus.BAD_REQUEST);
			response.put("errMsg", "value provided must be integer");
		} catch (Exception e) {
			response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("errMsg", "something went wrong");
		}
		return response;
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
