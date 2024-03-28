package com.tt24java.api;

import java.util.List;
import java.util.Map;

import com.tt24java.repository.entity.BuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tt24java.model.BuildingDTO;
import com.tt24java.service.BuildingService;

@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/api/building/")
    public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
    		                             @RequestParam (name="typeCode",required=false)List<String>typeCode) {
        List<BuildingDTO> result = buildingService.findAll(params,typeCode);
        return result;
    }

    @DeleteMapping(value = "/api/building/{id}")
    public void deleteBuilding(@PathVariable Long id){
        buildingService.deleteBuilding(id);
        System.out.println("delete oke");
    }
    
}