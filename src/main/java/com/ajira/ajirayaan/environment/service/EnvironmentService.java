package com.ajira.ajirayaan.environment.service;

import java.util.Map;

import com.ajira.ajirayaan.environment.dao.EnvironmentDAO;

public interface EnvironmentService {

	public void configureEnvironment(EnvironmentDAO env);

	public void modifyEnvironment(Map<String, Object> modifier);

	public String getTerrainForPosition(int row, int col);
}
