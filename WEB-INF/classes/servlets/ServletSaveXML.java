package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import org.jdom2.*;
import org.jdom2.output.*;

import beans.Annuaire;
import beans.Utilisateur;
import beans.UtilisateurData;

 
@WebServlet("/ServletSaveXML")
public class ServletSaveXML extends HttpServlet {
	
	private String VUE_LOGON = "/WEB-INF/logon.jsp";
	private String VUE_LIST = "/WEB-INF/afficherListe.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		/* SAUVEGARDE DES CONTACTS DANS UN FICHIER XML */
		Annuaire a = (Annuaire)session.getAttribute("annuaire");
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		String deco = request.getParameter("deco");
		
		Element racine = new Element("CONTACTS");
		Document xml = new Document(racine);
		Element nbContacts = new Element("NBContacts").setText(String.valueOf(a.getNbContact()));
		Element maxId = new Element("MaxID").setText(String.valueOf(a.getMaxId()));
		
		racine.addContent(nbContacts);
		racine.addContent(maxId);
		
		for(int i=0; i<a.getListeContact().size();i++) {
			Element contact = new Element("contact");
			Attribute att = new Attribute("id",a.getListeContact().get(i).getId());
			contact.setAttribute(att);
			
			Element nom = new Element("nom").setText(a.getListeContact().get(i).getNom());
			Element prenom  = new Element("prenom").setText(a.getListeContact().get(i).getPrenom());
			Element telephone  = new Element("telephone").setText(a.getListeContact().get(i).getTelephone());
			Element adresse  = new Element("adresse").setText(a.getListeContact().get(i).getAdresse());
			Element email  = new Element("email").setText(a.getListeContact().get(i).getEmail());
			contact.addContent(nom);
			contact.addContent(prenom);
			contact.addContent(telephone);
			contact.addContent(adresse);
			contact.addContent(email);
			
			racine.addContent(contact);
		}
		
		try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            //System.out.println("USER ID : "+user.getId());
            File f = new File("annuaire/Contacts"+user.getId()+".xml");
            sortie.output(xml, new FileOutputStream(f));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		
		if(deco != null && deco.equals("true")) session.invalidate();
		
		
		
		this.getServletContext().getRequestDispatcher(VUE_LOGON).forward(request, response);
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		/* SAUVEGARDE DES CONTACTS DANS UN FICHIER XML */
		Annuaire a = (Annuaire)session.getAttribute("annuaire");
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		String deco = request.getParameter("deco");
		
		Element racine = new Element("CONTACTS");
		Document xml = new Document(racine);
		Element nbContacts = new Element("NBContacts").setText(String.valueOf(a.getNbContact()));
		Element maxId = new Element("MaxID").setText(String.valueOf(a.getMaxId()));
		
		racine.addContent(nbContacts);
		racine.addContent(maxId);
		
		for(int i=0; i<a.getListeContact().size();i++) {
			Element contact = new Element("contact");
			Attribute att = new Attribute("id",a.getListeContact().get(i).getId());
			contact.setAttribute(att);
			
			Element nom = new Element("nom").setText(a.getListeContact().get(i).getNom());
			Element prenom  = new Element("prenom").setText(a.getListeContact().get(i).getPrenom());
			Element telephone  = new Element("telephone").setText(a.getListeContact().get(i).getTelephone());
			Element adresse  = new Element("adresse").setText(a.getListeContact().get(i).getAdresse());
			Element email  = new Element("email").setText(a.getListeContact().get(i).getEmail());
			contact.addContent(nom);
			contact.addContent(prenom);
			contact.addContent(telephone);
			contact.addContent(adresse);
			contact.addContent(email);
			
			racine.addContent(contact);
		}
		
		try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            //System.out.println("USER ID : "+user.getId());
            File f = new File("annuaire/Contacts"+user.getId()+".xml");
            sortie.output(xml, new FileOutputStream(f));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		
		if(deco != null && deco.equals("true")) session.invalidate();
		
		
		
		this.getServletContext().getRequestDispatcher(VUE_LIST).forward(request, response);
	}


}
