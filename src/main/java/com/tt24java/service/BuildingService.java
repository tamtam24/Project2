package com.tt24java.service;

import java.util.List;

import com.tt24java.model.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO>findAll(String name, Long districtid);

}
