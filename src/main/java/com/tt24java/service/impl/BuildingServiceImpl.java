package com.tt24java.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt24java.model.BuildingDTO;
import com.tt24java.repository.BuildingRepository;
import com.tt24java.repository.DistrictRepository;
import com.tt24java.repository.RentAreaRepository;
import com.tt24java.repository.entity.BuildingEntity;
import com.tt24java.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
    private BuildingRepository buildingRepository;
	@Autowired
    private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentareaRepository;

    
//    public BuildingServiceImpl(BuildingRepository buildingRepository) {
//        this.buildingRepository = buildingRepository;
//    }

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(params);
        List<BuildingDTO> result = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingDTO building = new BuildingDTO();
            building.setName(item.getName());
            building.setAddress(item.getStreet() + "," + item.getWard() + "," + districtRepository.findDistrictNameById(item.getDistrictid()));
            building.setNumberofbasement(item.getNumberofbasement());
            building.setManagername(item.getManagername());
            building.setManagerphonenumber(item.getManagerphonenumber());
            building.setFloorarea(item.getFloorarea());
            building.setRentprice(item.getRentprice());
            building.setServicefee(item.getServicefee());
            building.setBrokeragefee(item.getBrokeragefee());
            building.setTypeCode(rentareaRepository.findRentAreaByBuildingId(item.getId()));
            
            result.add(building);
        }
        return result;
    }
}
