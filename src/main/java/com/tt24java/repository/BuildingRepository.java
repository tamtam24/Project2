package com.tt24java.repository;


import java.util.List;
import java.util.Map;

import com.tt24java.repository.entity.BuildingEntity;


public interface BuildingRepository {
	List<BuildingEntity>findAll(Map<String,Object>params);
		
	
	

}