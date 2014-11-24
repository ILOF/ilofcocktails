/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.CocktailModel;
import uk.ac.dundee.computing.aec.instagrim.models.PicModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.CocktailStore;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "SearchCocktail", urlPatterns = { "/SearchCocktail" })
public class SearchCocktail extends HttpServlet {
	Cluster cluster = null;

	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String SearchCriteria = request.getParameter("searchCriteria");
		SearchCriteria = SearchCriteria.toLowerCase();
                CocktailModel cm = new CocktailModel();
                cm.setCluster(cluster);
                java.util.LinkedList<CocktailStore> Cocktails = cm.SearchOcasion(SearchCriteria);
                RequestDispatcher rd = request.getRequestDispatcher("/AllCocktails.jsp");
                request.setAttribute("Cocktails", Cocktails);
                rd.forward(request, response);
		//get text box string
                //remove white space, make lower case
                //puts new string in to seach function on cocktail model
                //this will return a linked list --
                //redirect to cocktail showing page, giving it the linked list
        
		
	}
        
        private void error(String error, HttpServletResponse response) throws ServletException, IOException {
       PrintWriter out = null;
        out = new PrintWriter(response.getOutputStream());
        out.println(error);
        out.println("Press the 'back' button to go back to register page");

        out.close();
        return;
    }

	
	@Override
	public String getServletInfo() {
		return "Short description";
	}

}