
<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instagrim.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagrim</title>
        <link rel="stylesheet" type="text/css" href="/ilofcocktails/Styles.css" />
    </head>
    <body>
        <header>
        <%  LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");%>
        <h1><center>InstaGrim ! </center></h1>
        <h2><center>Your world in Black and White</center></h2>
        
        <h2>Logged in as: <% out.println(lg.getUsername()); %></h2>
        </header>
        
        <nav>
            <ul>
                <li class="nav"><a href="/ilofcocktails/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/ilofcocktails/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
 
        <article>
            <h1>Your Pics</h1>
        <%
            java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
            if (lsPics == null) {
        %>
        <p>No Pictures found</p>
        <%
        } else {
        	/*
            Iterator<Pic> iterator;
            iterator = lsPics.iterator();
            while (iterator.hasNext()) {
                Pic p = (Pic) iterator.next();
                */
            for (int i =0; i<lsPics.size(); i++ ){	// my code, replicates Andy's code above
             	Pic p = lsPics.get(i);
                

        %>
        <!-- deleted a "<br/>" at the end of the previous line, the Break made the pics display in a column-->
        
        <a href="/ilofcocktails/Image/<%=p.getSUUID()%>" ><img src="/ilofcocktails/Thumb/<%=p.getSUUID()%>"></a><%
        

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