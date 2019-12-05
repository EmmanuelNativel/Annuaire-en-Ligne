package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import beans.UtilisateurData;

//ORIGINAL
@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {

	private String VUE_LOGON = "/WEB-INF/logon.jsp";
	private String SERVLET_XML_LOAD = "/ServletLoadXML";
	private String SERVLET_XML_SAVE = "/ServletSaveXML";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String createUser = request.getParameter("createUser");
		
		if(createUser != null){ //Création
			request.setAttribute("etat", "create");
		} else { //Pas création
			request.setAttribute("etat", null);
		}
		
		//Affichage de la jsp de connexion
		this.getServletContext().getRequestDispatcher(SERVLET_XML_LOAD).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String login = request.getParameter("login"); 
		String pass = request.getParameter("pass");
		String passConfirm = request.getParameter("passConfirm");
		String deconnection = request.getParameter("deconnection");
		UtilisateurData UData = (UtilisateurData) this.getServletContext().getAttribute("UData");

		
		if( deconnection != null && deconnection.equals("true") ) { //Si on doit déconnecter l'utilisateur
			request.setAttribute("deco", "true");
			this.getServletContext().getRequestDispatcher(SERVLET_XML_SAVE).forward(request, response);
		} else { 
			
			if(passConfirm==null) { //Si on est en mode Connexion 
				if( UData.verifierAuthentification(login, pass) && !login.isEmpty() && !pass.isEmpty()) {
					Utilisateur user = UData.getUtilisateur(login);
					//Ajouter l'objet utilisateur en paramètre de la session
					session.setAttribute("utilisateur", user);
					request.setAttribute("erreur", null);//Permet de ne pas afficher les erreur du formulaire
					this.getServletContext().getRequestDispatcher(SERVLET_XML_LOAD).forward(request, response);
				} else {
					request.setAttribute("erreur", "La connexion a échoué");//On envoie le message d'erreur
					this.getServletContext().getRequestDispatcher(VUE_LOGON).forward(request, response);
				}
			} else { //Si on est en mode création de contact
				Utilisateur u = UData.getUtilisateur(login);
				
				if(u == null) { //Si le contact n'existe pas déjà
					if(!login.isEmpty() && !pass.isEmpty() && !passConfirm.isEmpty()) { //si les champs ne sont pas vides
						if(pass.equals(passConfirm)) { //et que les mdp correspondent
							u = new Utilisateur();
							u.setLogin(login);
							u.setPass(pass);
							UData.addUtilisateur(u);
							session.setAttribute("utilisateur", u);
							request.setAttribute("succes", "Inscription réussie, Vous pouvez vous connecter");
							
						} else { //les mots de passes ne correspondent pas
							request.setAttribute("etat", "create");//on reste sur le formulaire de la création de compte
							request.setAttribute("erreur", "Les deux mots de passes ne correspondent pas");
						} 
					} else { //Veuillez remplir tous les champs du formulaire
						request.setAttribute("etat", "create");//on reste sur le formulaire de la création de compte
						request.setAttribute("erreur", "Veuillez remplir tous les champs du formulaire");
					} 
				} else { //utilisateur existe déjà
					request.setAttribute("etat", "create");//on reste sur le formulaire de la création de compte
					request.setAttribute("erreur", "L'identifiant existe déjà");
				} 
				
				
				this.getServletContext().setAttribute("UData", UData);
				this.getServletContext().getRequestDispatcher("/ServletSaveUserXML").forward(request, response);
			}
		}	
		
	}

}
