package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

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

import BlockChain.BlockChain;
import dao.PositionsDao;
import models.Candidate;
import models.Position;
import utils.ElectionConfig;

/**
 * Servlet implementation class countVotes
 */
public class countVotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public countVotes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jsonChain = null;
		try {
			jsonChain = new String(Files.readAllBytes(Paths.get("/home/shubham/chain")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(ElectionConfig.isVoting()) {
			System.out.println("Voting is going on currently");
		}else if(jsonChain == null) {
			System.out.println("There is no voting data");
		}else {
			// Vote results have seen sent to admin email
			StringBuilder sb = new StringBuilder(" ");
			BlockChain chain = BlockChain.fromJSON(jsonChain);
			ArrayList<Position> positions = PositionsDao.getApprovedPositionList();
			Iterator<Position> posit = positions.iterator();
			while(posit.hasNext()) {
				Position pos = posit.next();
				sb.append("<h4>"+pos.getName()+"</h4>"
						+ "<table>"
						+ "<tr>"
						+ "<th>Name of Candidate</th>"
						+ "<th>Number of Votes</th>"
						+ "</tr>");
				ArrayList<Candidate> candidates = pos.getCandidates();
				Iterator<Candidate> canit = candidates.iterator();
				while(canit.hasNext()) {
					Candidate can = canit.next();
					String publicKey = can.getWalletId();
					int votes = chain.getWalletAmount(publicKey);
					sb.append("<tr>"
							+ "<td>"+can.getFirstName()+" "+can.getFamilyName()+"</td>"
							+ "<td>"+votes+"</td></tr>");
				}
				sb.append("</table>");
			}
			try {
				countVotes.sendEmail(sb.toString());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("jsp/adminProfile.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected static void sendEmail(String resmessage)
			throws AddressException, MessagingException {
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
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("studentelectionlnmiit@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("studentelectionlnmiit@gmail.com"));
		message.setSubject("Student Election Results");

		String msg = resmessage;
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		message.setContent(multipart);
		Transport.send(message);
		System.out.println("Done!");
	}

}
