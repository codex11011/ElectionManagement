package services;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import models.Admin;

public class adminProfileSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin a1 = new Admin();
		AdminDao dao = new AdminDao();
		a1.setFirstName((String) request.getParameter("adminFirstName"));
		a1.setFamilyName((String) request.getParameter("adminFamilyName"));
		a1.setUserName((String) request.getParameter("adminUsername"));
		a1.setEmail((String) request.getParameter("adminEmail"));
		String prevEmail = (String) request.getParameter("prevEmail");
		try {
			dao.updateAdminDetails(a1, prevEmail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("adminEmail", a1.getEmail());
		session.setAttribute("adminUsername", a1.getUserName());
		session.setAttribute("adminDetails", a1);
		response.sendRedirect("../jsp/adminProfile.jsp");
		
	}

}
