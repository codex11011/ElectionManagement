package services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ElectionConfig;

public class handleVoting extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		HttpSession session = request.getSession();
		
		if(servletPath.equalsIgnoreCase("/handleVoting/start")) {
			ElectionConfig.startVoting();
//			System.out.println("voting Started");
			session.setAttribute("votingStatus",true);
			response.sendRedirect("../jsp/adminProfile.jsp");
			
		}else if(servletPath.equalsIgnoreCase("/handleVoting/stop")) {
			ElectionConfig.stopVoting();
//			System.out.println("voting Stopped");
			session.setAttribute("votingStatus",false);
			response.sendRedirect("../jsp/adminProfile.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
