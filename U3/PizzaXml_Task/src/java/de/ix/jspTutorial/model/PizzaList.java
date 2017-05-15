/**
 * Copyright @ 2000 Peter Rossbach (pr@webapp.de) und Lars Roewekamp (Lars@openKnowlege.de)
 * 
 * Source is only for non commercial and coaching usage.
 * 
 * Not Warranty to use it.
 */

package de.ix.jspTutorial.model;
import java.util.*;
import java.io.*;
import java.net.URL;
import javax.servlet.ServletContext;
import java.sql.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;


/**
 * Liste der Pizza fuer den Pizza Services
 * Die Pizza werden aus einer Properties Datei einmalig in den
 * ServletContext geladen.
 *
 * @author Peter Rossbach (<a href="mailto://pr@webapp.de">pr@webapp.de</a>), Lars Roewekamp ( <a href="mailto://lars.roewekamp@openKnowledge.de">lars.roewekamp@openKnowledge.de</a>)
 * @version $Id:$
 */
public class PizzaList {
    
    private TreeMap<Long, Pizza> pizzas;
	
   /**
    * Leere Liste von Pizzen
    *
    *
    * @see
    */
   public PizzaList() {
      this.pizzas = new TreeMap<Long,Pizza>();
   }

   /**
    * Initialisiern mit Testpizzen
    *
    *
    * @param pizzas Testpizzen
    *
    * @see
    */
   public PizzaList(TreeMap<Long,Pizza> pizzas) {
      this.pizzas = pizzas;
   }

   /**
    * Hole bestimmte Pizza
    *
    *
    * @param key Bestellnummer der Pizza
    *
    * @return Schluessel der Pizza
    *
    * @see
    */
   public Pizza getPizza(Long key) {
      return (Pizza) pizzas.get(key);
   } 

   /**
    * Hole alle Pizzen
    *
    *
    * @return
    *
    * @see
    */
   public TreeMap getPizzas() {
      return pizzas;
   } 

   /**
    * Reads the XML File from <i>/WEB-INF/config/pizzenExample.xml</i>.
    * and calls parseXMLList.
    * @param aApplication ServletContext
    */
   public void readXMLList(ServletContext aApplication) {
	   InputStream in = aApplication.getResourceAsStream("/WEB-INF/config/pizzenExample.xml");
	   this.parseXMLList(in);
   }
   
   
   /**
    * This method has to be implemented to work like in in the SaxTest but should parse
    * the 
    *
    */
   private void parseXMLList(InputStream in)
   {
       /* @ TODO: Parse XML file here. This works exactly like in the example before
       */
      // this.pizzas.put(new Long(0), new Pizza(0, "Implement me", "0nm", 10000000.99));
       
       
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
   
   
   private class PizzaHandler extends DefaultHandler {
       long i=1;
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
                        //@TODO: Read pizza data and print it to out stream
                        //System.out.println(qName + " encountered");
                        if(qName.equalsIgnoreCase("Pizza")){
                        String ids=attributes.getValue("pizzaId");
                        String name=attributes.getValue("pizzaName");
                        String size=attributes.getValue("pizzaSize");
                        String price=attributes.getValue("basePrice");
                        float baseprice =Float.parseFloat(price);
                        int id=Integer.parseInt(ids);
                       PizzaList.this.pizzas.put(i, new Pizza(id, name, size, baseprice));
                       // System.out.println("ID : "+ id + " Name : "+ name + " Size : "+ size + " Price : "+ price );
                       i++;
                        }
		}
                
                
	}
   
   
   
   /**
    * Show list of pizzas
    * 
    *
    * @return Liste der Pizzen
    *
    * @see
    */
   public String toString() {
      StringBuffer outStr = new StringBuffer();
      Set    entrySet = pizzas.entrySet();

      outStr.append("[") ;
      for (Iterator iter = entrySet.iterator(); iter.hasNext(); ) {
         Map.Entry entry = (Map.Entry) iter.next();

         outStr.append(((Pizza) entry.getValue()).toString()) ;
         if(iter.hasNext())
         		outStr.append(",\n");
      } 
      outStr.append("]") ;
      return outStr.toString();
   } 
   
}
