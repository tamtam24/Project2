package com.tt24java.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tt24java.model.BuildingDTO;
import com.tt24java.repository.DistrictRepository;
import com.tt24java.repository.RentAreaRepository;
import com.tt24java.repository.entity.BuildingEntity;
import com.tt24java.repository.entity.DistrictEntity;
import com.tt24java.repository.entity.rentareaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;

	@Autowired
	private RentAreaRepository rentareaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);

		DistrictEntity districtEntity = districtRepository.findDistrictNameById(item.getDistrictid());
		building.setAddress(item.getStreet() + "," + item.getWard() + "," + districtEntity.getName());
		List<rentareaEntity> rentAreas = rentareaRepository.findRentAreaByBuildingId(item.getId());
		String areaResult = rentAreas.stream().map(it -> it.toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		return building;
	}

}
