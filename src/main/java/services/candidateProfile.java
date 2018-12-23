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
public class candidateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		CandidateDao dao = new CandidateDao();
		Candidate c1 = new Candidate();
		System.out.println(servletPath);
		try {
			c1 = dao.getCandidateDetail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("candidate",c1);
		if("/candidateProfile/edit".equalsIgnoreCase(servletPath)) {
			response.sendRedirect("../jsp/editCandidateDetails.jsp");
		}
		if("/candidateProfile".equalsIgnoreCase(servletPath)) {				
			response.sendRedirect("jsp/candidateProfile.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		CandidateDao dao = new CandidateDao();
			try {
				dao.updateManifestoLink(email,(String)request.getParameter("manifesto"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Candidate c1 = new Candidate();
			try {
				c1 = dao.getCandidateDetail(email);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("candidate",c1);
			response.sendRedirect("../../jsp/candidateProfile.jsp");
		}
	

}
