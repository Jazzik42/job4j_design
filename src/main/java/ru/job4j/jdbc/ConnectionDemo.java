package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ClassLoader classLoader = PropertiesLoadder.class.getClassLoader();
        PropertiesLoadder ps = new PropertiesLoadder();
        try (InputStream inputSteam = classLoader.getResourceAsStream("app.properties")) {
           ps.load(inputSteam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String driver = ps.getValues("hibernate.connection.driver_class");
    Class.forName(driver);
    String url = ps.getValues("hibernate.connection.url");
    String login = ps.getValues("hibernate.connection.username");
    String password = ps.getValues("hibernate.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
