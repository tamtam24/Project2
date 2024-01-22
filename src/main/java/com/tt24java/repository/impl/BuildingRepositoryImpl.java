package com.tt24java.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tt24java.repository.BuildingRepository;
import com.tt24java.repository.entity.BuildingEntity;
import com.tt24java.utils.ConnectionJDBCUtil;
import com.tt24java.utils.NumberUtil;
import com.tt24java.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		String staffId = (String) params.get("staffId");
		if (StringUtil.checkString(staffId)) {
			sql.append("  INNER JOIN assignmentbuilding a ON b.id = a.buildingid ");
		}
		if (typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype br ON b.id = br.buildingid ");
			sql.append(" INNER JOIN renttype rt ON rt.id=br.renttypeid ");
		}
	}

	public static void queryNormal(Map<String, Object> params, StringBuilder where) {
		for (Map.Entry<String, Object> it : params.entrySet()) {
			if (!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area")
					&& !it.getKey().startsWith("rentPrice")) {
				String value = it.getValue().toString();

				if (StringUtil.checkString(value)) {
					if (NumberUtil.isNumber(value) == true) {
						where.append(" AND b." + it.getKey() + " = " + value);
					} else {
						where.append(" AND b." + it.getKey() + " LIKE '%" + value + "%' ");
					}
				}
			}

		}
	}

	public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffId = (String) params.get("staffId");
		if (StringUtil.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);

		}
		String rentAreaTo = (String) params.get("areaTo");
		String rentAreaFrom = (String) params.get("areaFrom");
		if (StringUtil.checkString(rentAreaFrom) == true || StringUtil.checkString(rentAreaTo) == true) {
			where.append(" AND EXISTS(SELECT * from rentarea r WHERE b.id=r.buildingid) ");
			if (StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND r.value >= " + rentAreaFrom);

			}
			if (StringUtil.checkString(rentAreaTo)) {
				where.append(" AND r.value <= " + rentAreaTo);

			}
			where.append(" ) ");
		}
		String rentPriceTo = (String) params.get("rentPriceTo");
		String rentPriceFrom = (String) params.get("rentPriceFrom");
		if (StringUtil.checkString(rentAreaFrom) == true || StringUtil.checkString(rentPriceTo) == true) {
			if (StringUtil.checkString(rentPriceFrom)) {
				where.append(" AND b.rentprice >= " + rentPriceFrom);

			}
			if (StringUtil.checkString(rentPriceTo)) {
				where.append(" AND b.rentprice <= " + rentPriceTo);

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
		if (typeCode != null && typeCode.size() != 0) {
			where.append(" AND ( ");
			String sql = typeCode.stream().map(it -> "renttype.code Like" + "'%" + it + "%'  ")
					.collect(Collectors.joining(" OR "));
			where.append(sql);
			where.append(" ) ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {

		StringBuilder sql = new StringBuilder("SELECT * FROM building b  ");
		joinTable(params, typeCode, sql);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		queryNormal(params, where);
		querySpecial(params, typeCode, where);
		where.append("GROUP BY b.id; ");
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

}
