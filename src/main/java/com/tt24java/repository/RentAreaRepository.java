package com.tt24java.repository;

import java.util.List;

import com.tt24java.repository.entity.rentareaEntity;

public interface RentAreaRepository {
	List<rentareaEntity> findRentAreaByBuildingId(int buildingId);

}
