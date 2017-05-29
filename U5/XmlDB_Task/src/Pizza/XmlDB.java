package Pizza;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;

import net.xqj.basex.BaseXXQDataSource;
import com.xqj2.XQConnection2;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQItem;
import javax.xml.xquery.XQItemType;
import org.w3c.dom.NamedNodeMap;


/**
 * @author johannesfliegem richard vogel
 * @mail <richard.vogel@informatik.tu-chemnitz.de>
 * 
 */
public class XmlDB
{
    // To create a new database, you first start the BaseX.jar
    // There you can import your .xml-file
    // After that you have to start a server
    //private static final String DB_NAME = "pizzenExample";
    private static final String DB_NAME = "pizzen";
    
    private static final String DB_HOST = "127.0.0.1"; // oder die IP-Adresse 134.109.x.y
    private static final int DB_PORT = 1984;
    private static final String DB_USER = "admin";
    private static final String DB_USER_PASSWORD = "admin";
    
    private XQConnection2 connection = null;
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        XmlDB xmldbTest = new XmlDB();
        xmldbTest.readListFromXMLDB();
    }
    
    /**
     * Liest die Pizzen aus der XML-Datenbank BaseX.
     */
    public void readListFromXMLDB()
    {
        // Datenquelle anlegen
        BaseXXQDataSource xqDataSource = new BaseXXQDataSource();
        // und alles mit den oben angegebenen Konstanten konfigurieren
        xqDataSource.setServerName(DB_HOST);
        xqDataSource.setPort(DB_PORT);
        xqDataSource.setUser(DB_USER);
        xqDataSource.setPassword(DB_USER_PASSWORD);
        
        //Verbindung zur Datenbank herstellen

        this.connection = (XQConnection2) xqDataSource.getConnection();
        try
        {
            XQExpression expression = connection.createExpression();
            
            /* 
            Here we see a list of XPATH queries.
            These fetch an amount of data from our xml
            
            @TODO: Complete the queries . Select each time the asked data.
            */
            // All Pizzas
            String queryStringAllPizzas = "/dbw:Pizzen/Pizza";
            // Pizzas with id 1
            String queryStringPizza1 = "/dbw:Pizzen/Pizza[@pizzaId='1']";
            // Pizzas under 5
            String queryStringCheapPizzas = "/dbw:Pizzen/Pizza[@basePrice< '5']";
            // Pizzas having at least one ingredient
            String queryStringAtLeastOneIngredient = "/dbw:Pizzen/Pizza[count(Ingredient)>=1]";
            // Pizzas having exactly one ingredient
            String queryStringOneIngredient = "/dbw:Pizzen/Pizza[count(Ingredient)=1]";
            //Amount of pizzas
            String queryStringAmount = "/dbw:Pizzen/count(Pizza)";
            // The most expensive pizza
            String queryStringMostExpensivePizza = "/dbw:Pizzen/Pizza[@basePrice = max(/dbw:Pizzen/Pizza/@basePrice)]";
            // The most expensive AND the most cheap pizza
            String queryStringMostExpensiveAndMostCheapPizza = "/dbw:Pizzen/Pizza[@basePrice = min(/dbw:Pizzen/Pizza/@basePrice) or @basePrice = max(/dbw:Pizzen/Pizza/@basePrice)] ";
            // Last pizza in XML List
            String queryStringLastPizza = "/dbw:Pizzen/Pizza[last()]";
            // next-to-last pizza 
            String queryStringNextToLastPizza = "/dbw:Pizzen/Pizza[last()-1]";
            // Pizzas having "Gouda" as Ingredient
            String queryStringPizzaGouda = "/dbw:Pizzen/Pizza[Ingredient='Gouda']";
            // Pizzas with Pepepr AND Garlic
            String queryStringPizzaPepperAndGarlic = "/dbw:Pizzen/Pizza[Ingredient='Pepper' and Ingredient='Garlic']";
           
            
            // ### Execute the query ### 
            // # TODO put your query inside that
            //XQResultSequence rs1 = this.executeQuery(queryStringAllPizzas);
            //XQResultSequence rs1 = this.executeQuery(queryStringPizza1);
            //XQResultSequence rs1 = this.executeQuery(queryStringCheapPizzas);
            //XQResultSequence rs1 = this.executeQuery(queryStringAtLeastOneIngredient);
            //XQResultSequence rs1 = this.executeQuery(queryStringOneIngredient);
            //XQResultSequence rs1 = this.executeQuery(queryStringAmount);
            //XQResultSequence rs1 = this.executeQuery(queryStringMostExpensivePizza);
            //XQResultSequence rs1 = this.executeQuery(queryStringMostExpensiveAndMostCheapPizza);
            //XQResultSequence rs1 = this.executeQuery(queryStringLastPizza);
            //XQResultSequence rs1 = this.executeQuery(queryStringNextToLastPizza);
            //XQResultSequence rs1 = this.executeQuery(queryStringPizzaGouda);
            XQResultSequence rs1 = this.executeQuery(queryStringPizzaPepperAndGarlic);
            
            List<String> pizzaList = this.generatePizzas(rs1);
            
            // Print result
            for(String line : pizzaList) {
                System.out.println(line);
            }
            
        }
        catch (XQException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // Ressourcen freigeben (Verbindung schließen)
            try
            {
                connection.close();
            }
            catch (XQException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * This function runs a query (and will automatically use our namespace)
     * @param queryString
     * @return 
     */
    public XQResultSequence executeQuery(String queryString) {
                String queryStringNamespace = new String("declare namespace dbw = \"http://www.tu-chemnitz.de/informatik/DVS/DBW\";fn:collection('" + DB_NAME + "')");
                queryString=queryStringNamespace.concat(queryString);
                
                try {
                    XQExpression expression = connection.createExpression();
                    return expression.executeQuery(queryString);
                } catch(Exception e) {
                    System.out.println("Error while executing: ".concat(queryString).
                            concat("Due to: ".concat(e.getMessage())));
                    return null;
                }
           
    }

    /**
     * Takes a result sequence and generates a pizza entry for each
     * found pizza. Also works when selecting integer values
     * */
    public List<String> generatePizzas (XQResultSequence queryResult) {
        List<String> pizzaList = new ArrayList<String>();
        try {
            while(queryResult.next())
            {
                //String documentString = rs.getSequenceAsString(null);
                XQItem item = queryResult.getItem();
                XQItemType type = queryResult.getItemType();   
                
                if(type.getTypeName().getLocalPart().equals("integer")) {
                   pizzaList.add(String.valueOf(item.getLong()));
                }else {
                    Node node = item.getNode();
                    NamedNodeMap nnm = node.getAttributes();
                    String name = nnm.getNamedItem("pizzaName").getNodeValue();
                    String id = nnm.getNamedItem("pizzaId").getNodeValue();
                    //String pizzaSize = nnm.getNamedItem("pizzaSize").getNodeValue();
                    //String basePrice = nnm.getNamedItem("basePrice").getNodeValue();
                    pizzaList.add(name);
                    pizzaList.add(id);
                   // pizzaList.add(pizzaSize);
                   // pizzaList.add(basePrice);
                }
            
        
                // den (DOM)-Parser anstoßen
                //this.parseXMLList(new ByteArrayInputStream(documentString.getBytes("UTF8")));
            }   
        } catch (XQException ex) {
            Logger.getLogger(XmlDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return pizzaList;
        }
    }
}
