package services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VoterDao;
import models.Voter;

/**
 * Servlet implementation class sendVoterEmail
 */
public class sendVoterEmail extends HttpServlet {

	static ArrayList<String> emails;

	public static void main(String[] args) throws SQLException {
		emails = new ArrayList<String>();
		emails.add("gargarajat@outlook.com");
		emails.add("gargarajat@gmail.com");
		emails.add("16ucs152@lnmiit.ac.in");
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("studentelectionlnmiit", "123@qwerty");
			}
		});
		for (int i = 0; i < emails.size(); i++) {
			String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			StringBuilder salt = new StringBuilder();
			Random rnd = new Random();
			while (salt.length() < 7) { // length of the random string.
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			String generatedPwd = salt.toString();
			try {
				sendVoterEmail.sendEmail(generatedPwd, emails.get(i), session);
				Voter voter = new Voter(emails.get(i), generatedPwd);
				VoterDao.saveVoter(voter);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Main Done!");
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sendVoterEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VoterDao.clearTable();
		// TODO Auto-generated method stub
		emails = new ArrayList<String>();
		emails.add("gargarajat@outlook.com");
		emails.add("gargarajat@gmail.com");
		emails.add("16ucs152@lnmiit.ac.in");
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("studentelectionlnmiit", "123@qwerty");
			}
		});
		for (int i = 0; i < emails.size(); i++) {
			String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			StringBuilder salt = new StringBuilder();
			Random rnd = new Random();
			while (salt.length() < 7) { // length of the random string.
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			String generatedPwd = salt.toString();
			try {
				sendVoterEmail.sendEmail(generatedPwd, emails.get(i), session);
				Voter voter = new Voter(emails.get(i), generatedPwd);
				try {
					VoterDao.saveVoter(voter);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HttpSession sessionVar = request.getSession();
		response.sendRedirect("jsp/adminProfile.jsp");


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected static void sendEmail(String password, String receiver, Session session)
			throws AddressException, MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("studentelectionlnmiit@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
		message.setSubject("Student Election Credentials");

		String msg = "<p>" + "Following are the credentials for your login to voting portal<br>" + "username : "
				+ receiver + "<br>password : " + password
				+ "<br>Kindly save this information at a secure place, it cannot be recovered or changed</p>";
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		message.setContent(multipart);
		Transport.send(message);
		System.out.println("Done!");
	}
}