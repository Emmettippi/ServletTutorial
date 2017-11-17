package it.objectmethod.esercizio.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.objectmethod.esercizio.beans.Actor;

public class ActorMapper implements RowMapper<Actor> {
	public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Actor actor = new Actor();
		actor.setId(rs.getInt("actor_id"));		   	
		actor.setFirstname(rs.getString("first_name"));
		actor.setLastname(rs.getString("last_name"));
		return actor;
	}
}
