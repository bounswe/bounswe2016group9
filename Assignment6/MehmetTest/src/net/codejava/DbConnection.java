package net.codejava;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DbConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("here");
		}catch(ClassNotFoundException e){
		
		}
		
		String url = "jdbc:mysql://localhost/mehmettest";
		String user = "root";
		String password = "";
		Connection conn = null;
		try{
			conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Success");
		}
		catch(SQLException e){
			System.out.println("Something went wrong");
		}
		
				
	}

}
