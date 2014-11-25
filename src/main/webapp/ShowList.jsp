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
        
        <%
            ListStore ls = (ListStore) request.getAttribute("Cocktails");
            java.util.LinkedList<String> list = ls.getItems();
            %>
            <p><% //out.println(Cocktails.size()); %></p>
            <%
            if (list == null) {                
        %>
        <p>No cocktails son</p>
        <%
        } else {
           // Iterator<CocktailStore> iterator;
            //iterator = Cocktails.iterator();
            //while (iterator.hasNext()) {
                //for(int i = 0; i < ls.size(); i++){
                //CocktailStore c = ls.get(i);
                for (int i = 0; i<list.size();i++)
                {

        %>
        
                                <div class="group">  
             <input type="text" required readonly>

        <label><% out.println(list.get(i)); %></label><br>

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
