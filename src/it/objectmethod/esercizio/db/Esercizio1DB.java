package it.objectmethod.esercizio.db;
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Esercizio1DB extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/sakila";

		final String USER = "root";
		final String PASS = "password";
	   
		String listoutput = new String();
		System.out.println("Ho creato le stringhe, fin qua ci sono!");
		Connection conn = null;
		Statement statement = null;
		System.out.println("Connessione e statement creati");
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			statement = conn.createStatement();
			
			String query = "SELECT first_name,last_name FROM sakila.actor ORDER BY first_name";
			ResultSet result = statement.executeQuery(query);
			
			String first = new String();
			String last = new String();
			while(result.next()){
				listoutput+="<tr>";
				first = result.getString("first_name");
				last = result.getString("last_name");
				listoutput+="<td>"+first+"</td>";
				listoutput+="<td>"+last+"</td>";
				listoutput+="</tr>";
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
		request.setAttribute("table", listoutput);
		request.getRequestDispatcher("jsp/Esercizio1DBOutput.jsp").forward(request, response);
	}
}
