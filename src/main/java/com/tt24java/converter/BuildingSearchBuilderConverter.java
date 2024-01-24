package com.tt24java.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tt24java.builder.BuildingSearchBuilder;
import com.tt24java.utils.MapUtil;
@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder().setName(MapUtil.getObject(params, "name", String.class))
				                                                                         .setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
				                                                                         .setWard(MapUtil.getObject(params, "ward", String.class))
				                                                                         .setStreet(MapUtil.getObject(params, "street", String.class))
				                                                                         .setDistrictId(MapUtil.getObject(params, "districtcode", Long.class))
				                                                                         .setNumberofBasement(MapUtil.getObject(params, "numberofbasement", Integer.class))
				                                                                         .setTypeCode(typeCode)
				                                                                         .setManagerName(MapUtil.getObject(params, "managername", String.class))
				                                                                         .setManagerPhoneNumber(MapUtil.getObject(params, "managerphonenumber", String.class))
				                                                                         .setRentPriceTo(MapUtil.getObject(params, "rentpriceto", Long.class))
				                                                                         .setRentPriceFrom(MapUtil.getObject(params, "rentpricefrom", Long.class))
				                                                                         .setAreaFrom(MapUtil.getObject(params, "areafrom", Long.class))
				                                                                         .setAreaTo(MapUtil.getObject(params, "areato", Long.class))
				                                                                         .setStaffId(MapUtil.getObject(params, "staffid", Long.class))                                                                       
				                                                                         .build();
		

		return null;
	}

}
