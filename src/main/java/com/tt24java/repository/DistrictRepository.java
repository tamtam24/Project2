package com.tt24java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tt24java.repository.entity.DistrictEntity;

public interface DistrictRepository extends JpaRepository<DistrictEntity,Long>{
	DistrictEntity findDistrictNameById(Long id);

}
