package com.tt24java.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tt24java.repository.RentAreaRepository;
import com.tt24java.repository.entity.rentareaEntity;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
    static final String DB_URL = "jdbc:mySQL://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "";

    @Override
    public String findRentAreaByBuildingId(int buildingId) {
        StringBuilder sql = new StringBuilder("SELECT value FROM rentarea WHERE buildingid = " + buildingId);
        List<rentareaEntity> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {

            while (rs.next()) {
                rentareaEntity rentarea = new rentareaEntity();
                rentarea.setValue(rs.getInt("value"));
                result.add(rentarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String rentAreas = "";
        for (rentareaEntity rentarea : result) {
            rentAreas += rentarea.getValue() + ",";
        }

        return rentAreas;
    }
}