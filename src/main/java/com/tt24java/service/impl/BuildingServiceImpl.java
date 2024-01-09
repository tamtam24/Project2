package com.tt24java.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tt24java.model.BuildingDTO;
import com.tt24java.repository.BuildingRepository;
import com.tt24java.repository.entity.BuildingEntity;
import com.tt24java.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService {

	private BuildingRepository buildingRepository;
	
	@Override
	public List<BuildingDTO>findAll(String name,Long districtId){
		List<BuildingEntity>buildingEntities = buildingRepository.findAll(name,districtId);
		List<BuildingDTO>result=new ArrayList<BuildingDTO>();
		for(BuildingEntity item:buildingEntities) {
			BuildingDTO building=new BuildingDTO();
			building.setName(item.getName());
			building.setAddress(item.getStreet()+ "," + "," + item.getWard() +"," + item.getDistrictid());
			building.setNumberofbasement(item.getNumberofbasement());
			building.setFloorarea(item.getFloorarea());
			result.add(building);
		}
		return result;
	}

}
