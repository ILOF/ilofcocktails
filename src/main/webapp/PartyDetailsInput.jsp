<%@page import="uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    
    <body>
        <header>
        <h1><center> ILOF Cocktails </center></h1>
        <h2><center>Add New Party </center></h2>        
        </header>
               
        <%  LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            String UserName = lg.getUsername();%>
       
        <article>
            <h3>Add New Party Details</h3>
            <form method="POST"  action="AddParty">
                <ul>
                    <li>Host: <% out.print(UserName); %></li>
                    <li>Party Name: <input type="text" name="partyName"></li>
                    <input value =<% out.print(UserName); %> name="hostName" type="hidden"  > </li>
                    <li>Date of the party (dd-mm-yyyy): <input type="text" name="date"></li>
                    <li>Location: <input type="text" name="location"></li>
                    <li>Theme/Description: <input type="text" name="description"></li>
                    <li>Preferable cocktails (separated by commas): <input type="text" name="cocktails"></li>                    
                    <li>List of guests (separated by commas): <input type="text" name="guests"></li>                
                    </ul>
                <br/>
                <input type="submit" value="Add Party"> 
            </form>
            
            

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/ilofcocktails">Home</a></li>
            </ul>
        </footer>
    </body>
</html>