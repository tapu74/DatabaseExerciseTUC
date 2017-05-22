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
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
    * Empty pizza list
    *
    *
    * @see
    */
   public PizzaList() {
      this.pizzas = new TreeMap<Long,Pizza>();
   }

   /**
    * Init if test pizzas
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
    * Hget Pizza by Key
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
    * Get all pizzas
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
    * This method has to be implemented to work like in in the Sax-Version but should parse
    * the  Pizza in Dom Style
    * You can basically use the code from the other example
    */
   private void parseXMLList(InputStream in)
   {
       /* @TODO: Parse XML file here. This works exactly like in the example before,
           if you hab
       */
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();

            
            Document document = builder.parse(in);

            // Now that we have the "document"-tree set up, we can work
            // with that. So we want to get the root element and process further
            
            Element xmlElement=document.getDocumentElement();
                   
                    // We arrive here with the main document Node (Which is the Node "Pizzen" in our example
        NodeList nList = xmlElement.getElementsByTagName("Pizza");
        // So first we have to get the child nodes of our Pizzen Element to get the pizzas
         long i=1;
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
           // System.out.println("\nCurrent Element :" 
             //  + nNode.getNodeName());
          
               Element eElement = (Element) nNode;
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               String ids=eElement.getAttribute("pizzaId");
               String name=eElement.getAttribute("pizzaName");
               String size=eElement.getAttribute("pizzaSize");
               String prices=eElement.getAttribute("basePrice");
               long id=Long.parseLong(ids);
               double price=Double.parseDouble(prices);
               
               this.pizzas.put(i, new Pizza(id, name, size, price));
               i++;  
               }
         }

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
   
   private static void generatePizzasFromXML(Element xmlElement)
    {
        
    }
   

   /**
    * Show List of pizzas
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
