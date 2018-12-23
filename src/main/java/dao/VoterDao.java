package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Voter;

public class VoterDao {
	private static Connection conn = null;
	
	public static void clearTable() {
		conn = DbConnection.getConn();
		String sql = "truncate table Voter";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Voter getVoter(String username) {
		conn = DbConnection.getConn();
		Voter v = new Voter();
		String sql = "select * from Voter where username=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				v.setPwdHash(rs.getString(2));
				v.setUsername(rs.getString(1));
				v.setPublicKey(rs.getString(3));
				v.setPrivateKey(rs.getString(4));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	public static void saveVoter(Voter v) throws SQLException {
		conn = DbConnection.getConn();

		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO Voter(username,pwdhash,public_key,private_key) VALUES('" + v.getUsername() + "','"
				+ v.getPwdHash() + "','" + v.getPublicKey() + "','" + v.getPrivateKey() + "');";
		stmt.executeUpdate(sql);
	}

	public static ArrayList<String> getAllPublicKeys() {
		conn = DbConnection.getConn();
		ArrayList<String> pks = new ArrayList<String>();
		String sql = "select * from Voter";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				pks.add(rs.getString(3));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pks;
	}
}