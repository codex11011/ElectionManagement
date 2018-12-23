package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import models.Position;

public class ElectionConfig {

	public static void main(String[] args) {

		ElectionConfig.makeFile();
	}

	public static ArrayList<Position> getPositions() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/shubham/electionConfig.xml");

		ArrayList<Position> positions = new ArrayList<Position>();

		Document document;
		try {
			document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			List posList = rootNode.getChild("positions").getChildren("pos");
			for (int i = 0; i < posList.size(); i++) {
				Element node = (Element) posList.get(i);
				positions.add(
						new Position(node.getChildText("Name"), Integer.parseInt(node.getChildText("maxWinners"))));
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return positions;
	}

	public static boolean isRegistering() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/shubham/electionConfig.xml");
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			if (rootNode.getChildText("isRegistering").matches("true")) {
				return true;
			}

		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean startRegistering() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/shubham/electionConfig.xml");
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			rootNode.getChild("isRegistering").setText("true");
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("/home/shubham/electionConfig.xml"));
			return true;
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean stopRegistering() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/shubham/electionConfig.xml");
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			rootNode.getChild("isRegistering").setText("false");
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("/home/shubham/electionConfig.xml"));
			return true;
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isVoting() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/shubham/electionConfig.xml");
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();
			if (rootNode.getChildText("isVoting").matches("true")) {
				return true;
			}

		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean startVoting() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/shubham/electionConfig.xml");
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			rootNode.getChild("isVoting").setText("true");
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("/home/shubham/electionConfig.xml"));
			return true;
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean stopVoting() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/shubham/electionConfig.xml");
		Document doc;
		try {
			doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			rootNode.getChild("isVoting").setText("false");
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("/home/shubham/electionConfig.xml"));
			return true;
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void makeFile() {
		try {
			Element election = new Element("election");
			Document doc = new Document(election);

			doc.getRootElement().addContent(new Element("isVoting").setText("False"));

			Element positions = new Element("positions");
			Element pos = new Element("pos");
			pos.setAttribute("id", "1");
			pos.addContent(new Element("Name").setText("Name of Position"));
			pos.addContent(new Element("maxWinners").setText("1"));
			positions.addContent(pos);
			doc.getRootElement().addContent(positions);

			Element voters = new Element("voters");
			Element vot = new Element("vot");
			vot.setAttribute("id", "1");
			vot.addContent(new Element("prefix").setText("16ucs"));
			vot.addContent(new Element("sufStart").setText("1"));
			vot.addContent(new Element("sufEnd").setText("225"));
			voters.addContent(vot);
			doc.getRootElement().addContent(voters);

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("/home/shubham/electionConfig.xml"));

			System.out.println("BlankFile Created!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	public void ReadXMLFile() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/shubham/electionConfig.xml");
		try {
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			System.out.println("isVoting : " + rootNode.getChildText("isVoting"));

			List votList = rootNode.getChild("voters").getChildren("vot");
			for (int i = 0; i < votList.size(); i++) {
				Element node = (Element) votList.get(i);
				System.out.println("Element " + i + " in voters");
				System.out.println("prefix : " + node.getChildText("prefix"));
				System.out.println("sufStart : " + node.getChildText("sufStart"));
				System.out.println("sufEnd : " + node.getChildText("sufEnd"));
			}

			List posList = rootNode.getChild("positions").getChildren("pos");
			for (int i = 0; i < posList.size(); i++) {
				Element node = (Element) posList.get(i);
				System.out.println("Element " + i + " in positions");
				System.out.println("Name : " + node.getChildText("Name"));
				System.out.println("maxWinners : " + node.getChildText("maxWinners"));
			}
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
	}

}