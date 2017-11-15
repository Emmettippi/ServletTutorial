package it.objectmethod.esercizio.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
			String query = "SELECT first_name,last_name,actor_id FROM sakila.actor ORDER BY first_name";
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				Actor a = new Actor();
				a.setFirstname(result.getString("first_name"));
				a.setLastname(result.getString("last_name"));
				a.setId(result.getInt("actor_id"));
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
	
	public void insertNewActor(Actor actor) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement statement=null;
		java.util.Date date = Calendar.getInstance().getTime();
		java.sql.Date now = new java.sql.Date(date.getTime());
		try {
			String query ="INSERT INTO actor (first_name,last_name,last_update) ";
			query+="VALUES (?,?,?) ";
			statement = conn.prepareStatement(query);
			statement.setString(1,actor.getFirstname());
			statement.setString(2,actor.getLastname());
			statement.setDate(3,now);
			int rows = statement.executeUpdate();
			if(rows==0) {
				System.out.println("ERROR IN insertNewActor()");
			}
		} catch (SQLException e) {
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
	}
	
	public void modifyActor(Actor actor) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement statement=null;
		java.util.Date date = Calendar.getInstance().getTime();
		java.sql.Date now = new java.sql.Date(date.getTime());
		try {
			String query ="UPDATE actor ";
			query+="SET first_name=?, last_name=?, last_update=? ";
			query+="WHERE actor_id=?";
			statement = conn.prepareStatement(query);
			statement.setString(1,actor.getFirstname());
			statement.setString(2,actor.getLastname());
			statement.setDate(3,now);
			statement.setInt(4, actor.getId());
			int rows = statement.executeUpdate();
			if(rows==0) {
				System.out.println("ERROR IN modifyActor()");
			}
		} catch (SQLException e) {
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
	}
	
	public Actor getActorById(int id) {
		Actor actor = new Actor();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement statement=null;
		try {
			String query = "SELECT first_name,last_name,actor_id FROM sakila.actor WHERE actor_id=?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				actor.setFirstname(result.getString("first_name"));
				actor.setLastname(result.getString("last_name"));
				actor.setId(result.getInt("actor_id"));
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
		return actor;
	}
}
