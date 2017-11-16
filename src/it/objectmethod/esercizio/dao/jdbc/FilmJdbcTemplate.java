package it.objectmethod.esercizio.dao.jdbc;

import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.beans.Film;
import it.objectmethod.esercizio.dao.FilmDao;
import it.objectmethod.esercizio.mapper.FilmMapper;

public class FilmJdbcTemplate extends NamedParameterJdbcDaoSupport implements FilmDao{

		@Override
		public List<Film> getAllFilm() {
			String query = "SELECT film_id,title FROM sakila.film ORDER BY title";
			List <Film> ret = getJdbcTemplate().query(query, new FilmMapper());
			return ret;
		}

		@Override
		public List<Film> getFilmByActor(Actor actor) {
			String query ="SELECT film.title,film.film_id ";
			query+="FROM actor JOIN film_actor JOIN film ON actor.actor_id=film_actor.actor_id AND film_actor.film_id=film.film_id ";
			query+="WHERE actor.actor_id=? ";
			query+="ORDER BY film.title";
			List <Film> ret = getJdbcTemplate().query(query, new Object[]{actor.getId()}, new FilmMapper());
			return ret;
		}
		
		public void insertNewFilm(Film film) {
			java.util.Date date = Calendar.getInstance().getTime();
			java.sql.Date now = new java.sql.Date(date.getTime());
			String query ="INSERT INTO film (title,last_update) ";
			query+="VALUES (?,?) ";
			getJdbcTemplate().update( query, film.getTitle(), now);
		}
		
		public void editFilm(Film film) {
			java.util.Date date = Calendar.getInstance().getTime();
			java.sql.Date now = new java.sql.Date(date.getTime());
			String query ="UPDATE film ";
			query+="SET title=?, last_update=? ";
			query+="WHERE film_id=?";
			getJdbcTemplate().update( query, film.getTitle(), now, film.getId());
		}
		
		public Film getFilmById(int id) {
			String query = "SELECT film_id,title FROM sakila.title WHERE film_id=?";
			Film film = getJdbcTemplate().queryForObject(query, new Object[]{id}, new FilmMapper());
			return film;
		}

}
