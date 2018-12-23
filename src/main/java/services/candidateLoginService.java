package services;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.CandidateDao;
import models.OAuthModel;
import utils.ElectionConfig;

public class candidateLoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestData = request.getReader().lines().collect(Collectors.joining());

		Gson gson = new Gson();
		OAuthModel x = gson.fromJson(requestData, OAuthModel.class);
		String username = (String) x.getName().getGivenName();
		String familyname = (String) x.getName().getFamilyName();
		String emailId = (String) x.getEmails()[0].getValue();
		String domain = (String) x.getDomain();
		CandidateDao dao = new CandidateDao();
		if (domain == null) {
			response.sendRedirect("jsp/signOutRedirect.jsp");
		} else if (domain != null) {
			if (("lnmiit.ac.in").equals(domain)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setAttribute("familyname", familyname);
				session.setAttribute("email", emailId);
				session.setAttribute("domain", domain);
				session.setAttribute("isRegistered", false);
				session.setAttribute("registrationStatus", ElectionConfig.isRegistering());
				if(dao.isRegistered(emailId)) {
				session.setAttribute("isRegistered", true);	
				}
				response.sendRedirect("jsp/CandidateForm.jsp");
			} else {
				response.sendRedirect("jsp/signOutRedirect.jsp");
			}
		}

	}
}
