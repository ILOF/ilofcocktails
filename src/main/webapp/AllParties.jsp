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
      <script type="text/javascript" language="JavaScript">
     function HideContent(d) {
            document.getElementById(d).style.display = "none";
        }
        function ShowContent(d) {
            document.getElementById(d).style.display = "block";
        }
        function ReverseDisplay(d) 
        {
            if (document.getElementById(d).style.display == "none") 
            {
                document.getElementById(d).style.display = "block";
            }
            else 
            {
                document.getElementById(d).style.display = "none";
            }
        }
            </script>
                    

<div id="data_block"> 

    <article>

        <%
            CocktailModel cm = new CocktailModel();
            PartyModel pm = new PartyModel();
            Cluster cluster;
            cluster = CassandraHosts.getCluster();
            pm.setCluster(cluster);
            cm.setCluster(cluster);
            String shopping = "";
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
             java.util.LinkedList<String> ingDisplay = new java.util.LinkedList<>();

                ListStore ls = new ListStore();
                java.util.LinkedList<String> CocktailIDss = new java.util.LinkedList<>();
                java.util.LinkedList<String> ing = new java.util.LinkedList<>();
                ing.add("asdasdasdasdsad");
                String Cocktailnames = "";
            for (int i = 0; i < Parties.size(); i++) {
                PartyStore p = Parties.get(i);
                Cocktailnames = "";
                String[] CocktailIds = p.getCocktails().split(",");
                for(int k = 0; k < CocktailIds.length; k++){
                    CocktailIDss.add(CocktailIds[k]);
                    Cocktailnames = Cocktailnames + cm.nameid(CocktailIds[k]);
                    if(k < CocktailIds.length-1){
                        Cocktailnames = Cocktailnames + " | ";
                    }
                }
                ls = cm.ReturnShopping(ing, CocktailIDss);
                ingDisplay = ls.getItems();
                shopping = cm.shopping(p.getCocktails());
                


        %>

        <div class="groupAllCocktails" color:white; ">  
             <label></label><br>
        </div>
        <a href="javascript:ReverseDisplay('<%=p.getPartyName()%>')" >
            <%out.println(p.getPartyName());%>
        </a>
       
        <div id="<%=p.getPartyName()%>" style="display:none; color:white; ">
             <div id="data_block1">  
                    <p><% out.println("Hosted by: " + p.getHostName()); %></p>
                 <p><% out.println("Date: " + p.getDate() + "| Location: " + p.getLocation() + "| Theme: " + p.getDescription()); %></p>
                 <p><% out.println("Guests: " + p.getGuests()); %></p>
                 <p><% out.println("Cocktails: " + Cocktailnames); %></p>
                 <a><% out.println("Shopping: " + shopping); %></a>
            
            
            
        </div>
        </div>
        <%
                 
             }
            }
        %>
        
    </article>
        </div>

</body>
</html>
