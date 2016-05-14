import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.atlas.json.JsonArray;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Hello() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		/*
		 * ********DATABASE *********
		 */
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("here");
		}catch(ClassNotFoundException e){

		}

		String url = "jdbc:mysql://localhost/mehmettest";
		String user = "root";
		String password = "";
		Connection conn = null;
		try{
			conn = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Success");
		}
		catch(SQLException e){
			System.out.println("Something went wrong");
		}
		/*
		 * ********DATABASE *********
		 */

		String qTerm = "PREFIX bd: <http://www.bigdata.com/rdf#>\n" +
				"PREFIX wikibase: <http://wikiba.se/ontology#>\n" +
				"PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n" +
				"PREFIX wd: <http://www.wikidata.org/entity/>\n" +
				"SELECT ?playerLabel ?teamLabel ?positionLabel WHERE {" 
				+"?player wdt:P106 wd:Q19204627.\n"
				+"?team wdt:P31 wd:Q17156793.\n"
				+"?player wdt:P54 ?team.\n"
				+"?player wdt:P413 ?position.\n"
				+"SERVICE wikibase:label {bd:serviceParam wikibase:language\"en\"}} limit 10 ";
		//				String docType =
		//						"<!doctype html public \"-//w3c//dtd html 4.0 " +
		//								"transitional//en\">\n";
		//				out.println(docType +
		//						"<html>\n" +
		//						"<head><title>" + title + "</title></head>\n" +
		//						"<body bgcolor=\"#f0f0f0\">\n" +
		//						"<h1 align=\"center\">" + title + "</h1>\n" +
		//						"<ul>\n" +
		//						"  <li><b>Search Query is </b>: "
		//						+ request.getParameter("query") + "\n" +
		//						"</ul>\n") ;
		//						out.println("</body></html>");
		Query query = QueryFactory.create(qTerm);
		QueryExecution qe = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql",query);
		ResultSet results = qe.execSelect();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ResultSetFormatter.outputAsXML(outputStream, results);
		String xml = new String(outputStream.toByteArray());
		System.out.println(xml);
		
		 

		//		ResultSet rs = qe.execSelect();
		//		while(rs.hasNext()){
		//
		//		 QuerySolution binding = rs.nextSolution();                     
		//		 System.out.println(binding.getLiteral(jSon)); 
		//		}




	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
