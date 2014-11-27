<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator

testing for commit
--%>
<link href='http://fonts.googleapis.com/css?family=PT+Sans|Lobster|Comfortaa:400,700|Playball' rel='stylesheet' type='text/css'> </link>
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
            Cluster cluster;
            cluster = CassandraHosts.getCluster();
            cm.setCluster(cluster);
           

            java.util.LinkedList<CocktailStore> Cocktails = (java.util.LinkedList<CocktailStore>) request.getAttribute("Cocktails");


        %>
        <%
            if (Cocktails == null) {
        %>
        <%
        } else {

        %>
        <%            for (int j = 0; j < Cocktails.size(); j++) {
                CocktailStore d = Cocktails.get(j);


        %>
        
        <div class="groupAllCocktails" color:white; ">  
             <label></label><br>
        </div>
        <a href="javascript:ReverseDisplay('<%=d.getId()%>')" >
            <%out.println(d.getCocktailName());%>
        </a>
       
        <div id="<%=d.getId()%>" style="display:none; color:white; ">
             <div id="data_block1">  
            <p><% out.println("Occasion:" + d.getOcasion() + " |  Glass:" + d.getGlass()  + " | Garnish:" + d.getGarnish()  + " | Type: " + d.getType());%></p>
            <a><% out.println(d.getSoi1() + "<br>" + d.getSoi2() + "<br>" + d.getSoi3() + "<br>" + d.getSoi4() + "<br>" + d.getSoi5() + "<br>" + d.getSoi6() + "<br>" + d.getSoi7() + "<br>" + d.getSoi8() + "<br>" + d.getSoi9() + "<br>" + d.getSoi10() + "<br>" + d.getSoi11() + "<br>" + d.getSoi12());%></a>    
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
