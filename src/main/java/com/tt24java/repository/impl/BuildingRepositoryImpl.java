package com.tt24java.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tt24java.repository.BuildingRepository;
import com.tt24java.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	static final String DB_URL = "jdbc:mySQL://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "";

	
	private String buildSqlQuery(Map<String, Object> params) {
		StringBuilder sql = new StringBuilder("SELECT * FROM building b WHERE 1=1 ");

		if (params.get("name") != null && !params.get("name").equals("")) {
			sql.append(" AND b.name like '%" + params.get("name") + "%' ");
		}
		if (params.get("districtid") != null) {
			sql.append(" AND b.districtid= " + params.get("districtid") + " ");
		}
		if (params.get("floorarea") != null) {
			sql.append(" AND b.floorarea= " + params.get("floorarea") + " ");
		}
		if (params.get("ward") != null) {
			sql.append(" AND b.ward like '%" + params.get("ward") + "%' ");
		}
		if (params.get("street") != null) {
			sql.append(" AND b.street like '%" + params.get("street") + "%' ");
		}
		if (params.get("numberofbasement") != null) {
			sql.append(" AND b.numberofbasement= " + params.get("numberofbasement") + " ");
		}
		if (params.get("direction") != null) {
			sql.append(" AND b.direction like '%" + params.get("direction") + "%' ");
		}
		if (params.get("level") != null) {
			sql.append(" AND b.level like '%" + params.get("level") + "%' ");
		}
		if (params.get("managername") != null) {
			sql.append(" AND b.managername like '%" + params.get("managername") + "%' ");
		}
		if (params.get("managerphonenumber") != null) {
			sql.append(" AND b.managerphonenumber like '%" + params.get("managerphonenumber") + "%' ");
		}
		if (params.get("rentpricefrom") != null) {
			sql.append(" AND b.rentprice >=" + params.get("rentpricefrom") + " ");
		}
		if (params.get("rentpriceto") != null) {
			sql.append(" AND b.rentprice <=" + params.get("rentpriceto") + " ");
		}
		if (params.get("rentareafrom") != null) {
			sql.append(" AND r.value >= " + params.get("rentareafrom") + " ");
		}
		if (params.get("rentareato") != null) {
			sql.append(" AND r.value <=" + params.get("rentareato") + " ");
		}
		if (params.get("staffid") != null) {
			sql.append(" AND u.staffid= " + params.get("staffid") + " ");
		}
		if (params.get("typeCode") != null) {
			sql.append(" AND rt.code like '%" + params.get("typeCode") + "%' ");
		}
		return sql.toString();
	}

	private void joinTables(StringBuilder sql, Map<String, Object> params) {
		if (params.get("rentareafrom") != null || params.get("rentareato") != null) {
			sql.append(" INNER JOIN rentarea r ON b.id = r.buildingid ");
		}
		if (params.get("nameofemployee") != null) {
			sql.append(" INNER JOIN assignmentbuilding a ON b.id = a.buildingid INNER JOIN user u ON u.id=a.staffid ");
		}
		if (params.get("renttype") != null) {
			sql.append(
					" INNER JOIN buildingrenttype br ON b.id = br.buildingid INNER JOIN renttype rt ON rt.id=br.renttypeid ");
		}

	}
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params) {

		StringBuilder sql = new StringBuilder(buildSqlQuery(params));
		joinTables(sql, params);

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {

			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getInt("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictid(rs.getInt("districtid"));
				building.setStructure(rs.getString("structure"));
				building.setNumberofbasement(rs.getInt("numberofbasement"));
				building.setFloorarea(rs.getInt("floorarea"));
				building.setDirection(rs.getString("direction"));
				building.setLevel(rs.getString("level"));
				building.setRentprice(rs.getInt("rentprice"));
				building.setRentpricedescription(rs.getString("rentpricedescription"));
				building.setServicefee(rs.getString("servicefee"));
				building.setCarfee(rs.getString("carfee"));
				building.setMotorbikefee(rs.getString("motorbikefee"));
				building.setOvertimefee(rs.getString("overtimefee"));
				building.setElectricityfee(rs.getString("electricityfee"));
				building.setDeposit(rs.getString("deposit"));
				building.setPayment(rs.getString("payment"));
				building.setRenttime(rs.getString("renttime"));
				building.setDecorationtime(rs.getString("decorationtime"));
				building.setBrokeragefee(rs.getFloat("brokeragefee"));
				building.setNote(rs.getString("note"));
				building.setLinkofbuilding(rs.getString("linkofbuilding"));
				building.setMap(rs.getString("map"));
				building.setImage(rs.getString("image"));
				building.setCreatedate(rs.getDate("createddate"));
				building.setModifieddate(rs.getDate("modifieddate"));
				building.setCreatedby(rs.getString("createdby"));
				building.setModifiedby(rs.getString("modifiedby"));
				building.setManagername(rs.getString("managername"));
				building.setManagerphonenumber(rs.getString("managerphonenumber"));
				result.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}