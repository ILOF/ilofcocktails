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
                    + "      drinkid text,\n"
                    + "      name text,\n"
                    + "      type text,\n"
                    + "      glass text,\n"
                    + "      garnish text,\n"
                    + "      occasion text,\n"
                    + "      ingredients text,\n"
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
                    + "      PRIMARY KEY (drinkid)"
                    + "  );";
            /*
            String CreatePicTable = "CREATE TABLE if not exists instagrim.Pics ("
                    + " user varchar,"
                    + " picid uuid, "
                    + " interaction_time timestamp,"
                    + " title varchar,"
                    + " image blob,"
                    + " thumb blob,"
                    + " processed blob,"
                    + " imagelength int,"
                    + " thumblength int,"
                    + "  processedlength int,"
                    + " type  varchar,"
                    + " name  varchar,"
                    + " PRIMARY KEY (picid)"
                    + ")";
            String Createuserpiclist = "CREATE TABLE if not exists instagrim.userpiclist (\n"
                    + "picid uuid,\n"
                    + "user varchar,\n"
                    + "pic_added timestamp,\n"
                    + "PRIMARY KEY (user,pic_added)\n"
                    + ") WITH CLUSTERING ORDER BY (pic_added desc);";
            String CreateAddressType = "CREATE TYPE if not exists instagrim.address (\n"
                    + "      street text,\n"
                    + "      city text,\n"
                    + "      zip int\n"
                    + "  );";
            */
            String CreateUserProfile = "CREATE TABLE if not exists ilofcocktails.userprofiles (\n"
                    + "      login text PRIMARY KEY,\n"
                     + "     password text,\n"
                    + "      first_name text,\n"
                    + "      last_name text,\n"
                    + "      email set<text>\n"
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
            
             session.close();
            
            /*
            System.out.println("" + CreatePicTable);

            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreatePicTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create tweet table " + et);
            }
            System.out.println("" + Createuserpiclist);

            try {
                SimpleStatement cqlQuery = new SimpleStatement(Createuserpiclist);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create user pic list table " + et);
            }
            System.out.println("" + CreateAddressType);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateAddressType);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create Address type " + et);
            }            
            */            
           

        } catch (Exception et) {
            System.out.println("Other keyspace or coulm definition error" + et);
        }

    }
}
