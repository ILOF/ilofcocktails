package uk.ac.dundee.computing.aec.instagrim.lib;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.*;

public final class Keyspaces {

    public Keyspaces() {

    }

    public static void SetUpKeySpaces(Cluster c) {
        try {
            //Add some keyspaces here
            String createkeyspace = "create keyspace if not exists ilofcocktails  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
            
            String CreateCocktailsTable = "CREATE TABLE if not exists ilofcocktails.cocktails (\n"
                    + "      drinkid int,\n"
                    + "      name text,\n"
                    + "      type text,\n"
                    + "      glass text,\n"
                    + "      garnish text,\n"
                    + "      ocasion text,\n"
                    + "      soi1 text,\n"
                    + "      soi2 text,\n"
                    + "      soi3 text,\n"
                    + "      soi4 text,\n"
                    + "      soi5 text,\n"
                    + "      soi6 text,\n"
                    + "      soi7 text,\n"
                    + "      soi8 text,\n"
                    + "      soi9 text,\n"
                    + "      soi10 text,\n"
                    + "      soi11 text,\n"       
                    + "      soi12 text,\n" 
                    + "      PRIMARY KEY (drinkid)"
                    + "  );";
            
            String CreateUserProfile = "CREATE TABLE if not exists ilofcocktails.userprofiles (\n"
                    + "      login text PRIMARY KEY,\n"
                     + "     password text,\n"
                    + "      first_name text,\n"
                    + "      last_name text,\n"
                    + "      email text\n"
                    + "  );";
            
            String CreateIngredients = "CREATE TABLE if not exists ilofcocktails.ingredients (\n"
                    + "      cocktailid int ,\n"
                    + "      ingredient text, \n"
                    + "      PRIMARY KEY(cocktailid, ingredient) "
                    + "  );";
            
            String CreateParties= "CREATE TABLE if not exists ilofcocktails.parties (\n"
                    + "      hostName text, \n"
                    + "      partyName text, \n"
                    + "      date text, \n"
                    + "      guests text, \n"
                    + "      location text, \n"
                    + "      cocktails text, \n"
                    + "      description text, \n"
                    + "      PRIMARY KEY(hostName, date) "
                    + "  );";
            
            String CreateTrending = "CREATE TABLE if not exists ilofcocktails.trending (\n"
                    + "      position int PRIMARY KEY,\n"
                    + "      cocktailid  text, \n"                    
                    + "  );";
            
            
            Session session = c.connect();
            try {
                PreparedStatement statement = session
                        .prepare(createkeyspace);
                BoundStatement boundStatement = new BoundStatement(statement);
                ResultSet rs = session.execute(boundStatement);
                System.out.println("created ilofcocktails ");
            } catch (Exception et) {
                System.out.println("Can't create ilofcocktails " + et);
            }

                //now add some column families 
            
            System.out.println("" + CreateCocktailsTable);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateCocktailsTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create cocktails table " + et);
            }      
                                                   
            System.out.println("" + CreateUserProfile);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateUserProfile);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Address Profile " + et);
            }
            
            System.out.println("" + CreateUserProfile);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateUserProfile);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Address Profile " + et);
            }
            
            
            System.out.println("" + CreateTrending);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateTrending);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Trending table " + et);
            }  
            
            
            System.out.println("" + CreateParties);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateParties);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create cocktails table " + et);
            } 
            
             session.close();
            
           

        } catch (Exception et) {
            System.out.println("Other keyspace or coulm definition error" + et);
        }

    }
}


