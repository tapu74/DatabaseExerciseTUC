/**
 * Copyright @ 2000 Peter Roßbach (pr@webapp.de) und Lars Röwekamp (Lars@openKnowlege.de)
 * 
 * Source is only for non commercial and coaching usage.
 * 
 * Not Warranty to use it.
 */
package de.ix.jspTutorial.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Die Klasse nimmt eine Bestellung mit Lieferadresse, Pizza und Zutaten auf.
 *
 *
 * @author Peter Rossbach (<a href="mailto://pr@webapp.de">pr@webapp.de</a>), Lars Röwekamp ( <a href="mailto://lars.roewekamp@openKnowledge.de">lars.roewekamp@openKnowledge.de</a>)
 * @version $Id:$
 */
public class Order {
	 /**
    * Version  des Source
    */
  public static String vcid = "$Id:$";

  /**
   * Vorname
   */
  private String firstname;
  /**
   * Nachname
   */
   private String lastname;
  /**
   * Adresse Strasse und Nummer 1
   */
   private String address1;
  /**
   * Adresse Strasse und Nummer 1
   */
   private String address2;
  /**
   * Ort
   */
   private String town;
  /**
   * Postleitzahl
   */
   private String zip;
  /**
   * eMail Adresse
   */
   
   /**
    * Store location where last order has been saved to
    */
   public String savePath = "Not saved";
   
   private String mail;
  /**
   * Bestellnummer der Pizza
   */
   private String pizzaId;
  /**
   * Liste der Zutaten als Comma separated list
   */
   private String ingredientsId;

   /**
    * Constructor declaration
    *
    *
    * @see
    */
   public Order() {
      this.firstname = "";
      this.lastname = "";
      this.address1 = "";
      this.address2 = "";
      this.town = "";
      this.zip = "";
      this.mail = "";
      this.pizzaId = "";
      this.ingredientsId = "";
   }

   /**
    * Constructor declaration
    *
    *
    * @param firstname
    * @param lastname
    * @param address1
    * @param address2
    * @param zip
    * @param town
    * @param mail
    * @param pizzaId
    * @param ingredientsId
    *
    * @see
    */
   public Order(String firstname, String lastname, String address1, 
                String address2, String zip, String town, String mail, 
                String pizzaId, String ingredientsId) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.address1 = address1;
      this.address2 = address2;
      this.zip = zip;
      this.town = town;
      this.mail = mail;
      this.pizzaId = pizzaId;
      this.ingredientsId = ingredientsId;
   }

   /**
    * Method declaration
    *
    *
    * @param firstname
    *
    * @see
    */
   public void setFirstname(String firstname) {
      this.firstname = firstname;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getFirstname() {
      return firstname;
   } 

   /**
    * Method declaration
    *
    *
    * @param lastname
    *
    * @see
    */
   public void setLastname(String lastname) {
      this.lastname = lastname;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getLastname() {
      return lastname;
   } 

   /**
    * Method declaration
    *
    *
    * @param address1
    *
    * @see
    */
   public void setAddress1(String address1) {
      this.address1 = address1;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getAddress1() {
      return address1;
   } 

   /**
    * Method declaration
    *
    *
    * @param address2
    *
    * @see
    */
   public void setAddress2(String address2) {
      this.address2 = address2;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getAddress2() {
      return address2;
   } 

   /**
    * Method declaration
    *
    *
    * @param zip
    *
    * @see
    */
   public void setZip(String zip) {
      this.zip = zip;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getZip() {
      return zip;
   } 

   /**
    * Method declaration
    *
    *
    * @param town
    *
    * @see
    */
   public void setTown(String town) {
      this.town = town;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getTown() {
      return town;
   } 

   /**
    * Method declaration
    *
    *
    * @param mail
    *
    * @see
    */
   public void setMail(String mail) {
      this.mail = mail;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getMail() {
      return mail;
   } 

   /**
    * Method declaration
    *
    *
    * @param ingredientsId
    *
    * @see
    */
   public void setIngredientsId(String ingredientsId) {
      this.ingredientsId = ingredientsId;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getIngredientsId() {
      return ingredientsId;
   } 

   /**
    * Method declaration
    *
    *
    * @param pizzaId
    *
    * @see
    */
   public void setPizzaId(String pizzaId) {
      this.pizzaId = pizzaId;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String getPizzaId() {
      return pizzaId;
   } 

   /**
    * Method declaration
    *
    *
    * @return
    *
    * @see
    */
   public String toString() {
      return "Firstname: " + firstname + "  Lastname: " + lastname 
             + "  Address: " + address1 + " " + address2 + "  ZIP/Town: " 
             + zip + " " + town + "  Mail: " + mail + "  Pizza: " + pizzaId 
             + "  Ingredients: " + ingredientsId;
   } 

   public void saveOrder(ServletContext aApplication) {
		try {
			// Load dummy order xml (empty orders file)
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
                        
			InputStream ordersStream = aApplication
					.getResourceAsStream("/WEB-INF/config/orders.xml");
			DocumentBuilder builder = factory.newDocumentBuilder();
			// parse it
			Document document = builder.parse(ordersStream);
			ordersStream.close();
			// Access root element
                        // @TODO get ordersNode
	
			
			// Here we create a new order node into the dom
			// @TODO Create Element for single "order"
                         

			// @TODO add Attributes to order (which are stored in that class)
            
                        // we append it here into the (empty) orders node, so 
                        // orders has now exactly one child
			// @TODO append above created order to parent "orders"

                        // if you reach here, and it works -> Be happy :)
                        
			// Now we save it... this is some JAVA boilerplate
                        // ... could be more intuitive, though ;)
			DOMSource domSource = new DOMSource(document);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			// Write into following path (Attention: This will be in your actual BUILD directory)
			OutputStream out = new FileOutputStream(aApplication.getRealPath("/jspTutorial/orders.xml"));
			StreamResult result = new StreamResult(out);
			// Now just write the stuff to the disk
			transformer.transform(domSource, result);
                        
                        // And store, where we saved the file to.
                        this.savePath=aApplication.getRealPath("/jspTutorial/orders.xml");
		} catch (IOException e) {
                       e.printStackTrace();
		} catch (TransformerException e) {
                        e.printStackTrace();
		} catch (SAXException sxe) {
			// Error generated by this application
			// (or a parser-initialization error)
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();

		} catch (ParserConfigurationException pce) {
			// Parser with specified options can't be built
			pce.printStackTrace();

		} catch (Exception e) {
                    e.printStackTrace();
                }

	}
}

//
// History
//
// $Log:$
//
//
