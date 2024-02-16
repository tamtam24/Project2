package com.tt24java.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tt24java.model.BuildingDTO;
import com.tt24java.model.BuildingRequestDTO;
import com.tt24java.repository.entity.BuildingEntity;
import com.tt24java.repository.entity.DistrictEntity;
import com.tt24java.service.BuildingService;

@RestController
@Transactional
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    
    @PersistenceContext
	private EntityManager entityManager;

    @GetMapping(value = "/api/building/")
    public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
    		                             @RequestParam (name="typeCode",required=false)List<String>typeCode) {
        List<BuildingDTO> result = buildingService.findAll(params,typeCode);
        return result;
    }
    
    @PostMapping(value ="/api/building/")
    public void createBuilding(@RequestBody BuildingRequestDTO buildingDTO) {
    	BuildingEntity builEntity=new BuildingEntity();
    	builEntity.setName(buildingDTO.getName());
    	builEntity.setStreet(buildingDTO.getStreet());
    	builEntity.setWard(buildingDTO.getWard());
    	DistrictEntity districtEntity=new DistrictEntity();
    	districtEntity.setId(buildingDTO.getDistictId());
    	builEntity.setDistrict(districtEntity);
    	entityManager.persist(builEntity);
    	System.out.print("oke");
    }
    
    @PutMapping(value ="/api/building/")
    public void updateBuilding(@RequestBody BuildingRequestDTO buildingDTO) {
    	BuildingEntity builEntity=new BuildingEntity();
    	builEntity.setId(1L);
    	builEntity.setName(buildingDTO.getName());
    	builEntity.setStreet(buildingDTO.getStreet());
    	builEntity.setWard(buildingDTO.getWard());
    	DistrictEntity districtEntity=new DistrictEntity();
    	districtEntity.setId(buildingDTO.getDistictId());
    	builEntity.setDistrict(districtEntity);
    	entityManager.merge(builEntity);
    	System.out.print("oke");
    }
    
    @DeleteMapping(value ="/api/building/{id}")
    public void deleteBuilding(@PathVariable Long id) {
    	BuildingEntity builEntity = entityManager.find(BuildingEntity.class, id);
    	entityManager.remove(builEntity);
    	System.out.print("oke");
    }
    
}