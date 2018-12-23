package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import models.Candidate;


public class candidateFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestData = request.getReader().lines().collect(Collectors.joining());
	
		String filterbtnVal ="";
		dao.CandidateDao dao = new dao.CandidateDao();
		ArrayList<Candidate> list_candidate = dao.getFilteredList(requestData);
		if(requestData.equals("1")) {
			filterbtnVal = "accepted";
			
		}if(requestData.equals("-1")) {
			filterbtnVal = "rejected";
			
		}if(requestData.equals("0")) {
			filterbtnVal = "pending";
			
		}
		if(requestData.equals("all")) {
			filterbtnVal = "all";
			list_candidate = dao.getAllCandidateDetails();
		}
		HttpSession session = request.getSession();
		session.setAttribute("candidates", list_candidate);
		session.setAttribute("filterBtn", filterbtnVal);
		response.sendRedirect("jsp/candidateList.jsp");
		

	}

}
