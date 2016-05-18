package com.kasap;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.QuerySolution;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class PokemonInputServlet
 */
@WebServlet("/PokemonInput")
public class PokemonInput extends HttpServlet {


	private static final long serialVersionUID = 1L;
	protected void makeQuery() {
		String queryString  = "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
				"PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
				"PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
				"PREFIX wd: <http://www.wikidata.org/entity/>\n" +
				"SELECT  ?pokemon ?pokemonLabel ?dexnumber  ?parts ?partsLabel\n" +
				"WHERE\n" +
				"  { ?pokemon wdt:P31 wd:Q3966183 .\n" +
				"    ?pokemon wdt:P1112 ?dexnumber .\n" +
				"    ?pokemon wdt:P361 ?parts .\n" +
				"    SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\" }\n" +
				"  }\n" +
				"ORDER BY ?dexnumber";

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query);
		ResultSet results = qexec.execSelect();
		
		ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
		while(results.hasNext()) {
			QuerySolution row = results.nextSolution();
			Pokemon poke = new Pokemon(row);
			pokemonList.add(poke);

		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PokemonInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		makeQuery();
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		
		out.println("<h3>List Pokémons</h3>");
		out.println("<p>Enter a number of Pokédex number between 1-700. It will find the closest 10 Pokémons for this Pokédex number.</p>");
		out.println("<p><a href=Pokemon_Input>Make a query for population<a></p>");
		out.println("<p><a href=Pokemon_Saved>Saved countries<a></p>");

		//out.println("</body>");

		out.println("</body>");
		out.println("</html>");

	}

}
