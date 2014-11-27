<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator

testing for commit
--%>

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
    </head>
    <body>
        <header>
        
        <h1>ILOFCocktails</h1>
      
        </header>
        <nav>
            <ul>
                <li class="nav"><a href="/ilofcocktails/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/ilofcocktails/Images/majed">Sample Images</a></li>
            </ul>
        </nav>
 
     
        <div id ="searchbar"
        <form method="POST" action="/ilofcocktails/SearchCocktail">
            
                        <div class="group">  

            <input type="text" required name = "searchCriteria">

            <span class="bar"></span>
                                    <span class="highlight"></span>

            <label>Occasion e.g. Mothers Day</label>
            
       <%--   <input type="submit" value="Search"> <br><br> --%>
             
              </div>

                    </form>         

    </div>
    
    
    
    
   
   
        
        <article>
        
        <%
            CocktailModel cm = new CocktailModel();
            Cluster cluster;
            cluster = CassandraHosts.getCluster();
            cm.setCluster(cluster);
            //java.util.LinkedList<CocktailStore> Cocktails = cm.AllCocktails();

            java.util.LinkedList<CocktailStore> Cocktails = (java.util.LinkedList<CocktailStore>) request.getAttribute("Cocktails");
            java.util.LinkedList<CocktailStore> Trending = (java.util.LinkedList<CocktailStore>) request.getAttribute("Trending");

            
            %>
            <p><% //out.println(Cocktails.size()); %></p>
            <%
            if (Cocktails == null) {                
        %>
        <p>Trending Cocktails</p>
        <%
        } else {
                
                %>
                <a>Trending</a>
                <%
           // Iterator<CocktailStore> iterator;
            //iterator = Cocktails.iterator();
            //while (iterator.hasNext()) {
                for(int i = 0; i < Trending.size(); i++){
                CocktailStore c = Trending.get(i);

        %>
        
                                <div class="group">  
             <input type="text" required readonly>

        <label><% out.println(c.getCocktailName()+"   "+c.getId()); %></label><br>

                                </div>
        
        <%

            }
                for(int j = 0; j < Cocktails.size(); j++){
                       CocktailStore d = Cocktails.get(j);

                 
                       %>
                               <div class="group">  
             <input type="text" required readonly>

        <label><% out.println(d.getCocktailName()+"   "+d.getId()); %></label><br>

                                </div>
                       <%
                       
                       
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
