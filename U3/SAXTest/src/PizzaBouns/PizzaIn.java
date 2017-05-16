package PizzaBouns;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tapu
 */
public class PizzaIn {
/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// Read Pizza-XML
		File f = new File("./src/PizzaIn.xml");
		try {
			InputStream in = new FileInputStream(f);
			// Create the SaxClass, which implemets the handlers
                        // for our XML-Paring-Eventts (that is THIS class)
			PizzaIn pizTest = new PizzaIn();
			pizTest.parseXMLList(in);
                        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
                
	}

	/**
	 * Reads the xml data stream
	 * @param in the XML-Input-Stream
         * 
	 * @author Richard Vogel, Johannes Fliege	
	 */
	public void parseXMLList(InputStream in) {
		
                /**
                 * This is the default handler. It will be called each time
                 * some event occurs while reading (eg: Tag found etc)
                 */
		DefaultHandler handler = new PizzaHandler();
		// Use the default (non-validating) parser
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// Parse the input
		SAXParser saxParser = null;
		try {
			// get a new SAXParser
			saxParser = factory.newSAXParser();
			// Start parsing
			saxParser.parse(in, handler);
                                                
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is the actual handler
         * we are only interested in "Pizza" Elements. The Sax parser will just
         * run one time through the document and calls our (Pizza)Handler each time 
         * an event each time something 
         * 
	 * @author Johannes Fliege, Richard Vogel
	 * 
	 */
	private class PizzaHandler extends DefaultHandler {
            	/*
        * Every time the parser encounters the beginning of a new element,
        * it calls this method, which resets the string buffer
        */ 
        boolean ingredient = false;
      

   @Override
   public void startElement(String uri, 
   String localName, String qName, Attributes attributes)
      throws SAXException {
      if (qName.equalsIgnoreCase("Pizza")) {
         String name = attributes.getValue("Name");
         System.out.println("Name : " + name);
      } else if (qName.equalsIgnoreCase("ingredient")) {
         ingredient = true;
         
      } 
   }
       
       @Override
   public void endElement(String uri, 
   String localName, String qName) throws SAXException {
      if (qName.equalsIgnoreCase("Pizza")) {
        System.out.println("\n");
        i=0;
      }
   }
    public int i=0;
   @Override
   public void characters(char ch[], 
      int start, int length) throws SAXException {
       
      if (ingredient) {
         
         if(i>0){
         System.out.print(" , ");
         }
         System.out.print( new String(ch, start, length));
         ingredient = false;
         i++;
      } 
   }

       /*
        * When the parser encounters the end of an element, it calls this method
        */
                
	}
}


