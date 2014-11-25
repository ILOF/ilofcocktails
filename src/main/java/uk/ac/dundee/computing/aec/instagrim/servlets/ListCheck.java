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
import uk.ac.dundee.computing.aec.instagrim.stores.ListStore;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "ListCheck", urlPatterns = { "/ListCheck" })
public class ListCheck extends HttpServlet {
	Cluster cluster = null;

	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
                CocktailModel cm = new CocktailModel();
                cm.setCluster(cluster);
                java.util.LinkedList<String> Drinks = new java.util.LinkedList<>();
                java.util.LinkedList<String> ingredientsHad = new java.util.LinkedList<>();
                Drinks.add("247");
                Drinks.add("619");
                ingredientsHad.add("random");

                ListStore List = cm.ReturnShopping(ingredientsHad, Drinks);
                RequestDispatcher rd = request.getRequestDispatcher("/ShowList.jsp");
                request.setAttribute("Cocktails", List);
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