package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Annuaire;

@WebServlet("/ModifierServlet")
public class ModifierServlet extends HttpServlet {
	
	String SERVLET_SAVE_XML = "/ServletSaveXML";
	String VUE_MODIFIER = "/WEB-INF/Contact.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String telephone = request.getParameter("telephone");
		String adresse = request.getParameter("adresse");
		String email = request.getParameter("email");
		String idModif = request.getParameter("idModif");
		
		
		if(idModif == null) { //Si vrais, on ne doit pas afficher le formulaire de modification de contact
			HttpSession session = request.getSession();
			Annuaire a = (Annuaire)session.getAttribute("annuaire");
			
			if(id==null) { //On doit afficher le formulaire d'ajout d'un contact
				
				this.getServletContext().getRequestDispatcher(VUE_MODIFIER).forward(request, response);
				
			} else if(id.equals("new")) { //On ajoute le contact	
				if(!nom.isEmpty() && !prenom.isEmpty()) {
					a.addContact(nom, prenom, telephone, adresse, email);
					session.setAttribute("annuaire", a);
				} else {
					request.setAttribute("error", "Les champs obligatoires ne sont pas remplis");
				}
				this.getServletContext().getRequestDispatcher(SERVLET_SAVE_XML).forward(request, response);
				
			} else {  //On modifie un contact
				if(!nom.isEmpty() && !prenom.isEmpty()) {
					a.modifierContact(id, nom, prenom, telephone, adresse, email);
					session.setAttribute("annuaire", a);
				}
				this.getServletContext().getRequestDispatcher(SERVLET_SAVE_XML).forward(request, response);
			}
			
		} else { //On doit afficher le formulaire de modification de contact
			request.setAttribute("idModif", idModif);
			this.getServletContext().getRequestDispatcher(VUE_MODIFIER).forward(request, response);
		}
	}

}
