package it.objectmethod.esercizio.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.dao.ActorDao;
import it.objectmethod.esercizio.dao.ConnectionManager;

public class ActorDaoImpl implements ActorDao{

	@Override
	public List<Actor> getAllActors() {
		List<Actor> ret = new ArrayList<Actor>();
		Connection conn = ConnectionManager.getConnection();
		Statement statement=null;
		try {
			statement = conn.createStatement();
			String query = "SELECT first_name,last_name FROM sakila.actor ORDER BY first_name";
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				Actor a = new Actor();
				a.setFirstname(result.getString("first_name"));
				a.setLastname(result.getString("last_name"));
				ret.add(a);
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			if(statement!=null) {
				statement.close();
			}
			if(conn!=null) {
				conn.close();
			}
				
		}
		catch(Exception se){
			se.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<Actor> getActorsByFilmName(String film) {
		List<Actor> ret = new ArrayList<Actor>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement statement=null;
		try {
			String query ="SELECT actor.first_name,actor.last_name ";
			query+="FROM actor JOIN film_actor JOIN film ON actor.actor_id=film_actor.actor_id AND film_actor.film_id=film.film_id ";
			query+="WHERE film.title=? ";
			query+="ORDER BY actor.first_name, actor.last_name";
			statement = conn.prepareStatement(query);
			statement.setString(1, film.toUpperCase());
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Actor a = new Actor();
				a.setFirstname(result.getString("first_name"));
				a.setLastname(result.getString("last_name"));
				ret.add(a);
			}
			result.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			if(statement!=null) {
				statement.close();
			}
			if(conn!=null) {
				conn.close();
			}
				
		}
		catch(Exception se){
			se.printStackTrace();
		}
		return ret;
	}

}
