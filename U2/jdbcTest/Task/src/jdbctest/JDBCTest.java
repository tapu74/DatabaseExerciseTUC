package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/***
 * Small demo to show the use of jdbc in connection with postresql
 * 
 * (C) 2017 TU Chemnitz / Datenverwaltungssysteme (DVS)
 * @author richard vogel, johannes fliege
 * 
 */
public class JDBCTest
{
        
        /**
         * Enter your Data here (Can be like exercise 1)
         */
        public static final String server = "pgsql.hrz.tu-chemnitz.de";
        public static final String user = "test_db_tapu_rw";
        public static final int port = 5432;
        public static final String dbName = "test_db_tapu";
        public static final String  password = "oolee7yia8U";
        public static final String query = "SELECT * from university";
         
        
	public static void main (String[] args)
	{
		try
		{
			// Load postgreSQL driver
			Class.forName("org.postgresql.Driver");

                        String connectionUrl = "jdbc:postgresql://"+JDBCTest.server+"/"+JDBCTest.dbName;

                        // 1. Establish connection
			Connection connection = DriverManager.getConnection(connectionUrl, JDBCTest.user, JDBCTest.password);
			
			// 2. Create statement
			Statement statement = connection.createStatement();
		
			// 3. Execute statement
			ResultSet result = statement.executeQuery(JDBCTest.query);
			
                     
                        // Create output like:
                        // ColumnName: value1, ColumnName: value2; ...
                        // ...
                        // Use function @see java.sql.ResultSet.getMetaData() des result column headers
 
                        
                        
                        // Read results
       			while (result.next()) 
			{
                                // Print values here
                            int id=result.getInt("id");
                            String name=result.getString("name");
                            String course=result.getString("course");
                            System.out.println(id+" "+name + " " +course);
                            
				
			}
            
			// 4. Free ressources 

                        result.close();
			statement.close();
			connection.close();

		} catch(ClassNotFoundException e)
		{
			// Handle exception for not found database driver
			System.out.println("JDBC-Driver postgres driver not found (Please add it to libraries)");
		} catch(SQLException e)
		{
                        // Catch SQL Errors
                        System.out.println("Error while talking to database");
			System.out.println(e.getMessage());
		}
	}
}