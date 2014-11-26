<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator

testing for commit
--%>

<%@page import="uk.ac.dundee.computing.aec.instagrim.models.PartyModel"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.CocktailStore"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts"%>
<%@page import="com.datastax.driver.core.Cluster"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.models.CocktailModel"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ilofcocktails</title>
        <link rel="stylesheet" type="text/css" href="/ilofcocktails/Styles.css" />
    </head>
    <body>
        <header>
        
        <h1>ILOFCocktails ! </h1>
        <h2>Look up parties</h2>
        </header>
 
     
        
        <form method="POST" action="/ilofcocktails/SearchParty">            
                        <div class="group">  
            <input type="text" required name = "searchCriteria">
            <span class="bar"></span>
                                    <span class="highlight"></span>
            <label> party name</label>
            
       <%--   <input type="submit" value="Search"> <br><br> --%>      
              </div>
                    </form>         

       
    
        <article>
        
        <%
            PartyModel pm = new PartyModel();
            Cluster cluster;
            cluster = CassandraHosts.getCluster();
            pm.setCluster(cluster);
            //java.util.LinkedList<PartyStore> Cocktails = pm.AllParties();

            java.util.LinkedList<PartyStore> Parties = (java.util.LinkedList<PartyStore>) request.getAttribute("Parties");
            %>
            <p><% //=out.println(Parties.size()); %></p>
            <%
            if (Parties == null) {                
        %>
        <p>No parties set yet.</p>
        <%
        } else {
           // Iterator<PartyStore> iterator;
            //iterator = Parties.iterator();
            //while (iterator.hasNext()) {
                for(int i = 0; i < Parties.size(); i++){
                PartyStore p = Parties.get(i);

        %>
        
                                <div class="group">  
             <input type="text" required readonly>

        <label><% out.println(p.getPartyName() + " hosted by " + p.getHostName() + " on " + p.getDate()); %></label><br>

                                </div>
        
        <%

            }
            }
        %>
        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/ilofcocktails">Home</a></li>
            </ul>
        </footer>
    </body>
</html>
