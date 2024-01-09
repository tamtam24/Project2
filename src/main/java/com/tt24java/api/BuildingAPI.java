package com.tt24java.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tt24java.model.BuildingDTO;
import com.tt24java.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	
	private BuildingService buildingService;


	@GetMapping(value="/api/building/")
	public List<BuildingDTO>getBuilding(@RequestParam(name="name",required = false)String name,
			                           @RequestParam(name="floorarea",required = false)int floorarea,
			                           @RequestParam(name="districtid", required = false)Long district,
			                           @RequestParam(name="ward",required = false)String ward,
			                           @RequestParam(name="street",required = false)String street,
			                           @RequestParam(name="numberofbasement",required = false)int numberofbasement,
			                           @RequestParam(name="direction",required = false)String direction,
			                           @RequestParam(name="level",required = false)String level,
			                           @RequestParam(name="rentareafrom",required = false)int rentareafrom,
			                           @RequestParam(name="rentareato",required = false)int rentareato,
			                           @RequestParam(name="rentpricefrom",required = false)int rentpricefrom,
			                           @RequestParam(name="rentpriceto",required = false)int rentpriceto,
			                           @RequestParam(name="typeCode", required = false)List<String>typeCode
			                           ){
		List<BuildingDTO>result= buildingService.findAll(name,district);
		return result;
	}
	
}	


