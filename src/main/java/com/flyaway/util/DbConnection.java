package com.flyaway.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	 static String driverName = "com.mysql.jdbc.Driver";
	 static  String connectionUrl = "jdbc:mysql://localhost:3306/";
	 static    String dbName = "flyaway";
	 static   String userId = "root";
	 static   String password = "mohit1234";
	    
	    
	    
	    public static Connection getConnection() {
	  	
	  	Connection  connection=null;
	    	try {
	    		  Class.forName(driverName);
	    		  connection=  DriverManager.getConnection(connectionUrl+dbName, userId, password);
			System.out.println(connection.toString());
	    	return connection;
	    	} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return connection;
	    }
	    
	   

}
