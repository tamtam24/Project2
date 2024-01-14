package com.tt24java.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.tt24java.repository.DistrictRepository;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    static final String DB_URL = "jdbc:mySQL://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "";

    @Override
    public String findDistrictNameById(int districtid) {
        String sql = "SELECT name FROM district WHERE id = " + districtid;
        String districtName = "";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                districtName = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return districtName;
    }
}