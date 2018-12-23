package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ElectionConfig;
public class handleRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		HttpSession session = request.getSession();
		System.out.println(servletPath);
		if(servletPath.equalsIgnoreCase("/handleRegistration/start")) {
			ElectionConfig.startRegistering();
//			System.out.println("Registration Started");
//			session.setAttribute("registrationStatus",true);
			response.sendRedirect("../jsp/adminProfile.jsp");
			
		}else if(servletPath.equalsIgnoreCase("/handleRegistration/stop")) {
			ElectionConfig.stopRegistering();
//			System.out.println("Registration Stopped");
//			session.setAttribute("registrationStatus",false);
			response.sendRedirect("../jsp/adminProfile.jsp");
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
