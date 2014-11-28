<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator

testing for commit
--%>
<link href='http://fonts.googleapis.com/css?family=PT+Sans|Lobster|Comfortaa:400,700|Playball' rel='stylesheet' type='text/css'> </link>
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

    <div class="titleCocktail">
        <h1> ILOFCocktails</h1> 
    </div>

    <div class="header-image1">
        <img src="bannerbanner.png">
    </div>
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
            <li><a href="/ilofcocktails/">Home</a>
            <li><a href="/ilofcocktails/AllCocktails">List of all cocktails</a>
               
            <li><a href="/ilofcocktails/Trending.jsp">Trending</a>
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
    
                    
                    
    <form method="POST" action="/ilofcocktails/SearchParty">            
        <div class="allpartyfun">  
            <input type="text" required name = "searchCriteria">
            <span class="bar"></span>
            <span class="highlight"></span>
            <label> Search by Party Name: </label>

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
            for (int i = 0; i < Parties.size(); i++) {
                PartyStore p = Parties.get(i);

        %>

        <div class="allpartyfun">  
            <input type="text" required readonly>


            <labelpartydesc><% out.println(p.getPartyName() + " hosted by " + p.getHostName() + " on " + p.getDate()); %></labelpartydesc>

            <!-- Check the details:               -->
            <form method="POST"  action="selectedParty">
                <input type="submit" value="Check Details"> 
            </form>
        </div>

        <%

                }
            }
        %>
        
    </article>
    
</body>
</html>
