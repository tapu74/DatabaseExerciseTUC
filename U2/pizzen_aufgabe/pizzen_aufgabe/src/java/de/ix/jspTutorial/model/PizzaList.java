/**
 * Copyright @ 2000 Peter Ro��bach (pr@webapp.de) und Lars R��wekamp (Lars@openKnowlege.de)
 * 
 * Source is only for non commercial and coaching usage.
 * 
 * Not Warranty to use it.
 */

package de.ix.jspTutorial.model;
import java.util.*;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;

/**
 * Liste der Pizza für den Pizza Services
 * Die Pizza werden aus einer Properties Datei einmalig in den
 * ServletContext geladen.
 *
 * @author Peter Rossbach (<a href="mailto://pr@webapp.de">pr@webapp.de</a>), Lars R��wekamp ( <a href="mailto://lars.roewekamp@openKnowledge.de">lars.roewekamp@openKnowledge.de</a>)
 * @version $Id:$
 */
public class PizzaList {      
	
   private TreeMap<Long,Pizza> pizzas;

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
    * @return Schlüssel der Pizza
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
    * Lese die Liste der Properties aus der Datei <i>/WEB-INF/config/pizza.properties"</i>
    *
    *
    * @param aApplication ServletContext
    *
    * @see javax.servlet.ServletContext
    */
   public void readList(ServletContext aApplication) {
      readList(aApplication.getResourceAsStream("/WEB-INF/config/pizza.properties"));
   } 

   /**
    * Lese die Liste aus einem Eingabestrom.
    * Format der Datei:
    * <xmp>
    * pizza.count=2
    *
    * pizza.id_0.id=0
    * pizza.id_0.size=klein (15 cm)
    * pizza.id_0.name=Napoli
    * pizza.id_0.basePrice=5.95
    *
    * pizza.id_1.id=1
    * pizza.id_1.size=mittel (22 cm)
    * pizza.id_1.name=Napoli
    * pizza.id_1.basePrice=7.95
    *
    * pizza.id_2.id=2
    * pizza.id_2.size=riesig (35 cm)
    * pizza.id_2.name=Napoli
    * pizza.id_2.basePrice=9.95
    *
    * </xmp>
    * @param aResourceStream der Eingabe Strom
    *
    * @see
    */
   public void readList(InputStream aResourceStream) {
      pizzas = new TreeMap<Long,Pizza>();
      try {
         Properties props = new Properties();

         props.load(aResourceStream);
         Integer pizzaCount = new Integer(props.getProperty("pizza.count"));

         for (int i = 0; i < pizzaCount.intValue(); i++) {
            Long   pizzaId = new Long(props.getProperty("pizza.id_" + i 
                                                        + ".id"));
            String pizzaName = props.getProperty("pizza.id_" + i + ".name");
            String pizzaSize = props.getProperty("pizza.id_" + i + ".size");
            Double basePrice = new Double(props.getProperty("pizza.id_" + i 
                    + ".basePrice"));

            pizzas.put(pizzaId, 
                       new Pizza(pizzaId.longValue(), pizzaName, pizzaSize, 
                                 basePrice.doubleValue()));
         } 
      } catch (Exception e) {
         throw new RuntimeException("Unable to read resource to get data source" 
                                    + e);
      } 
   }
    
    /**
     * This function needs to be edited
     * readListFromDB().
     */
    public void readListFromDB() throws SQLException
    {
        
        try
        {
                        DatabaseConnection connection=DatabaseConnection.getInstance();
            
			// 2. Create statement
			Statement statement = connection.getConnection().createStatement();
                        String query = "SELECT * from pizzen";
			// 3. Execute statement
			ResultSet result = statement.executeQuery(query);
            
                        /**
                         * Import pizza.sql into your postgreSQL Database.
                         * You can use \i pizza.sql inside postgres prompt
                         * 
                         * Use following code snippet to create a pizza
                         * Pizza pizza = new Pizza(pizzaId, pizzaName, pizzaSize,
                                        basePrice);
                        * 
                        *  use: pizzas.put(pizzaId, pizza); to add a pizza to result set
                         */
                       
                        long  i=1;
                        //pizzas.put(new Long(0), new Pizza(0, "Please implement me. I am static and want to be in the database....", "0.0 mmm", 10000));
                         while (result.next()) 
			{
                            // Print values here
                            int id=result.getInt("pizzaId");
                            String name=result.getString("pizzaName");
                            String size=result.getString("pizzaSize");
                            Double price=result.getDouble("basePrice");
                            //System.out.println(id+" "+name + " " +course);
                            pizzas.put(i,new Pizza(id,name,size,price));
                            
			i++;	
			} 
                         
                         result.close();
			statement.close();
			connection.closeConnection();
                        //throw new SQLException("Please implement me");
		}  catch(SQLException e)
		{
                        // Catch SQL Errors
                        System.out.println("Error while talking to database");
			System.out.println(e.getMessage());
		}
        
        
        
        
    }

   

   /**
    * zeige die Liste der Pizzen
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
