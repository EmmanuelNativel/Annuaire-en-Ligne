package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Annuaire;
import beans.Contact;


@WebServlet("/AfficherFiche")
public class AfficherFiche extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idContact = request.getParameter("idContact");
		
		HttpSession session = request.getSession();
		
		Annuaire a = (Annuaire)session.getAttribute("annuaire");
		Contact u = a.getContact(idContact);
		
		request.setAttribute("currentContact", u);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/afficherListe.jsp").forward(request, response);
	}

}
