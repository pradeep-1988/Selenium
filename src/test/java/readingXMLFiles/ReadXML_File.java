package readingXMLFiles;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML_File {
	
	/*
	Two ways to read the xml files:
		* 1 Read XML File in Java and Get XML Node Values by Providing Node Name
		* 2 Read XML File in Java and Get XML Node Values by Providing XPath
	
	*/
	
	@Test
	public void GetXMLNodeByNodeName() {
		
		try {
			
			File file = new File("/Users/pradeep/Documents/NewStarting_Workspace/Selenium/xmlTestData.xml"); 

			//Create a new object of DocumentBuilderFactory
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			//Create an object DocumentBuilder to parse the XML file data
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element name is: " + doc.getDocumentElement().getNodeName()); 
			
			//Creating a list of nodes present in the XML file
			NodeList custNodeList = doc.getElementsByTagName("Customer");
			System.out.println("Total customer nodes: "+custNodeList.getLength());
			
			//Get the data from all the customer nodes:
			for(int i=0; i<custNodeList.getLength(); i++) {
				Node node = custNodeList.item(i);
				System.out.println("\n"+"("+i+")" +" Child Node Name :" + node.getNodeName()); 
				//System.out.println(node.getNodeName());
				
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				   {  
					      Element element = (Element) node;  
					      System.out.println("Customer CompanyName: "+ element.getElementsByTagName("CompanyName").item(0).getTextContent());  
					      System.out.println("Customer ContactName: "+ element.getElementsByTagName("ContactName").item(0).getTextContent());  
					      System.out.println("Customer ContactTitle: "+ element.getElementsByTagName("ContactTitle").item(0).getTextContent());  
					      System.out.println("Customer Phone: "+ element.getElementsByTagName("Phone").item(0).getTextContent());  
				   }  
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Test
	public void GetXMLNodeByXPath() {
		
		try {
			
			File file = new File("/Users/pradeep/Documents/NewStarting_Workspace/Selenium/xmlTestData.xml"); 

			//Create a new object of DocumentBuilderFactory
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			//Create an object DocumentBuilder to parse the XML file data
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element name is: " + doc.getDocumentElement().getNodeName()); 
			
			 // Get the order date of 1st Order
			 String str1stOrderDate =  getNodeValueFromDom(doc,"//Orders/Order[1]/OrderDate");
			 System.out.println("Order date is: " + str1stOrderDate);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//Method to get XML Node value using XPath
	 public static String getNodeValueFromDom(Document xmlDoc, String strXPath) 
	 {
	    try 
	    {
	       XPath xPath = XPathFactory.newInstance().newXPath();
	       return xPath.compile(strXPath).evaluate(xmlDoc);
	    } 
	    catch (XPathExpressionException e) 
	    {
	       e.printStackTrace();
	    }
	    return null;
	 }
}
