package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Annuaire;


@WebServlet("/SupprimerServlet")
public class SupprimerServlet extends HttpServlet {
	
	private String SERVLET_SAVE_XML = "/ServletSaveXML";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("idSuppr");
		
		HttpSession session = request.getSession();
		
		Annuaire a = (Annuaire)session.getAttribute("annuaire");
		a.supprimerContact(id);
		
		session.setAttribute("annuaire", a);
		
		this.getServletContext().getRequestDispatcher(SERVLET_SAVE_XML).forward(request, response);
	}



}
