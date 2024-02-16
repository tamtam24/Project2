package com.tt24java.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt24java.builder.BuildingSearchBuilder;
import com.tt24java.converter.BuildingDTOConverter;
import com.tt24java.converter.BuildingSearchBuilderConverter;
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
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params,List<String>typeCode) {
    	BuildingSearchBuilder buildingSearchBuilder=buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        List<BuildingDTO> result = new ArrayList<BuildingDTO>();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
            result.add(building);
        }
        return result;
    }
}
