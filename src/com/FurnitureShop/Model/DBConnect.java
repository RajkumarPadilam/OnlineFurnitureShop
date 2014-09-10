package com.FurnitureShop.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class gets back a connection object to database
 * @author Team Mavericks
 *
 */
public class DBConnect 
{
	
	private final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
    private final String DB_URL="jdbc:mysql://localhost/FURNITURE_SHOP";   
    private final String USER = "root";
    private final String PASS = "";
    private Connection connection;
    
    /**
     * Connects to the Database and returns a Connection object
     * @return Connection object
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection getConnectionToDatabase() throws SQLException, ClassNotFoundException
    {
    	
		Class.forName(JDBC_DRIVER);
		connection=DriverManager.getConnection(DB_URL,USER,PASS);
		
		return connection;
    }
    
}
