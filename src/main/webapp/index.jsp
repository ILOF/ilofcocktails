
<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>

<link href='http://fonts.googleapis.com/css?family=PT+Sans|Lobster|Comfortaa:400,700|Playball' rel='stylesheet' type='text/css'>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    
            <link rel="stylesheet" type="text/css" href="Styles.css" />
           <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <head>
     
        <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                    <%}
                            }else{
                                %>
    <h4><a style = "font-family: Arial" align="left" href="login.jsp"> Login/Register             
             </a> </h4>
    <%
                                        
                            
                    }%>
        
        
    <div id="titlepad">
        <title>ILOFCocktails</title>
    </div>
        
        <div class="header-image1">
            <img src="bannernew.jpg" class="header-image" alt="Cocktail_banner"> 

          
        </div>
        
        
        <div class="title">
        <h1> ILOFCocktails</h1> 
        </div>
    </head>
    <body>
                 
            

               
                    <%
                        
                        LoggedIn lge = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lge != null) {
                            String UserName = lge.getUsername();
                            if (lge.getlogedin()) {
                    %>
                <div id="horizon-nav">
                <ul id="navlist">    
                <li><a href="/ilofcocktails/AllCocktails">List of all cocktails</a>
                <li><a href="/ilofcocktails/Trending.jsp">Trending</a>
                <li><a href="/ilofcocktails/AllParties">Check the upcoming parties</a>
                <li><a href="/ilofcocktails/PartyDetailsInput.jsp">Organise a new party</a>
                <li><a href="/ilofcocktails/Logout">Logout</a>
                <li>
                </ul>    </div>
                    <%}
                            }else{
                                %>
                 <!-- <a href="login.jsp">Login</a>
                 <a href="register.jsp">Register</a> -->
                     
                
                <%
                                        
                            
                    }%>
                    
                    
            
        
                    
                    
        <div id="placeholder">
            
                <div id="search">
                     <div id="search123">
                        <a href="/ilofcocktails/AllCocktails" class="fill-div">All Cocktails</a>
                    </div>
                    
            </div>  
            
            
            <div id="party">
                <div id="search12">
                    <div class="select-style">
                        Search by   <select name="searchlist" form="searchform">
                            <option value="Occasion">Occasion</option>
                            <option value="Type">Type</option>
                            <option value="Glass">Glass</option>
                            <option value="Name">Name</option>
                            <option value="IngredientHard">Ingredient Hard</option>
                            <option value="IngredientSoft">Ingredient Soft</option>
                        </select>
                    </div>
                    <form method="POST" action="/ilofcocktails/SearchCocktail" id="searchform">

                        <div class="group">  
                            <input id="inputsearch" type="text" name = "searchCriteria">

                            <%--   <span class="bar"></span>
                             
                                                    <span class="highlight"></span>  --%>



                            <%--   <input type="submit" value="Search"> <br><br> --%>

                        </div>
                    </div>
            </div>
            
            
            <div id="trending">
                <div id="search123">
                        <a href="/ilofcocktails/Trending" class="fill-div">Trending</a>
                    </div>
            </div>    
        </div>
        
    </body>
  
</html>