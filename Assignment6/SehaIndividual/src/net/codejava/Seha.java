package net.codejava;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/Seha")
public class Seha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Seha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
    	String key= request.getParameter("key");
    	PrintWriter wri=response.getWriter();
    	
    	
    	 String resString ="<html> <h2>Key : "+ key+ " </h2> </html>";
    	 wri.println(resString);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      out.println("<h3>" + "This is Seha's unsuccessful  trial." + "</h3>");
	      out.println("<h3>" + "My SPARQL query is commented, you can look up. Topic was singers who get an award (like Grammy) and their groups (ie Abba, Led Zeppelin)" + "</h3>");
	      out.println("<h3>" + "You can check searchBox.html" + "</h3>");
/*
		String queryString=
				"PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
		                "PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
		                "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
		                "PREFIX wd: <http://www.wikidata.org/entity/>\n" +
		                "SELECT ?singer ?singerLabel ?membership ?membershipLabel ?award ?awardLabel\n" +
		                "WHERE\n" +
		                "{\n" +
		                "\t?singer wdt:P106 wd:Q177220 .\n" +
		                "?singer wdt:P463 ?membership .\n" +
		                "?membership wdt:P166 ?award .\n" +
		                "\tSERVICE wikibase:label { bd:serviceParam wikibase:language \"en\" }\n" +
				" }";

				Query query = QueryFactory.create(queryString);
				QueryExecution qexec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", query);

				try {
				    ResultSet results = qexec.execSelect();
				    for (; results.hasNext();) {

				    // Result processing is done here.
				    }
				}
				finally {
				   qexec.close();
				}
		*/
       
		
	}

}
