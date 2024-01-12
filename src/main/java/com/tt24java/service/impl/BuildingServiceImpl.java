package com.tt24java.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt24java.model.BuildingDTO;
import com.tt24java.repository.BuildingRepository;
import com.tt24java.repository.entity.BuildingEntity;
import com.tt24java.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(params);
        List<BuildingDTO> result = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO building = new BuildingDTO();
            building.setName(item.getName());
            building.setAddress(item.getStreet() + "," + item.getWard() + "," + item.getDistrictid());
            building.setNumberofbasement(item.getNumberofbasement());
            building.setFloorarea(item.getFloorarea());
            building.setRentprice(item.getRentprice());
            
            result.add(building);
        }
        return result;
    }
}
