package models;

import com.google.gson.Gson;

public class Candidate extends User {
	private String batch;
	private String walletId = null;
	private String manifestoId = null;
	private String position;
	private String cgpa;
	private String hostel;
	private String room;
	private String phoneNum;
	private String status = "true";
	private String branch;

	public Candidate(String firstName, String familyName, String email, String batch,String walletId,String manifestoId,
			String position, String cgpa, String hostel, String room, String phoneNum, String branch) {
		super(firstName, familyName, email);
		this.batch = batch;
		this.walletId = walletId;
		this.manifestoId = manifestoId;
		this.position = position;
		this.cgpa = cgpa;
		this.hostel = hostel;
		this.room = room;
		this.phoneNum = phoneNum;
		this.branch = branch;
	}

	public Candidate() {
		// TODO Auto-generated constructor stub
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public String getManifestoId() {
		return manifestoId;
	}

	public void setManifestoId(String manifestoId) {
		this.manifestoId = manifestoId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCgpa() {
		return cgpa;
	}

	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}

	public String getHostel() {
		return hostel;
	}

	public void setHostel(String hostel) {
		this.hostel = hostel;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this).toString();
	}
}
