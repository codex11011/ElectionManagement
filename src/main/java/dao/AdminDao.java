package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Admin;

public class AdminDao {
	private Connection conn = DbConnection.getConn();
	public boolean authenticate(String userName, String pwd) {
		String sql = "select * from Admin where username=? and pwdhash=?";
		try { 
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, pwd);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Admin getAdminDetails(String username) throws SQLException {
		String sql="select Users.*,Admin.username from Users INNER JOIN Admin ON Users.email_id = Admin.email_id where Admin.username='"+username+"';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Admin a1 = new Admin();
		while(rs.next()) {
			a1.setEmail(rs.getString(1));
			a1.setFirstName(rs.getString(2));
			a1.setFamilyName(rs.getString(3));
			a1.setUserName(rs.getString(4));
		}
		return a1; 
	}
	
	public void updateAdminDetails(Admin a1,String prevEmail) throws SQLException {
		String sql = "update Admin,Users SET first_name='"+a1.getFirstName() +"',family_name='"+ a1.getFamilyName()+"',username='"+a1.getUserName()+"',Users.email_id='"+a1.getEmail()+"',Admin.email_id='"+a1.getEmail()+"' where Admin.email_id='"+ prevEmail +"' and Users.email_id ='"+prevEmail+"';";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		
	
	}	
	
}
