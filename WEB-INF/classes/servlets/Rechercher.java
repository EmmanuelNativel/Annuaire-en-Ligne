package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import beans.Annuaire;
import beans.Contact;
import java.io.*;


@WebServlet("/Rechercher")
public class Rechercher extends HttpServlet {
	String VUE_C = "/WEB-INF/choix_type.jsp";
	String VUE_L = "/WEB-INF/afficherListe.jsp";
	

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String resetList = request.getParameter("resetList");
		
		if(resetList != null && resetList.equals("true")) { //Réafficher la liste complète
			request.setAttribute("listeRechercher", null);
			this.getServletContext().getRequestDispatcher(VUE_L).forward(request,response);
		}
		else if(type == null) { //Pas de recherche
			this.getServletContext().getRequestDispatcher(VUE_C).forward(request,response);

		}else { //recherche d'un contact
			request.setAttribute("type", type);
			this.getServletContext().getRequestDispatcher(VUE_C).forward(request,response);
		}		
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//request.setAttribute("erreur", null);
		String mot =request.getParameter("champ");
		Annuaire a =(Annuaire) session.getAttribute("annuaire");
		String type= request.getParameter("type");
		
		ArrayList<Contact> l= a.rechercherContact(mot,type); //liste des contacts correspondants à la recherche
		
		if(l.size()<=0) request.setAttribute("erreur", "Aucun Contact trouvé");
		
		request.setAttribute("listeRechercher", l);
		this.getServletContext().getRequestDispatcher(VUE_L).forward(request,response);
		
		
	}
}
