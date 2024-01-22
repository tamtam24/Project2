package com.tt24java.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt24java.converter.BuildingDTOConverter;
import com.tt24java.model.BuildingDTO;
import com.tt24java.repository.BuildingRepository;

import com.tt24java.repository.entity.BuildingEntity;

import com.tt24java.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
    private BuildingRepository buildingRepository;
	@Autowired
    private BuildingDTOConverter buildingDTOConverter;
    

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params,List<String>typeCode) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(params,typeCode);
        List<BuildingDTO> result = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
            result.add(building);
        }
        return result;
    }
}
