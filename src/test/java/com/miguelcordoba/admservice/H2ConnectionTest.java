package com.miguelcordoba.admservice;
import java.sql.Connection;
import java.sql.DriverManager;

public class H2ConnectionTest {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:admservice", "sa", "")) {
            System.out.println("Connected to H2 database successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}