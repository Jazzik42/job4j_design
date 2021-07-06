package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Statement st;
    private String sql;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    private void initStatement() throws SQLException {
        if (st == null) {
            st = connection.createStatement();
        }
    }

    public void createTable(String tableName) throws SQLException {
        sql = String.format("Create table if not exists %s(%s%n);",
                tableName,
                "id serial primary key");
        st.execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        sql = String.format("drop table %s;",
                tableName);
        st.execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        sql = String.format("alter table %s add %s %s;",
                tableName,
                columnName,
                type);
        st.execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        sql = String.format("alter table %s drop %s;",
                tableName,
                columnName);
        st.execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        sql = String.format("alter table %s rename %s to %s;",
                tableName,
                columnName,
                newColumnName);
        st.execute(sql);
    }

    public String getScheme(Connection connect, String tableName) throws SQLException {
        DatabaseMetaData metaData = connect.getMetaData();
        StringBuilder scheme = new StringBuilder();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString(""),
                        columns.getString("")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void connection() throws ClassNotFoundException, SQLException {
        Class.forName(this.properties.getProperty("hibernate.connection.driver_class"));
        String url = this.properties.getProperty("hibernate.connection.url");
        String username = this.properties.getProperty("hibernate.connection.username");
        String pass = this.properties.getProperty("hibernate.connection.password");
        this.connection = DriverManager.getConnection(url, username, pass);
    }

    private static Properties getProperties() throws IOException {
        PropertiesLoadder propertiesLoadder = new PropertiesLoadder();
        ClassLoader classLoader = PropertiesLoadder.class.getClassLoader();
        try (InputStream io = classLoader.getResourceAsStream("app.properties")) {
            propertiesLoadder.load(io);
            return propertiesLoadder.getProperties();
        }
    }

    public static void main(String[] args) {
        try {
            Properties properties = TableEditor.getProperties();
            TableEditor tableEditor = new TableEditor(properties);
            tableEditor.connection();
            tableEditor.initStatement();
            tableEditor.dropTable("Cars");
            tableEditor.createTable("Cars");
            tableEditor.addColumn("Cars", "Name", "varchar(255)");
            tableEditor.addColumn("Cars", "Engine", "varchar(255)");
            tableEditor.dropColumn("Cars", "Engine");
            tableEditor.renameColumn("Cars", "Name", "mame");
            System.out.println(tableEditor.getScheme(tableEditor.connection, "Cars"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

