<link href='http://fonts.googleapis.com/css?family=PT+Sans|Lobster|Comfortaa:400,700|Playball' rel='stylesheet' type='text/css'> </link>
<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add party</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
   
       <div class="titleCocktail">
          <h1> ILOFCocktails</h1> 
       </div>

        <div class="header-image1">
           <img src="bannerbanner.png">
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
        </ul>    </div>
        <%}
        } else {
        %>
    <div id="horizon-nav">
        <ul id="navlist">    
            <li><a href="/ilofcocktails/">Home</a>
            <li><a href="/ilofcocktails/AllCocktails">List of all cocktails</a>
            <li><a href="/ilofcocktails/Trending.jsp">Trending</a>
            <li><a href="login.jsp"> Login/Register </a></li>            
        </ul>    </div>
        <%
                    }%>

               
        <%  LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            String UserName = lg.getUsername();%>
             
        
                    
                    
                  <div class="group">  
           
                  <div id="partyCircle">
                       <div id="formParty">
                   
                    <form method="POST"  action="AddParty">
                        <div class="group">
                        
                            <input value =<% out.print(UserName); %> name="hostName" type="hidden"  > </li>
                            
                            <labelPartyName>Party Name:</labelPartyName> <br>
                            <input id="p" type="text" name="partyName"> 
                          
                            <labeldate>Date(DD-MM-YYYY):</labeldate><br>
                              <input id="p" type="text" name="date">   
                           
                             <labellocation>Location:</labellocation><br>    
                             <input id="p" type="text" name="location">                      
                                                     
                             <labeltheme>Theme of Party:</labeltheme><br>
                            <input id="p" type="text" name="description">                           
                            
                            <labelpreferable>Preferable Cocktails(separated by commas):</labelpreferable><br>
                            <input id="p" type="text" name="cocktails">                         
                            
                            <labelguests>List of guests:</labelguests><br>
                            <input id="p" type="text" name="guests">  <br>                        
                          
                            
                           
                            <div class="buttonParty">
                             <input id="p" type="submit" name="Add Party" value="Add Party" class="button" >
                             </div>                          
                    </form>
                    </div>
                
            </div>
                  </div>
                  </div>
    
                    
    </body>
</html>