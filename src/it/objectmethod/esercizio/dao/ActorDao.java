package it.objectmethod.esercizio.dao;

import java.util.List;

import it.objectmethod.esercizio.beans.Actor;

public interface ActorDao {
	
	public List<Actor> getAllActors();
	
	public List<Actor> getActorsByFilmName(String film);

}
