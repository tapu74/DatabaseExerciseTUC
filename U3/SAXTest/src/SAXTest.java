import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author johannesfliege
 * 
 */
public class SAXTest {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// Read Pizza-XML
		File f = new File("./src/pizzenExample.xml");
		try {
			InputStream in = new FileInputStream(f);
			// Create the SaxClass, which implemets the handlers
                        // for our XML-Paring-Eventts (that is THIS class)
			SAXTest saxTest = new SAXTest();
			saxTest.parseXMLList(in);
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
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
                        //@TODO: Read pizza data and print it to out stream
                        //System.out.println(qName + " encountered");
                        if(qName.equalsIgnoreCase("Pizza")){
                        String id=attributes.getValue("pizzaId");
                        String name=attributes.getValue("pizzaName");
                        String size=attributes.getValue("pizzaSize");
                        String price=attributes.getValue("basePrice");
                        
                        System.out.println("ID : "+ id + " Name : "+ name + " Size : "+ size + " Price : "+ price );
                        }
		}
                
                
	}

}
