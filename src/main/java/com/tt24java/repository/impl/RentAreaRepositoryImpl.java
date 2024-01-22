package com.tt24java.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tt24java.repository.RentAreaRepository;
import com.tt24java.repository.entity.rentareaEntity;
import com.tt24java.utils.ConnectionJDBCUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

    @Override
    public List <rentareaEntity> findRentAreaByBuildingId(Long buildingId) {
    	
        String sql = "SELECT value FROM rentarea WHERE buildingid = " + buildingId;
        
        List<rentareaEntity> result = new ArrayList<>();
        try (Connection conn = ConnectionJDBCUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {

            while (rs.next()) {
                rentareaEntity rentarea = new rentareaEntity();
                rentarea.setValue(rs.getString("value"));
                result.add(rentarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }
}