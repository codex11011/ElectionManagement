package services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDao;
import models.Candidate;

public class homePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CandidateDao dao = new CandidateDao();
		ArrayList<Candidate> list_candidate = dao.getFilteredList("1");
		HttpSession session = request.getSession();
		session.setAttribute("filterBtn", "1");
		session.setAttribute("candidates", list_candidate);
		response.sendRedirect("jsp/home.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
