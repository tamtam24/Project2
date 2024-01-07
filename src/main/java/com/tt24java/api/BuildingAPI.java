package com.tt24java.api;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tt24java.Beans.BuildingDTO;

@Controller
public class BuildingAPI {
	@RequestMapping(value="/api/building/",method=RequestMethod.GET)
	public void building(@RequestParam(value="name", required = false )String name,
						 @RequestParam(value="ID")String ID_number) {
		System.out.print(name);
	}
	
	
	
	@RequestMapping(value="/api/building/",method=RequestMethod.POST)
	public void building2(@RequestBody BuildingDTO buildingDTO ) {
		System.out.print("oke");
	}
	

}
