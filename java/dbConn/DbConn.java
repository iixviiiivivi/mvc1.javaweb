package dbConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {

	// Connect MS SQL Server
	public static Connection getMSSqlConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=LoremIpsum";
		String user = "user";
		String password = "1234";
		Connection con = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // optional
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
		
		return con;
	}
	
	// Connect MySql Server
	public static Connection getMySqlConnection() {
		String url = "jdbc:mysql://localhost:3306/sample?serverTimezone=UTC";
		String user = "root";
		String password = "1234";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Error");
			e.printStackTrace();
		}
		
		return con;
	}

}
