package it.objectmethod.esercizio.dao.jdbc;

import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.dao.ActorDao;
import it.objectmethod.esercizio.mapper.ActorMapper;

public class ActorJdbcTemplate extends NamedParameterJdbcDaoSupport implements ActorDao{

	@Override
	public List<Actor> getAllActors() {
		String query = "SELECT first_name firstname,last_name lastname,actor_id id FROM sakila.actor ORDER BY first_name";
		BeanPropertyRowMapper<Actor> actorMapper = new BeanPropertyRowMapper<Actor>(Actor.class);
		List <Actor> ret = getJdbcTemplate().query(query, actorMapper);
		return ret;
	}

	@Override
	public List<Actor> getActorsByFilmName(String film) {
		String query ="SELECT actor.first_name,actor.last_name,actor.actor_id ";
		query+="FROM actor JOIN film_actor JOIN film ON actor.actor_id=film_actor.actor_id AND film_actor.film_id=film.film_id ";
		query+="WHERE film.title=? ";
		query+="ORDER BY actor.first_name, actor.last_name";
		List <Actor> ret = getJdbcTemplate().query(query, new Object[]{film}, new ActorMapper());
		return ret;
	}
	
	public void insertNewActor(Actor actor) {
		java.util.Date date = Calendar.getInstance().getTime();
		java.sql.Date now = new java.sql.Date(date.getTime());
		String query ="INSERT INTO actor (first_name,last_name,last_update) ";
		query+="VALUES (?,?,?) ";
		getJdbcTemplate().update( query, actor.getFirstname(), actor.getLastname(), now);
	}
	
	public void modifyActor(Actor actor) {
		java.util.Date date = Calendar.getInstance().getTime();
		java.sql.Date now = new java.sql.Date(date.getTime());
		String query ="UPDATE actor ";
		query+="SET first_name=?, last_name=?, last_update=? ";
		query+="WHERE actor_id=?";
		getJdbcTemplate().update( query, actor.getFirstname(), actor.getLastname(), now, actor.getId());
	}
	
	public Actor getActorById(int id) {
		String query = "SELECT first_name,last_name,actor_id FROM sakila.actor WHERE actor_id=?";
		Actor actor = getJdbcTemplate().queryForObject(query, new Object[]{id}, new ActorMapper());
		return actor;
	}
}
