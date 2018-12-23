package VotingApp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import BlockChain.BlockChain;
import models.Block;
import javax.swing.JLabel;

public class ConfirmationWindow {
	// frame
	JFrame f;

	// Constructor
	ConfirmationWindow(final BlockChain chain, final Block block, String message) {
		// Frame initiallization
		f = new JFrame();

		// Frame Title
		f.setTitle("Confirm!");
		// Frame Size
		f.setSize(500, 200);
		f.getContentPane().setLayout(null);
		JLabel messageBox = new JLabel("Please confirm your selection or restart app");
		messageBox.setBounds(60, 35, 387, 15);
		if(message != null) {
			messageBox.setText(message);
		}
		JButton btnSubmit = new JButton("CONFIRM");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(message != null) {
					System.exit(0);
					f.dispose();
				}
				try {
					block.setHeight(chain.head.getHeight() + 1);
					System.out.println("Starting to mine!");
					if (block.mine()) {
						System.out.println("Mined!");
						Map<String, Object> params = new LinkedHashMap<>();
						params.put("block", block.toJSON());
						StringBuilder postData = new StringBuilder();
						for (Map.Entry<String, Object> param : params.entrySet()) {
							if (postData.length() != 0)
								postData.append('&');
							postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
							postData.append('=');
							postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
						}
						byte[] postDataBytes = postData.toString().getBytes("UTF-8");
						URL url2 = new URL(Constants.BASE_URL + "/Vote");
						HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
						conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
						conn.setDoOutput(true);
						conn.getOutputStream().write(postDataBytes);
						Reader in1 = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
						StringBuilder sb = new StringBuilder();
						for (int c; (c = in1.read()) >= 0;)
							sb.append((char) c);
						String response2 = sb.toString();
						JsonParser parser = new JsonParser();
						JsonObject jsonObject = (JsonObject) parser.parse(response2);
						String success = jsonObject.get("success").getAsString();
						String message = jsonObject.get("message").getAsString();
						messageBox.setText(message);
						Thread.sleep(300);

					} else {
						System.out.println("Test Failed!");
					}
				} catch (Exception e) {

				}
				System.exit(0);
				f.dispose();
			}
		});
		btnSubmit.setBounds(196, 90, 120, 26);
		f.getContentPane().add(btnSubmit);
		
		
		f.getContentPane().add(messageBox);
		// Frame Visible = true
		f.setVisible(true);
	}
}