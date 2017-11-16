package it.objectmethod.esercizio.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.esercizio.beans.Film;

public class FilmMapper implements RowMapper<Film> {
	public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
		Film film = new Film();
		film.setId(rs.getInt("film_id"));		   	
		film.setTitle(rs.getString("title"));
		return film;
	}
}
