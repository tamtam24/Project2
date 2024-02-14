package com.tt24java.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.tt24java.builder.BuildingSearchBuilder;
import com.tt24java.repository.BuildingRepository;
import com.tt24java.repository.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
	
		//JPQL
//		String sql="FROM BuildingEntity ";
//		Query query=entityManager.createQuery(sql,BuildingEntity.class);
//		return query.getResultList();
//		
		//SQLNative
		String sql= "SELECT * from building b where b.name like '%building%' ";
		Query query=entityManager.createNativeQuery(sql,BuildingEntity.class);
		return query.getResultList();
	}
	

}
