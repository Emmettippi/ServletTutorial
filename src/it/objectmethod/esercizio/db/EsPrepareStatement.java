package it.objectmethod.esercizio.db;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EsPrepareStatement extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String strfilm = "'"+request.getParameter("filmname")+"'";
		int error=0;
		
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/sakila";

		final String USER = "root";
		final String PASS = "password";
	   
		String output = "";
		
		Connection conn = null;
		PreparedStatement statement = null;
		ArrayList<Actor> actorlist = new ArrayList<Actor>(0);
		System.out.println("Crea l'Arraylist");
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String query = new String();
			//System.out.println("Crea la Stringa query");
			query+="SELECT actor.first_name,actor.last_name ";
			query+="FROM actor JOIN film_actor JOIN film ON actor.actor_id=film_actor.actor_id AND film_actor.film_id=film.film_id ";
			query+="WHERE film.title=? ";
			query+="ORDER BY actor.first_name, actor.last_name";
			//System.out.println("Compilata stringa query");
			//ANZITUTTO non so se questo sia corretto
			statement = conn.prepareStatement(query);
			//System.out.println("statement = conn.prepareStatement(query);");
			statement.setString(1, strfilm);
			///////////////////////////////////////////////////
			//È qua che fa casino, ma non so perché////////////
			//So solo che non modifica la query come dovrebbe//
			///////////////////////////////////////////////////
			//System.out.println("statement.setString(1, strfilm);");
			System.out.println(query);
			statement.executeUpdate(query);
			//System.out.println("statement.executeUpdate(query);");
			
			Actor actor = new Actor();
			//System.out.println("Actor actor = new Actor();");
			ResultSet result = statement.executeQuery(query);
			//System.out.println("ResultSet result = statement.executeQuery(query);");
			int i=0;
			while(result.next()){
				actor.SetBoth(result.getString("actor.first_name"), result.getString("actor.last_name"));
				actorlist.add(actor);
				System.out.println(actorlist.get(i).GetFirstName()+actorlist.get(i).GetLastName());
				i++;
				System.out.println(i+"");
			}
			if(output.equals("")){
				error=2;
			}
			result.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(statement!=null) {
					conn.close();
				}
			}
			catch(SQLException se){
				//NULL;
			}
			//Perché ripete il codice?
			try{
				if(conn!=null)
				conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		strfilm.replaceAll("'", "");
		request.setAttribute("actor", actorlist);	//la lista
		request.setAttribute("film", strfilm);		//il nome del film
		request.setAttribute("error", error);		//errore
		request.setAttribute("i", actorlist.size());				//la lunghezza della lista
		request.getRequestDispatcher("jsp/PSOutput.jsp").forward(request, response);
	}
}
