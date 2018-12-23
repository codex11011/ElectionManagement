package VotingApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;

public class VoterLoginWindow {

	// frame
	JFrame f;
	String id = null;
	String pwd = null;
	private JTextField usernameTextBox;
	public String response = null;
	private JPasswordField passwordField;

	// Constructor
	VoterLoginWindow() {
		// Frame initiallization
		f = new JFrame();

		// Frame Title
		f.setTitle("Login");

		f.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		// Frame Size
		f.setSize(500, 200);
		f.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("VoterID");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(116, 21, 80, 17);
		f.getContentPane().add(lblNewLabel);

		usernameTextBox = new JTextField();
		usernameTextBox.setBounds(243, 20, 135, 20);
		f.getContentPane().add(usernameTextBox);
		usernameTextBox.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(243, 61, 135, 19);
		f.getContentPane().add(passwordField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(116, 63, 81, 14);
		f.getContentPane().add(lblPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = usernameTextBox.getText();
				pwd = passwordField.getText();
				try {
					Map<String, Object> params = new LinkedHashMap<>();
					params.put("username", id);
					params.put("password", pwd);
					StringBuilder postData = new StringBuilder();
					for (Map.Entry<String, Object> param : params.entrySet()) {
						if (postData.length() != 0)
							postData.append('&');
						postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
						postData.append('=');
						postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
					}
					byte[] postDataBytes = postData.toString().getBytes("UTF-8");
					URL url = new URL(Constants.BASE_URL + "/VoterLogin");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
					conn.setDoOutput(true);
					conn.getOutputStream().write(postDataBytes);
					Reader in1 = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					StringBuilder sb = new StringBuilder();
					for (int c; (c = in1.read()) >= 0;)
						sb.append((char) c);
					response = sb.toString();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				f.dispose();
			}
		});
		btnLogin.setBounds(190, 127, 89, 23);
		f.getContentPane().add(btnLogin);
		
		
		// Frame Visible = true
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new VoterLoginWindow();
	}
}