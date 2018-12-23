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

public class candidateListService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CandidateDao dao = new CandidateDao();
		ArrayList<Candidate> list_candidate = dao.getAllCandidateDetails();
		HttpSession session = request.getSession();
		session.setAttribute("filterBtn", "all");
		session.setAttribute("candidates", list_candidate);
		response.sendRedirect("jsp/candidateList.jsp");
	}
}
