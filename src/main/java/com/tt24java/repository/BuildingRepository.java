package com.tt24java.repository;


import java.util.List;

import com.tt24java.repository.entity.BuildingEntity;


public interface BuildingRepository {
	List<BuildingEntity>findAll(String name, Long districtId);
		
	
	

}