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

public class CocktailModel {

    Cluster cluster;
    private Object session;

    public void CocktailModel() {
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
     
     
    public java.util.LinkedList<CocktailStore> AllCocktails() {
        java.util.LinkedList<CocktailStore> Cocktails = new java.util.LinkedList<>();
        Session session = cluster.connect("ilofcocktails");
        PreparedStatement ps = session.prepare("select * from cocktails");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                ));
        if (rs.isExhausted()) {
            System.out.println("No cocktails (should never happen)");
            return null;
        } else {
            for (Row row : rs) {
                CocktailStore cs = new CocktailStore();
                cs.setId(""+row.getInt("drinkid"));
                cs.setCocktailName(row.getString("name"));
                cs.setGarnish(row.getString("garnish"));
                cs.setGlass(row.getString("glass"));
                cs.setOcasion(row.getString("ocasion"));
                cs.setType(row.getString("type"));
                cs.setSoi1(row.getString("soi1").toLowerCase());
                cs.setSoi2(row.getString("soi2").toLowerCase());
                cs.setSoi3(row.getString("soi3").toLowerCase());
                cs.setSoi4(row.getString("soi4").toLowerCase());
                cs.setSoi5(row.getString("soi5").toLowerCase());
                cs.setSoi6(row.getString("soi6").toLowerCase());
                cs.setSoi7(row.getString("soi7").toLowerCase());
                cs.setSoi8(row.getString("soi8").toLowerCase());
                cs.setSoi9(row.getString("soi9").toLowerCase());
                cs.setSoi10(row.getString("soi10").toLowerCase());
                cs.setSoi11(row.getString("soi11").toLowerCase());
                cs.setSoi12(row.getString("soi12").toLowerCase());

                
                Cocktails.add(cs);
            }
        }
        return Cocktails;

    }
    
        public java.util.LinkedList<CocktailStore> SearchNames(String SearchCriteria) {
               java.util.LinkedList<CocktailStore> Cocktails = AllCocktails();
               java.util.LinkedList<CocktailStore> CocktailsReturn = new java.util.LinkedList<>();
               for (int j = 0; j < Cocktails.size(); j++) {
                        CocktailStore currentCocktail = Cocktails.get(j); 
                        if(currentCocktail.getCocktailName().toLowerCase().contains(SearchCriteria.toLowerCase())  ){
                            CocktailsReturn.add(currentCocktail);
                        }                        
               }               
               return CocktailsReturn;            
        }

        
        
    public java.util.LinkedList<CocktailStore> SearchType(String SearchCriteria) {
        
        
               java.util.LinkedList<CocktailStore> Cocktails = AllCocktails();
               java.util.LinkedList<CocktailStore> CocktailsReturn = new java.util.LinkedList<>();
               for (int j = 0; j < Cocktails.size(); j++) {
                        CocktailStore currentCocktail = Cocktails.get(j); 
                        if(currentCocktail.getType().toLowerCase().contains(SearchCriteria.toLowerCase())  ){
                            CocktailsReturn.add(currentCocktail);
                        }
                        
               }
               
               return CocktailsReturn;

            
        }
        
    public java.util.LinkedList<CocktailStore> SearchGlass(String SearchCriteria) {
               java.util.LinkedList<CocktailStore> Cocktails = AllCocktails();
               java.util.LinkedList<CocktailStore> CocktailsReturn = new java.util.LinkedList<>();
               for (int j = 0; j < Cocktails.size(); j++) {
                        CocktailStore currentCocktail = Cocktails.get(j); 
                        if(currentCocktail.getGlass().toLowerCase().contains(SearchCriteria.toLowerCase())  ){
                            CocktailsReturn.add(currentCocktail);
                        }
                        
               }
               
               return CocktailsReturn;

            
        }
    
  public java.util.LinkedList<CocktailStore> SearchOcasion(String SearchCriteria) {
               java.util.LinkedList<CocktailStore> Cocktails = AllCocktails();
               java.util.LinkedList<CocktailStore> CocktailsReturn = new java.util.LinkedList<>();
               for (int j = 0; j < Cocktails.size(); j++) {
                        CocktailStore currentCocktail = Cocktails.get(j); 
                        if(currentCocktail.getOcasion().toLowerCase().contains(SearchCriteria.toLowerCase())  ){
                            CocktailsReturn.add(currentCocktail);
                        }
                        
               }
               
               return CocktailsReturn;

            
        }
  
  public java.util.LinkedList<CocktailStore> ReturnTrending() {
               java.util.LinkedList<CocktailStore> Cocktails = AllCocktails();
               java.util.LinkedList<CocktailStore> CocktailsReturn = new java.util.LinkedList<>();
               java.util.LinkedList<String> CocktailsTop = new java.util.LinkedList<>();

               Session session = cluster.connect("ilofcocktails");
                         PreparedStatement ps = session.prepare("select * from trending");
                         ResultSet rs = null;
                         BoundStatement boundStatement = new BoundStatement(ps);
                         rs = session.execute( // this is where the query is executed
                         boundStatement.bind( // here you are binding the 'boundStatement'
                                            ));
                         if (rs.isExhausted()) {
                        System.out.println("No cocktails (should never happen)");
                        return null;
                        } else {

                            for (Row row : rs) {
                                CocktailsTop.add(""+row.getInt("cocktailid"));
                            
                            //System.out.println(row.getString("ingredient"));
                            } 
                         }
                         
                         for(int i = 0; i < CocktailsTop.size(); i++){
                             for(int j = 0; j<Cocktails.size(); j++){
                                 if(Cocktails.get(j).getId().equals(CocktailsTop.get(i))){
                                     CocktailsReturn.add(Cocktails.get(j));
                                 }
                             }
                         }
                        
               
               
               return CocktailsReturn;

            
        }
        //this will be overloaded to take in a party and use
      public ListStore ReturnShopping(java.util.LinkedList<String> ingredientsHad, java.util.LinkedList<String> CocktailIDs) {
          ListStore ls = new ListStore();
          java.util.LinkedList<String> ingredientsNeeded = new java.util.LinkedList<>();
          for (int j = 0; j<CocktailIDs.size(); j++){

                        Session session = cluster.connect("ilofcocktails");
                         PreparedStatement ps = session.prepare("select * from ingredients where cocktailid =?");
                         ResultSet rs = null;
                         BoundStatement boundStatement = new BoundStatement(ps);
                         rs = session.execute( // this is where the query is executed
                         boundStatement.bind( // here you are binding the 'boundStatement'
                                            Integer.parseInt(CocktailIDs.get(j))));
                         if (rs.isExhausted()) {
                        System.out.println("No cocktails (should never happen)");
                        return null;
                        } else {
                            for (Row row : rs) {
                            if(!ingredientsNeeded.contains(row.getString("ingredient"))){
                            ingredientsNeeded.add(row.getString("ingredient").toLowerCase());
                            }
                            //System.out.println(row.getString("ingredient"));
                            }
                         }

          }
          for(int i = 0; i < ingredientsNeeded.size(); i++){
              if(!ingredientsHad.contains(ingredientsNeeded.get(i))){
                  ls.addItem(ingredientsNeeded.get(i));
              }
          
          }
          
          
          
          
          
          
          
          //loop though words -- 
          //    loop through ingrediants --
          //if match, dont push ingrediant on to the ListStore -- if not.. do.. lawl
          
          
          
          return ls;
          
      }
      


  
    public java.util.LinkedList<CocktailStore> HardSearch(String SearchCriteria) {
            //connect to db and get ingrediants
                Session session = cluster.connect("ilofcocktails");
        
                String[] SearchWords = SearchCriteria.split(",");
                for(int b = 0; b < SearchWords.length; b++){
                }
               java.util.LinkedList<CocktailStore> Cocktails = AllCocktails();
               java.util.LinkedList<CocktailStore> CocktailsReturn = new java.util.LinkedList<>();
               java.util.LinkedList<String> Ingredients = new java.util.LinkedList<>();
                         int matches = 0;
                         int noOfIngredients = 0;
                         
               for (int j = 0; j < Cocktails.size(); j++) {
                        Ingredients.clear();
                        CocktailStore currentCocktail = Cocktails.get(j);
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
    
    
    
                
                public java.util.LinkedList<CocktailStore> SoftSearchIngredients(String SearchCriteria) {
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
                    
                    java.util.LinkedList<CocktailStore> Cocktails = AllCocktails();
                    java.util.LinkedList<CocktailStore> CocktailsReturn = new java.util.LinkedList<>();
                    
                   
                    for (int j = 0; j < Cocktails.size(); j++) {
                        CocktailStore currentCocktail = Cocktails.get(j);
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


    
   
    public java.util.LinkedList<Pic> getPicsForUser(String User) {
        java.util.LinkedList<Pic> Pics = new java.util.LinkedList<>();
        Session session = cluster.connect("ilofcocktails");
        PreparedStatement ps = session.prepare("select picid from userpiclist where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                Pic pic = new Pic();
                java.util.UUID UUID = row.getUUID("picid");
                System.out.println("UUID" + UUID.toString());
                pic.setUUID(UUID);
                Pics.add(pic);

            }
        }
        return Pics;
    }

    public Pic getPic(int image_type, java.util.UUID picid) {
        Session session = cluster.connect("ilofcocktails");
        ByteBuffer bImage = null;
        String type = null;
        int length = 0;
        try {
            Convertors convertor = new Convertors();
            ResultSet rs = null;
            PreparedStatement ps = null;
         
            if (image_type == Convertors.DISPLAY_IMAGE) {
                
                ps = session.prepare("select image,imagelength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_THUMB) {
                ps = session.prepare("select thumb,imagelength,thumblength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                ps = session.prepare("select processed,processedlength,type from pics where picid =?");
            }
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            picid));

            if (rs.isExhausted()) {
                System.out.println("No Images returned");
                return null;
            } else {
                for (Row row : rs) {
                    if (image_type == Convertors.DISPLAY_IMAGE) {
                        bImage = row.getBytes("image");
                        length = row.getInt("imagelength");
                    } else if (image_type == Convertors.DISPLAY_THUMB) {
                        bImage = row.getBytes("thumb");
                        length = row.getInt("thumblength");
                
                    } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                        bImage = row.getBytes("processed");
                        length = row.getInt("processedlength");
                    }
                    
                    type = row.getString("type");

                }
            }
        } catch (Exception et) {
            System.out.println("Can't get Pic" + et);
            return null;
        }
        session.close();
        Pic p = new Pic();
        p.setPic(bImage, length, type);

        return p;

    }

}
