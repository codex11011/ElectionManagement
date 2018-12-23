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

public class candidateFormHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

//		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Candidate n1 = new Candidate();
		CandidateDao dao = new CandidateDao();
			
			n1.setFirstName(request.getParameter("firstName"));
			n1.setFamilyName(request.getParameter("familyName"));
			n1.setEmail(request.getParameter("email"));
			n1.setPhoneNum(request.getParameter("PhoneNumber"));// long
			n1.setHostel(request.getParameter("hostel"));
			n1.setRoom(request.getParameter("roomNumber"));
			n1.setBatch(request.getParameter("batch"));
			n1.setBranch(request.getParameter("branch"));
			n1.setCgpa(request.getParameter("cgpa"));
			n1.setPosition(request.getParameter("position"));
			try {
				dao.saveNomineeDetails(n1);
			} catch (SQLException e) {
				
				e.printStackTrace();
			
		}
			HttpSession session = request.getSession();
			System.out.println(session.getAttribute("isRegistered"));
			session.setAttribute("candidate", n1);
			session.setAttribute("isRegistered", true);
			response.sendRedirect("jsp/candidateProfile.jsp");
	}
}
