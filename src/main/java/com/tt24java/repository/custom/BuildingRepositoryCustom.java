package com.tt24java.repository.custom;

import java.util.List;

import com.tt24java.builder.BuildingSearchBuilder;
import com.tt24java.repository.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);

}
