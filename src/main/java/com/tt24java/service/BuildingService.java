package com.tt24java.service;

import java.util.List;
import java.util.Map;

import com.tt24java.model.BuildingDTO;
import com.tt24java.repository.entity.BuildingEntity;

public interface BuildingService {
	List<BuildingDTO>findAll(Map<String,Object>params,List<String>typeCode);
	void deleteBuilding(Long id);

}
