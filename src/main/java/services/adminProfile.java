package services;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.DbConnection;
import models.Admin;

public class adminProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		Admin a1 = new Admin();
		AdminDao dao = new AdminDao();
		HttpSession session = request.getSession();
		String adminUsername = (String) session.getAttribute("adminUsername");
		try {
			a1 = dao.getAdminDetails(adminUsername);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		session.setAttribute("adminDetails", a1);
		if("/adminProfile/edit".equalsIgnoreCase(servletPath)) {
				response.sendRedirect("../jsp/editAdminDetails.jsp");
		}
		if("/adminProfile".equalsIgnoreCase(servletPath)) {				
			response.sendRedirect("jsp/adminProfile.jsp");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
