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
                    <h4>
                <a href="/ilofcocktails/AllCocktails">List of all cocktails
                <a href="/ilofcocktails/AllParties">Check the upcoming parties
                <a href="/ilofcocktails/PartyDetailsInput.jsp">Organise a new party
                    <a href="/ilofcocktails/Logout">Logout</a></h4>
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
                    
                  <div class="group">     
        <div id="placeholder">
            
            <div id="loginCircle">
               <div id="loginform">
                   
                    <form method="POST"  action="Login">
                        <div class="group">
                       
                            <input type="text" name="username"> <br> 
                               <labelpassword>Password</labelpassword>
                            <input type="password" name="password">
                           <!--   <span class="bar"></span> -->
                            
                            <labelusername> Username</labelusername> 
                      <!--   <input type="text" value="Login">-->
                      
                       <input id="input1" type="submit" name="Register" class="button2" >
                    </form>


                    </div>
            
            </div>
        </div>
            
            <div id="registercircle">
               <div id="registerform">
                   
                    <form method="POST"  action="Register">
                        <div class="group">
                       
                            <input type="text" name="username"> <br> 
                            <labelusernameregister>Username</labelusernameregister> 
                               
                              <input type="text" name="email">  <br> 
                             <labelemailr>Email</labelemailr>
                             
                            <input type="password" name="password"><br> 
                          
                            <labelpasswordr2>Password</labelpasswordr2>
                            
                             <input type="password" name="password">
                      
                             <labelpasswordr>Password</labelpasswordr> <br>
                             
                             <input id="input1" type="submit" name="Register" class="button" >
                      
                          
                            
                           
                           <!--   <span class="bar"></span> -->
                             <!--   <span class="bar"></span> -->
                          
                      
                      
                      
                      
                      
                    </form>


                    </div>
                    
            </div>
        </div>
            
         
            
            
                  </div>
            
                        
                  </div>
        
    </body>
  
</html>