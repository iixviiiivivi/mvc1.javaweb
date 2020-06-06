package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public interface iDAO {
	
	// only works on Java 1.8
	static Connection getMSSqlConnection() {
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
	
	// hibernate database connection and orm
	static Session getSession() {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		return session;		
	}
	
	// CRUD (create, read, update, delete) methods
	void add(Object obj);
	Object querySingle(int id);
	void updateAll(int id, Object obj);
	void delete(int id);	
}
