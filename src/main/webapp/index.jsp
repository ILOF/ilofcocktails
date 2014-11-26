
<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    
    <head>
        <title>ILOFCocktails</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
         
        <div class="header-image1">
            <img src="cocktails_banner.jpg" class="header-image" alt="Cocktail_banner">
          
        </div>
        
        <div class="title">
        <h1> ILOFCocktails</h1> 
        </div>
            
        <nav>
            <ul>

               
                    <%
                        
                        LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                        if (lg != null) {
                            String UserName = lg.getUsername();
                            if (lg.getlogedin()) {
                    %>

                <li><a href="/ilofcocktails/Images/<%=lg.getUsername()%>">Your Images</a></li>
                <li><a href="/ilofcocktails/AllCocktails">List of all cocktails</a></li>
                <li><a href="/ilofcocktails/AllParties">Check the upcoming parties</a></li>
                <li><a href="/ilofcocktails/PartyDetailsInput.jsp">Organise a new party</a></li>
                <li><a href="/ilofcocktails/Logout">Logout</a></li>
                    <%}
                            }else{
                                %>
                 <a href="login.jsp">Login</a>
                 <a href="register.jsp">Register</a>
                     
                
                <%
                                        
                            
                    }%>
                    
                    <style>
                        .header-image1{
                            position:absolute;
                       top:0;
                       bottom:0;   
                       width: 100%;
                       height: 35%;
                      max-height:100%;
                      max-width:100%;
                     overflow:hidden;    
                     left: 0;
                     right: 0;
                     z-index:-1;
                        }
                        .header-image {
                        position:absolute;
                       top:0;
                       bottom:0;   
                       width: 100%;
                       height: 100%;
                      max-height:100%;
                      max-width:100%;
                     overflow:hidden;    
                     left: 0;
                     right: 0;
                     z-index:-1;
            }
                        </style>
            </ul>
        </nav>
        <footer>
            <ul>
                <li>&COPY ILOFCocktails</li>
            </ul>
        </footer>
    </body>
</html>