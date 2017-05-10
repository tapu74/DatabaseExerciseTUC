/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ix.jspTutorial.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author aktu
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private String server = "pgsql.hrz.tu-chemnitz.de";
    private String user = "test_db_tapu_rw";
    private int port = 5432;
    private String dbName = "test_db_tapu";
    private String password = "oolee7yia8U";
    private String url = "jdbc:postgresql://"+server+"/"+dbName;
  

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
    public void closeConnection() throws SQLException
    {
        connection.close();
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

}