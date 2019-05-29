package com.cg.paymentwallet.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import com.cg.paymentwallet.exception.CustomerException;

public class DButil {
	private static Connection conn;
	static FileInputStream fs = null;
	public static Connection getConnection() throws CustomerException {
		if(conn==null){
			try {
				fs = new FileInputStream("Resource/jdbc.properties");
				Properties prop = new Properties();
				prop.load(fs);
				String driver = prop.getProperty("driver");
				String url=prop.getProperty("url");
				String user =prop.getProperty("username");
				String pass =prop.getProperty("password");
				Class.forName(driver);
				System.out.println("Driver is  found");
				conn = DriverManager.getConnection(url,user,pass);  				
				System.err.print("Connection  is established");
			} catch (ClassNotFoundException e) {
				throw new CustomerException("Driver is  not found"+e.getMessage());
			} catch (SQLException e) {
				throw new CustomerException("Problem to establish connection "+e.getMessage());
			} catch (FileNotFoundException e) {
				System.err.println("Error in finding jdbc properties file");
			} catch (IOException e) {
				System.err.println("Error in fetching file data");
			}finally{
				if(fs != null){
					try {
						fs.close();
					} catch (IOException e) {
						System.err.println(e.getMessage());;
					}
				}
			}
		}
		return conn;
	}
}