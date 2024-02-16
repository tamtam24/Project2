package com.tt24java.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tt24java.model.BuildingDTO;
import com.tt24java.repository.entity.BuildingEntity;
import com.tt24java.repository.entity.rentareaEntity;

@Component
public class BuildingDTOConverter {
	
	
	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		building.setAddress(item.getStreet() + "," + item.getWard() + "," + item.getDistrict().getName());
		List<rentareaEntity> rentAreas = item.getItems();
		String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(areaResult);
		return building;
	}

}
