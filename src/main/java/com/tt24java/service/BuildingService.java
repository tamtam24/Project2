package com.tt24java.service;

import java.util.List;
import java.util.Map;

import com.tt24java.model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO>findAll(Map<String,Object>params);

}
