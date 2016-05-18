package com.kasap;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class MenafServlet
 */
@WebServlet("/MenafServlet")
public class MenafServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenafServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		
		out.println("<h3>List Pokémons</h3>");
		out.println("<p>Enter a Pokédex number between 1-700. It will find 10 Pokémons which have the closest Pokédex numbers.</p>");
		out.println("<p><a href=PokemonInput>Make a query for population<a></p>");
		out.println("<p><a href=PokemonSaved>Saved countries<a></p>");

		out.println("</body>");
		out.println("</html>");

	}

}
