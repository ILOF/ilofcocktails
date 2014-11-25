package uk.ac.dundee.computing.aec.instagrim.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;
 CREATE TABLE Tweets (
 user varchar,
 interaction_time timeuuid,
 tweet varchar,
 PRIMARY KEY (user,interaction_time)
 ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 */
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import static org.imgscalr.Scalr.*;
import org.imgscalr.Scalr.Method;

import uk.ac.dundee.computing.aec.instagrim.lib.*;
import uk.ac.dundee.computing.aec.instagrim.stores.*;
//import uk.ac.dundee.computing.aec.stores.TweetStore;

public class PartyModel {

    Cluster cluster;
    private Object session;

    public void PartyModel() {
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
     
     
    public java.util.LinkedList<PartyStore> AllParties() {
        java.util.LinkedList<PartyStore> Parties = new java.util.LinkedList<>();
        Session session = cluster.connect("ilofcocktails");
        PreparedStatement ps = session.prepare("select * from parties");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                ));
        if (rs.isExhausted()) {
            System.out.println("No parties.");
            return null;
        } else {
            for (Row row : rs) {
                PartyStore prt = new PartyStore();
                prt.setCocktails(row.getString("cocktails"));
                prt.setDate(row.getString("date"));
                prt.setDescription(row.getString("description"));
                prt.setGuests(row.getString("guests"));
                prt.setHostName(row.getString("hostName"));
                prt.setPartyName(row.getString("partyName"));
                prt.setLocation(row.getString("location"));
                
                Parties.add(prt);
            }
        } 
        return Parties;
    }
    
    
    
        public java.util.LinkedList<PartyStore> SearchNames(String SearchCriteria) {
               java.util.LinkedList<PartyStore> Parties = AllParties();
               java.util.LinkedList<PartyStore> PartiesReturn = new java.util.LinkedList<>();
               for (int j = 0; j < Parties.size(); j++) {
                        PartyStore currentParty = Parties.get(j); 
                        if(currentParty.getPartyName().toLowerCase().contains(SearchCriteria.toLowerCase())  ){
                            PartiesReturn.add(currentParty);
                        }                        
               }
               return PartiesReturn;
        }
    
        
            
    public void addParty(String hostName, String date, String cocktails, String description, String guests, String location, String  partyName){       
        Session session = cluster.connect("ilofcocktails");    
        PreparedStatement newParty = session.prepare("insert into parties (hostName, date, cocktails, description, guests, location, partyName) values (?,?,?,?,?,?,?) ");
        BoundStatement boundStatementFirstNameDelete = new BoundStatement(newParty);
        
        session.execute(boundStatementFirstNameDelete.bind(hostName, date, cocktails, description, guests, location, partyName));          
    }
    
    
    
     
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> do something with those?
    /*
    public java.util.LinkedList<PartyStore> HardSearch(String SearchCriteria) {
            //connect to db and get ingrediants
                Session session = cluster.connect("ilofcocktails");
        
                String[] SearchWords = SearchCriteria.split(",");
                for(int b = 0; b < SearchWords.length; b++){
                }
               java.util.LinkedList<PartyStore> Cocktails = AllCocktails();
               java.util.LinkedList<PartyStore> CocktailsReturn = new java.util.LinkedList<>();
               java.util.LinkedList<String> Ingredients = new java.util.LinkedList<>();
                         int matches = 0;
                         int noOfIngredients = 0;
                         
               for (int j = 0; j < Cocktails.size(); j++) {
                        Ingredients.clear();
                        PartyStore currentCocktail = Cocktails.get(j);
                        //get ingrediants for the current cocktail
                        PreparedStatement ps = session.prepare("select * from ingredients where cocktailid=?");
                        ResultSet rs = null;
                        BoundStatement boundStatement = new BoundStatement(ps);
                         rs = session.execute( // this is where the query is executed
                        boundStatement.bind( // here you are binding the 'boundStatement'
                            Integer.parseInt(currentCocktail.getId()))); 
                         if (rs.isExhausted()) {
                        System.out.println("No cocktails (should never happen)");
                        return null;
                        } else {
                            for (Row row : rs) {
                            Ingredients.add(row.getString("ingredient").toLowerCase());
                            //System.out.println(row.getString("ingredient"));
                            }
                         }
                         noOfIngredients = Ingredients.size();
                         matches = 0;
                        for (int k = 0; k < SearchWords.length; k++) {
                            for (int m = 0; m < Ingredients.size(); m ++){
                                if(SearchWords[k].equals(Ingredients.get(m))){
                                    System.out.println(SearchWords[k]+" = "+Ingredients.get(m));

                                    matches++;
                                }
                            }
                        }
                        if (matches >= Ingredients.size()-1){
                            CocktailsReturn.add(currentCocktail);
                        }

                        //loop through ingrediants, loop through words
                        //add to count if match, if all indrediants match, 
                        //add to return list
                        
               }
               
               return CocktailsReturn;

            
        }
    */
    
    
                /*
                public java.util.LinkedList<PartyStore> SoftSearchIngredients(String SearchCriteria) {
                       //Generates linked list of all cocktails for seaching on
            
            //testing
            System.out.println("MATE IM WORKING");
                    String[] SearchWords = SearchCriteria.split(",");
                    for (int i = 0; i < SearchWords.length; i++){
                        System.out.println(SearchWords[i]);
                    }
                    
                    //loop through each cocktail in cocktails
                    //          loop through all ingrediants
                    //check each ingredian with soi1-12
                    //if match -  add current cocktail to COcktailsReturn
                    
                    java.util.LinkedList<PartyStore> Cocktails = AllCocktails();
                    java.util.LinkedList<PartyStore> CocktailsReturn = new java.util.LinkedList<>();
                    
                   
                    for (int j = 0; j < Cocktails.size(); j++) {
                        PartyStore currentCocktail = Cocktails.get(j);
                        boolean returned = false;
                        for (int k = 0; k < SearchWords.length; k++) {
                            if (currentCocktail.getSoi1().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi2().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi3().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi4().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi5().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi6().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi7().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi8().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi9().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi10().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi11().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getSoi12().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            if (currentCocktail.getGarnish().contains(SearchWords[k])){
                                if (!returned){
                                CocktailsReturn.add(currentCocktail);
                                returned = true;
                                }
                            }
                            
                        }
                    }
    
                    return CocktailsReturn;
            
        }
*/

    
   

}
