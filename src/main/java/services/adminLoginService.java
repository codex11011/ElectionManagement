package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class adminLoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response
			) throws ServletException, IOException {
		String username = (String) request.getParameter("adminUsername");
		String email = (String) request.getParameter("adminEmail");
		String password = (String) request.getParameter("adminPassword");
		dao.AdminDao dao = new dao.AdminDao();
		
		if(dao.authenticate(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("adminEmail", email);
			session.setAttribute("adminUsername", username);
			response.sendRedirect("candidateList");
			
		}else {
			response.sendRedirect("jsp/login.jsp");
		}
	}
}