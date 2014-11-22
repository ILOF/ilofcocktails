<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator

testing for commit
--%>

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
        <h2></h2>
        </header>
        
        <nav>
            <ul>
                <li class="nav"><a href="/ilofcocktails/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/ilofcocktails/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
 
        <article>
            <h1>All cocktails</h1>
        <%
            CocktailModel cm = new CocktailModel();
            Cluster cluster;
            cluster = CassandraHosts.getCluster();
            cm.setCluster(cluster);
            //java.util.LinkedList<CocktailStore> Cocktails = cm.AllCocktails();

            java.util.LinkedList<CocktailStore> Cocktails = (java.util.LinkedList<CocktailStore>) request.getAttribute("Cocktails");
            if (Cocktails == null) {
        %>
        <p>No cocktails son</p>
        <%
        } else {
           // Iterator<CocktailStore> iterator;
            //iterator = Cocktails.iterator();
            //while (iterator.hasNext()) {
                for(int i = 0; i < Cocktails.size(); i++){
                CocktailStore c = Cocktails.get(i);

        %>
        <a><% out.println(c.getCocktailName()); %></a><br> <%

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
