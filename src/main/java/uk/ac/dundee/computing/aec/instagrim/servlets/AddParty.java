
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
import uk.ac.dundee.computing.aec.instagrim.models.CocktailModel;
import uk.ac.dundee.computing.aec.instagrim.models.PartyModel;
import uk.ac.dundee.computing.aec.instagrim.models.User;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "AddParty", urlPatterns = { "/AddParty" })
public class AddParty extends HttpServlet {
	Cluster cluster = null;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		cluster = CassandraHosts.getCluster();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
            
                PartyModel pm = new PartyModel();
                CocktailModel cm = new CocktailModel();
                pm.setCluster(cluster);
                cm.setCluster(cluster);
                               String[] Names = request.getParameter("cocktails").split(", ");
                               String test = "";
                               for (int i = 0; i< Names.length; i++){
                                   test = test+cm.idname(Names[i]);
                                   if(i<Names.length-1){
                                       test = test+",";
                                   }
                               }

                String CocktailIds = cm.IDsFromNames(request.getParameter("cocktails"));
                            
                String partyName = request.getParameter("partyName");
                String hostName = request.getParameter("hostName");
                String date = request.getParameter("date");
                String location = request.getParameter("location");
                String description = request.getParameter("description");
                String cocktails = request.getParameter("cocktails");
                String guests = request.getParameter("guests");
                                
                
                pm.addParty(hostName, date, test, description, guests, location, partyName);
                response.sendRedirect("/ilofcocktails/AllParties");
               
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
        /*
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	*/	
        
        
	@Override
	public String getServletInfo() {
		return "Short description";
	}
}