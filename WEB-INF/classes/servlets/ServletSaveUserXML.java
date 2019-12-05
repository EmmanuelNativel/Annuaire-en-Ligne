package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import beans.UtilisateurData;


@WebServlet("/ServletSaveUserXML")
public class ServletSaveUserXML extends HttpServlet {
	
	private String VUE_LOGON = "/WEB-INF/logon.jsp";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*SAUVEGARDE DES UTILISATEURS EN XML */
		
		UtilisateurData UData = (UtilisateurData) this.getServletContext().getAttribute("UData");
		
		Element racineUser = new Element("UTILISATEURS");
		Document xmlUser = new Document(racineUser);
		Element maxIdUser = new Element("MaxIdUser").setText(String.valueOf(UData.getMaxIdUser()));
		
		racineUser.addContent(maxIdUser);
		
		for(int i=0; i<UData.getListeUtilisateur().size();i++) {
			Element utilisateur = new Element("utilisateur");
			Attribute attUser = new Attribute("id",String.valueOf(UData.getListeUtilisateur().get(i).getId()));
			utilisateur.setAttribute(attUser);
			
			Element login = new Element("login").setText(UData.getListeUtilisateur().get(i).getLogin());
			Element pass  = new Element("pass").setText(UData.getListeUtilisateur().get(i).getPass());
			utilisateur.addContent(login);
			utilisateur.addContent(pass);
			
			racineUser.addContent(utilisateur);
		}
		
		try{
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            File f = new File("annuaire/Utilisateurs.xml");
            sortie.output(xmlUser, new FileOutputStream(f));
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher(VUE_LOGON).forward(request, response);
	}

}
