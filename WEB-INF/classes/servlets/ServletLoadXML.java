package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.util.List;

import beans.Utilisateur;
import beans.Annuaire;
import beans.UtilisateurData;

@WebServlet("/ServletLoadXML")
public class ServletLoadXML extends HttpServlet {
	
	private String VUE = "/WEB-INF/afficherListe.jsp";
	private String VUE_LOGON = "/WEB-INF/logon.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*LOAD XML UTILISATEURS */
		
		UtilisateurData UData = new UtilisateurData(); //crée un nouvel objet représentant uen liste d'utilisateurs 
		
		//On va charger le fichier XML et reconstruire une liste d'Utilisateur à partir de ce fichier
		String fileNameUser = "Utilisateurs.xml";
		
		SAXBuilder builderUser = new SAXBuilder();
		File fichierXMLUser = new File("annuaire/"+fileNameUser);
		Document documentUser;
		try {
			
			documentUser = builderUser.build(fichierXMLUser);
			
			Element racineUser = documentUser.getRootElement();
			Element maxIdUser = racineUser.getChild("MaxIdUser");
			List<Element> utilisateur = racineUser.getChildren("utilisateur");
			UData.setMaxIdUser(Integer.parseInt(maxIdUser.getText()));
			
			for(int i=0; i<utilisateur.size();i++) {				
				UData.addUtilisateur( Integer.parseInt(utilisateur.get(i).getAttributeValue("id")), 
									 utilisateur.get(i).getChild("login").getText(), 
									 utilisateur.get(i).getChild("pass").getText() );
			}
			
		} catch (JDOMException e ) {
			e.printStackTrace();
		} 
		catch (IOException e ) {
			e.printStackTrace();
		}
		
		this.getServletContext().setAttribute("UData", UData);
		
		
		this.getServletContext().getRequestDispatcher(VUE_LOGON).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*LOAD XML CONTACTS */
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
		Annuaire annuaire = new Annuaire();
		
		String fileName = "Contacts"+user.getId()+".xml";
		
		SAXBuilder builder = new SAXBuilder();
		File fichierXML = new File("annuaire/"+fileName);
		Document document;
		try {
			
			document = builder.build(fichierXML);
			
			Element racine = document.getRootElement();
			Element nbContact = racine.getChild("NBContacts");
			Element maxId = racine.getChild("MaxID");
			List<Element> contacts = racine.getChildren("contact");
			annuaire.setNbContact(Integer.parseInt(nbContact.getText()));
			annuaire.setMaxId(Integer.parseInt(maxId.getText()));
			
			for(int i=0; i<contacts.size();i++) {				
				annuaire.addContact( Integer.parseInt(contacts.get(i).getAttributeValue("id")), 
									 contacts.get(i).getChild("nom").getText(), 
									 contacts.get(i).getChild("prenom").getText(),
									 contacts.get(i).getChild("telephone").getText(),
									 contacts.get(i).getChild("adresse").getText(),
									 contacts.get(i).getChild("email").getText());
			}
			
		} catch (JDOMException e ) {
			e.printStackTrace();
		} 
		catch (IOException e ) {
			e.printStackTrace();
		}
		
		session.setAttribute("annuaire", annuaire);
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		
	}


}
