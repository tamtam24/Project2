package com.tt24java.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tt24java.repository.custom.BuildingRepositoryCustom;
import com.tt24java.repository.entity.BuildingEntity;


public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>,BuildingRepositoryCustom{
	void deleteByIdIn(Long[]ids);
		
	List<BuildingEntity> findByNameContaining(String s);
	List<BuildingEntity> findByNameContainingAndStreet(String name,String street);
	

} 