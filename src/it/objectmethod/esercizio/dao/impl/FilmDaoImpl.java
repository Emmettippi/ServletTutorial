package it.objectmethod.esercizio.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.beans.Film;
import it.objectmethod.esercizio.dao.ConnectionManager;
import it.objectmethod.esercizio.dao.FilmDao;

public class FilmDaoImpl implements FilmDao{
	@Override
	public List<Film> getAllFilm() {
		List<Film> ret = new ArrayList<Film>();
		Connection conn = ConnectionManager.getConnection();
		Statement statement=null;
		try {
			statement = conn.createStatement();
			String query = "SELECT film_id,title FROM sakila.film ORDER BY title";
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				Film film = new Film();
				film.setTitle(result.getString("title"));
				film.setId(result.getInt("film_id"));
				ret.add(film);
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
	public List<Film> getFilmByActor(Actor actor) {
		List<Film> ret = new ArrayList<Film>();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement statement=null;
		try {
			String query ="SELECT film.title,film.film_id ";
			query+="FROM actor JOIN film_actor JOIN film ON actor.actor_id=film_actor.actor_id AND film_actor.film_id=film.film_id ";
			query+="WHERE actor.actor_id=? ";
			query+="ORDER BY film.title";
			statement = conn.prepareStatement(query);
			statement.setInt(1, actor.getId());
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Film film = new Film();
				film.setTitle(result.getString("title"));
				film.setId(result.getInt("film_id"));
				ret.add(film);
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
	
	public void insertNewFilm(Film film) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement statement=null;
		java.util.Date date = Calendar.getInstance().getTime();
		java.sql.Date now = new java.sql.Date(date.getTime());
		try {
			String query ="INSERT INTO film (title,last_update) ";
			query+="VALUES (?,?) ";
			statement = conn.prepareStatement(query);
			statement.setString(1,film.getTitle());
			statement.setDate(2,now);
			int rows = statement.executeUpdate();
			if(rows==0) {
				System.out.println("ERROR IN insertNewFilm()");
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
	
	public void editFilm(Film film) {
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement statement=null;
		java.util.Date date = Calendar.getInstance().getTime();
		java.sql.Date now = new java.sql.Date(date.getTime());
		try {
			String query ="UPDATE film ";
			query+="SET title=?, last_update=? ";
			query+="WHERE film_id=?";
			statement = conn.prepareStatement(query);
			statement.setString(1,film.getTitle());
			statement.setDate(2,now);
			statement.setInt(3, film.getId());
			int rows = statement.executeUpdate();
			if(rows==0) {
				System.out.println("ERROR IN editFilm()");
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
	
	public Film getFilmById(int id) {
		Film film = new Film();
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement statement=null;
		try {
			String query = "SELECT film_id,title FROM sakila.title WHERE film_id=?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				film.setTitle(result.getString("title"));
				film.setId(result.getInt("film_id"));
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
		return film;
	}
}
