package net.codejava;

import java.io.FileReader;
import java.sql.ResultSet;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.IOException;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jdk.internal.org.xml.sax.SAXException;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.xml.internal.txw2.Document;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchKey = request.getParameter("searchKey");
		
		//System.out.println("key: " + key);
		
		PrintWriter writer = response.getWriter();
		
		String htmlRespone = "<html>";
		htmlRespone += "<h2>Your key is : " + searchKey + " </h2>";
		htmlRespone += "</html>";
		
		writer.println(htmlRespone);
		//writer.println("I can not do a connection with json and my database.");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String query = "https://query.wikidata.org/sparql?query=";
		query += "select ?universityLabel ?countryLabel ?inception WHERE {" +
			"?university wdt:P31/wdt:P279* wd:Q3918 ;"+
	        "wdt:P571 ?inception ."+
	        "?university wdt:P17 ?country ." +
		    "SERVICE wikibase:label {"+
			"bd:serviceParam wikibase:language \"en, de\" ." +
		    "}"+
		    "}"+
		    "limit 10" ;
		Query query2 = QueryFactory.create(query); //s2 = the query above
        QueryExecution qExe = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", query );
        ResultSet results = qExe.execSelect();
        ResultSetFormatter.out(System.out, results, query2) ;*/
	}
}
