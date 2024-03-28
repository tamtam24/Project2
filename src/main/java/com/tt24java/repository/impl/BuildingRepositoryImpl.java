package com.tt24java.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tt24java.builder.BuildingSearchBuilder;
import com.tt24java.repository.BuildingRepository;
import com.tt24java.repository.entity.BuildingEntity;
import com.tt24java.utils.ConnectionJDBCUtil;
import com.tt24java.utils.NumberUtil;
import com.tt24java.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId!=null) {
			sql.append("  INNER JOIN assignmentbuilding a ON b.id = a.buildingid ");
		}
		List<String>typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype  ON renttype.id=buildingrenttype.renttypeid ");
		}
	}

	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		

		try {
			Field[]fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item:fields) {
				item.setAccessible(true);
				String fieldName=item.getName();
				if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
					&& !fieldName.startsWith("rentPrice")) {
					Object value = item.get(buildingSearchBuilder);
					if (value!=null) {
						if (item.getType().getName().equals("java.lang.Long")||item.getType().getName().equals("java.lang.Integer")||item.getType().getName().equals("java.lang.Float")) {
							where.append(" AND b." + fieldName + " = " + value);
						} else if(item.getType().getName().equals("java.lang.String")) {
							where.append(" AND b." + fieldName+ " LIKE '%" + value + "%' ");
						}
				}
				
			}
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
	
		Long staffId = buildingSearchBuilder.getStaffId();
		
		if (staffId!=null) {
			where.append(" AND assignmentbuilding.staffid = " + staffId + " ");

		
		}
		Long rentAreaTo = buildingSearchBuilder.getAreaTo();
		Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
		if (rentAreaTo!=null || rentAreaFrom!=null) {
			where.append(" AND EXISTS ( SELECT * from rentarea r WHERE b.id = r.buildingid ");
			if (rentAreaFrom!=null) {
				where.append(" AND r.value >= " + rentAreaFrom);

			}
			if (rentAreaTo!=null) {
				where.append(" AND r.value <= " + rentAreaTo);

			}
			where.append(" ) ");
		}
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		if (rentPriceTo!=null || rentPriceFrom!=null) {
			if (rentPriceFrom!=null) {
				where.append(" AND b.rentprice >=" + rentPriceFrom);

			}
			if (rentPriceTo!=null) {
				where.append(" AND b.rentprice <=" + rentPriceTo);

			}
		}
		// java 7
//		if (typeCode != null && typeCode.size() != 0) {
//			List<String>code = new ArrayList<>();
//			for( String item:typeCode) {
//				code.add("'" + item + "'");
//			}
//			where.append(" AND renttype.code IN ( " + String.join(",", code) + ") ");
//		}
        // java 8
		List<String>typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			where.append(" AND (");
			String sql = typeCode.stream().map(it -> "renttype.code Like" + "'%" + it + "%'  ")
					.collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
	}


	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {

		StringBuilder sql = new StringBuilder(" SELECT * FROM building b  ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		where.append(" GROUP BY b.id; ");
		sql.append(where);

		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {

			while (rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictid(rs.getLong("districtid"));
				building.setStructure(rs.getString("structure"));
				building.setNumberofbasement(rs.getInt("numberofbasement"));
				building.setFloorarea(rs.getLong("floorarea"));
				building.setDirection(rs.getString("direction"));
				building.setLevel(rs.getString("level"));
				building.setRentprice(rs.getLong("rentprice"));
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

	@Override
	public BuildingEntity findBuildingById(Long id){
		String sql =  "SELECT * from building where id="+id;
		BuildingEntity building = new BuildingEntity();
		try (Connection conn = ConnectionJDBCUtil.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {

				building.setId(rs.getLong("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictid(rs.getLong("districtid"));
				building.setStructure(rs.getString("structure"));
				building.setNumberofbasement(rs.getInt("numberofbasement"));
				building.setFloorarea(rs.getLong("floorarea"));
				building.setDirection(rs.getString("direction"));
				building.setLevel(rs.getString("level"));
				building.setRentprice(rs.getLong("rentprice"));
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return building;
	}


	@Override
	public void deleteBuildingByEntity(BuildingEntity buildingEntity) {
		Long id = buildingEntity.getId();
		String sql = "DELETE FROM building WHERE id = " + id;
		try (Connection conn = ConnectionJDBCUtil.getConnection();
			 Statement stmt = conn.createStatement()) {
			int rowsAffected = stmt.executeUpdate(sql);
			if (rowsAffected > 0) {
				System.out.println("Building with ID " + id + " deleted successfully.");
			} else {
				System.out.println("No building found with ID " + id + ". Nothing deleted.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
