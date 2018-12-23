package dao;

import java.security.KeyPair;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Candidate;
import utils.Crypto;

public class CandidateDao {
	private static Connection conn = DbConnection.getConn();

	public void saveNomineeDetails(Candidate n1) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql1 = "INSERT INTO Users VALUES('"+ (String) n1.getEmail()+ "','"+ n1.getFirstName() +"','"+n1.getFamilyName()+"');";
		KeyPair keys = Crypto.generateKeys();
		String sql2 = "INSERT INTO Candidate(phone_num,hostel,room_num,batch,branch,cgpa,position,email_id,wallet_id,manifesto_id) VALUES('"
				+ n1.getPhoneNum() + "','" + n1.getHostel() + "','" + n1.getRoom() + "','" + n1.getBatch() + "','"
				+ n1.getBranch() + "','" + n1.getCgpa() + "','" + n1.getPosition() +"','"+n1.getEmail()+"','"+  Crypto.getPublicKeyasString(keys) +"','"+ "" + "');";
		stmt.executeUpdate(sql1);
		stmt.executeUpdate(sql2);

	}
	
	public static ArrayList<Candidate> getApprovedCandidates() {
		return new CandidateDao().getFilteredList("1");
	}


	public ArrayList<Candidate> getAllCandidateDetails() {
		String sql = "select Users.first_name,Users.family_name,Candidate.* from Users INNER JOIN Candidate ON Users.email_id = Candidate.email_id;";
		ArrayList<Candidate> candidate_details = new ArrayList<Candidate>();
		try {
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Candidate n1 = new Candidate();
				n1.setFirstName(rs.getString(1));
				n1.setFamilyName(rs.getString(2));
				n1.setEmail(rs.getString(3));
				n1.setBatch(rs.getString(4));
				n1.setWalletId(rs.getString(5));
				n1.setManifestoId(rs.getString(6));
				n1.setPosition(rs.getString(7));
				n1.setCgpa(rs.getString(8));
				n1.setHostel(rs.getString(9));				
				n1.setRoom(rs.getString(10));
				n1.setPhoneNum(rs.getString(11));
				n1.setBranch(rs.getString(12));
				n1.setStatus(rs.getString(13));
				candidate_details.add(n1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		return candidate_details;

	}
	
	public void handleCandidateRequest(String email,String status) throws SQLException {
		String sql = "update Candidate SET status='"+status+"' where email_id='"+email+"'";
		conn = DbConnection.getConn();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		
	}
	
	public ArrayList<Candidate> getFilteredList(String requestData) {
		String sql = "select Users.first_name,Users.family_name,Candidate.* from Users INNER JOIN Candidate ON Users.email_id = Candidate.email_id;";
		ArrayList<Candidate> candidate_details = new ArrayList<Candidate>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				if(requestData.equals(rs.getString(13))){			
					Candidate n1 = new Candidate();
					n1.setFirstName(rs.getString(1));
					n1.setFamilyName(rs.getString(2));
					n1.setEmail(rs.getString(3));
					n1.setBatch(rs.getString(4));
					n1.setWalletId(rs.getString(5));
					n1.setManifestoId(rs.getString(6));
					n1.setPosition(rs.getString(7));
					n1.setCgpa(rs.getString(8));
					n1.setHostel(rs.getString(9));				
					n1.setRoom(rs.getString(10));
					n1.setPhoneNum(rs.getString(11));
					n1.setBranch(rs.getString(12));
					n1.setStatus(rs.getString(13));
					candidate_details.add(n1);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}
		return candidate_details;

	}
	
	public boolean isRegistered(String email_id) {
		String sql = "select * from Candidate where email_id=?";
		try { 
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Candidate getCandidateDetail(String email_id) throws SQLException {
		String sql = "select Users.first_name,Users.family_name,Candidate.* from Users INNER JOIN Candidate ON Users.email_id = Candidate.email_id where Candidate.email_id=?;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email_id);
		ResultSet rs = stmt.executeQuery();
		Candidate n1 = new Candidate();
		if(rs.next()) {
			n1.setFirstName(rs.getString(1));
			n1.setFamilyName(rs.getString(2));
			n1.setEmail(rs.getString(3));
			n1.setBatch(rs.getString(4));
			n1.setWalletId(rs.getString(5));
			n1.setManifestoId(rs.getString(6));
			n1.setPosition(rs.getString(7));
			n1.setCgpa(rs.getString(8));
			n1.setHostel(rs.getString(9));				
			n1.setRoom(rs.getString(10));
			n1.setPhoneNum(rs.getString(11));
			n1.setBranch(rs.getString(12));
			n1.setStatus(rs.getString(13));
		}
		return n1;
		
	}
	public void updateCandidateDetails(Candidate c1) throws SQLException {
		String sql = "update Candidate,Users SET first_name='"+c1.getFirstName() +"',family_name='"+ c1.getFamilyName()+"',cgpa='"+c1.getCgpa()+"',branch='"+c1.getBranch()+"',phone_num='"+c1.getPhoneNum()+"',room_num='"+c1.getRoom()+"',position='"+c1.getPosition()+"',batch='"+c1.getBatch()+"',hostel='"+c1.getHostel()+"' where Users.email_id='"+ c1.getEmail()+"' and Candidate.email_id='"+ c1.getEmail()+"';";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	}
	public void updateManifestoLink(String email,String manifestoLink) throws SQLException {
		String sql = "update Candidate SET manifesto_id='"+manifestoLink+"' where email_id='"+email+"';";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	};
}
