package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDao;
import models.Candidate;

public class candidateRequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String email = (String)request.getParameter("email");
		String status = (String) request.getParameter("status");
		String updatedStatus = null ;
		CandidateDao dao = new CandidateDao();
		if("/candidateRequest/accept".equalsIgnoreCase(servletPath) && status != "1") {
			try {
				dao.handleCandidateRequest(email,"1");
				updatedStatus = "1";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if("/candidateRequest/reject".equalsIgnoreCase(servletPath) && status != "-1") {
			try {
				dao.handleCandidateRequest(email,"-1");
				updatedStatus = "-1";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ArrayList<Candidate> list_candidate = dao.getAllCandidateDetails();
		HttpSession session = request.getSession();
		session.setAttribute("filterBtn", "all");
		session.setAttribute("candidates", list_candidate);
		response.sendRedirect("../jsp/candidateList.jsp");
	}

}
