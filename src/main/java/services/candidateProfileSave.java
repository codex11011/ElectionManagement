package services;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDao;
import models.Candidate;
public class candidateProfileSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Candidate c1 = new Candidate();
		CandidateDao dao = new CandidateDao();
		HttpSession session = request.getSession();
		String email =(String) session.getAttribute("email");
		c1.setPhoneNum((String)request.getParameter("PhoneNumber"));
		c1.setBatch((String) request.getParameter("batch"));
		c1.setBranch((String) request.getParameter("branch"));
		c1.setCgpa((String) request.getParameter("cgpa"));
		c1.setHostel((String) request.getParameter("hostel"));
		c1.setRoom((String) request.getParameter("roomNumber"));
		c1.setPosition((String) request.getParameter("position"));
		c1.setFamilyName((String) request.getParameter("familyName"));
		c1.setFirstName((String) request.getParameter("firstName"));
		c1.setEmail((String) request.getParameter("email"));
		try {
			dao.updateCandidateDetails(c1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("candidate", c1);
		response.sendRedirect("../jsp/candidateProfile.jsp");
		
	}

}
