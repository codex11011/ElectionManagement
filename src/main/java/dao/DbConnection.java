package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
	public static Connection getConn(){
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DBStatic.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBStatic.DB_URL,
											DBStatic.USER,
											DBStatic.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	
}
