package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import BlockChain.BlockChain;
import BlockChain.Constants;
import dao.CandidateDao;
import dao.PositionsDao;
import dao.VoterDao;
import models.Block;
import models.Candidate;
import models.Position;
import models.Transaction;
import utils.Crypto;
import utils.ElectionConfig;
import utils.General;

/**
 * Servlet implementation class Vote
 */
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Position> positions;
	BlockChain chain;
	KeyPair keys;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		String jsonChain = null;
		positions = PositionsDao.getApprovedPositionList();
		try {
			jsonChain = new String(Files.readAllBytes(Paths.get("/home/shubham/chain")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonChain == null) {
			keys = Crypto.generateKeys();
			int max_amount = 0;
			for (int i = 0; i < positions.size(); i++) {
				int num = positions.get(i).getMaxWinners();
				max_amount += ((num) * (num + 1)) / 2;
			}
			System.out.println("Max Amount : " + max_amount);
			ArrayList<String> voterPublicKeys = VoterDao.getAllPublicKeys();
			chain = new BlockChain(null);
			Block block = new Block(General.getTimeStamp(), chain.head.hashBlock());
			for (int i = 0; i < voterPublicKeys.size(); i++) {
				Transaction tran = new Transaction(Crypto.getPublicKeyasString(keys), voterPublicKeys.get(i),
						max_amount, String.valueOf(i));
				tran.sign(Crypto.getPrivateKeyasString(keys));
				block.addTransaction(tran.toJSON());
			}
			block.setHeight(chain.head.getHeight() + 1);
			if (block.mine()) {
				Constants.addCode code = chain.addBlock(block, true);
				if (code == Constants.addCode.SUCCESS) {
					System.out.println("Successfully mined");
					// System.out.println(chain.toJSON());
				} else {
					System.out.println("An error occured while adding genesis: " + code);
				}
			}
			chain.addBlock(block, true);
			System.out.println("SYSTEM INIT COMPLETE! READY TO SERVE");
		}else {
			chain = BlockChain.fromJSON(jsonChain);
			System.out.println("INIT FROM CHAIN!");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		JsonObject json = new JsonObject();
		json.addProperty("chain", chain.toJSON());
		JsonArray arr = new JsonArray();
		for (int i1 = 0; i1 < positions.size(); i1++) {
			arr.add(positions.get(i1).toJSON());
		}
		json.addProperty("positions", arr.toString());
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		String object = request.getParameter("block");
		Block block = Block.fromJSON(object);
		JsonObject json = new JsonObject();
		Constants.addCode code = chain.addBlock(block, false);
		System.out.println("Got Post!");
		if (code == Constants.addCode.SUCCESS) {
			json.addProperty("success", "true");
			json.addProperty("message", "Successfully Cast Vote");
			PrintWriter pw = new PrintWriter(new FileOutputStream("/home/shubham/chain", false));
			pw.print(chain.toJSON());
			pw.close();
		} else {
			json.addProperty("success", "false");
			json.addProperty("message", code.toString());
			json.addProperty("chain", chain.toJSON());
			System.out.println(code.toString());
		}
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}

}