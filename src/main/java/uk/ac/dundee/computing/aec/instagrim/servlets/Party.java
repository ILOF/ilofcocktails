/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.dundee.computing.aec.instagrim.servlets;

import com.datastax.driver.core.Cluster;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.dundee.computing.aec.instagrim.lib.CassandraHosts;
import uk.ac.dundee.computing.aec.instagrim.models.PartyModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;
import uk.ac.dundee.computing.aec.instagrim.stores.LoggedIn;

@WebServlet(name = "Party", urlPatterns = { "/Party" })
public class Party extends HttpServlet {
	Cluster cluster = null;

	public void init(ServletConfig config) throws ServletException {
		cluster = CassandraHosts.getCluster();
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
                String partyName = request.getParameter("partyName");	
                String hostName = request.getParameter("hostName");
		String date = request.getParameter("date");
		String location = request.getParameter("location");
		String description = request.getParameter("description");
                String cocktails = request.getParameter("cocktails");
                String guests = request.getParameter("guests");
                
                
                
                
                
                
                
		
		PartyModel prt = new PartyModel();
		prt.setCluster(cluster);
		
                // uncomment the line below!!!!
		prt.addParty(hostName, date, cocktails, description, guests, location, partyName );
		response.sendRedirect("/ilofcocktails");
		
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	// OBSOLETE UNLESS CHANGED ABOVE TO USE IT
	// username and password cannot be less than 2 symbols each
	public boolean registrationError(String user, String pass) {
		if (user.length() < 2 || pass.length() < 2) {
			return true; // there is an error while registering
		} else
			return false; // registration details are fine
	}

}
