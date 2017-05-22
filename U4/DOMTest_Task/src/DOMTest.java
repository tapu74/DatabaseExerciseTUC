import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 */

/**
 * @author johannesfliege, richard vogel <richard.vogel@informatik.tu-chemnitz.de>
 * 
 * 
 */
public class DOMTest
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // Pizza-XML einlesen
        File f = new File("./src/files/pizzenExample.xml");
        try
        {
            InputStream in = new FileInputStream(f);
            // unser DOMTest-Objekt erzeugen
            //DOMTest domTest = new DOMTest();
            
            //domTest.parseXMLList(in);
            DOMTest.parseXMLList(in);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Reads XML-Data from a data stream
     * 
     * @param in
     *           The XML Input stream (Plain data)
     */
    public static void parseXMLList(InputStream in)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(in);
            
            // Now that we have the "document"-tree set up, we can work
            // with that. So we want to get the root element and process further
            DOMTest.generatePizzasFromXML(document.getDocumentElement());

        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This Method parses the pizza DOM that is created  in
     * the parseXMLList function
     * 
     * @param document our XML Main Element (Topmost)
     */
    private static void generatePizzasFromXML(Element xmlElement)
    {
        // We arrive here with the main document Node (Which is the Node "Pizzen" in our example
        NodeList nList = xmlElement.getElementsByTagName("Pizza");
        // So first we have to get the child nodes of our Pizzen Element to get the pizzas
         System.out.println("-----------Pizza Information-------------");
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
             
           // System.out.println("\nCurrent Element :" 
             //  + nNode.getNodeName());
           //  System.out.print(nNode.getNodeType() + "   "+Node.ELEMENT_NODE  );
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println(
                       "Id : " + eElement.getAttribute("pizzaId") +
                       "    Name : "+ eElement.getAttribute("pizzaName") +
                       "    Size : " + eElement.getAttribute("pizzaSize") +
                       "    Price : "+ eElement.getAttribute("basePrice"));
               
           }
         }
        // ... your code
        
        
        // Now find all actual pizza elements (iterate over NodeList) and print out their data (attributes)
        // Be aware, that we return not only Pizzas but also other Nodes (which may
        // not be too obvisious at the beginning). 
        
        //Check the comment in the XML file for hints
        
     
        // ... your code
       // System.out.println("Implement Code to output XML file using DOM parser");
    }

}
