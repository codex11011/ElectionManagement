package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.VoterDao;
import models.Voter;
import utils.ElectionConfig;

/**
 * Servlet implementation class VoterLogin
 */
public class VoterLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoterLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		JsonObject json = new JsonObject();
		if (!ElectionConfig.isVoting()) {
			json.addProperty("success", "false");
			json.addProperty("message", "Voting is not active");
		} else {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Voter voter = null;
			voter = VoterDao.getVoter(username);
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(password.getBytes("UTF-8"));
				StringBuilder string = new StringBuilder();
				for (byte b : hash) {
					int val = b;
					for (int i = 0; i < 8; i++) {
						string.append((val & 128) == 0 ? 0 : 1);
						val <<= 1;
					}
				}
				password = string.toString();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}if (password.matches(voter.getPwdHash())) {
				json.addProperty("success", "true");
				json.addProperty("message", "Successfully Logged In");
				json.addProperty("publicKey", voter.getPublicKey());
				json.addProperty("privateKey", voter.getPrivateKey());
			} else {
				json.addProperty("success", "false");
				json.addProperty("message", "Username and/or Password is Incorrect");
			}
		}
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}

}