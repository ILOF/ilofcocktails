
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
     
        
    <h4><a style = "font-family: Arial" align="left" href="login.jsp"> Login             
            <a style="font-family: Arial" align="left"  href="register.jsp"> Register </a> </h4>
    
        
        
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
                 <nav>
            <ul>

               
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>
                    <div id="horizon-nav">
                <h4>    <a style = "font-family: Arial" align="left" href="/ilofcocktails/AllCocktails">List of all cocktails
                <a style = "font-family: Arial" align="left" href="/ilofcocktails/AllParties">Check the upcoming parties
                <a style = "font-family: Arial" align="left" href="/ilofcocktails/PartyDetailsInput.jsp">Organise a new party
                    <a style = "font-family: Arial" align="left" href="/ilofcocktails/Logout">Logout</a></h4>
                    </div>
                    <%}
                            }else{
                                %>
                 <!-- <a href="login.jsp">Login</a>
                 <a href="register.jsp">Register</a> -->
                     
                
                <%
                                        
                            
                    }%>
                    
                    
            </ul>
        </nav>
                    
                    
        <div id="placeholder">
            
                <div id="search">
                     <div id="search123">
                        <a href="/ilofcocktails/AllCocktails" class="fill-div">All Cocktails</a>
                    </div>
                    
            </div>  
            
            
            <div id="party">
                <div id="search12">
                        <a href="/ilofcocktails/PartyDetailsInput.jsp" class="fill-div">Search for a cocktail</a>
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