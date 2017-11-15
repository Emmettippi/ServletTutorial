package it.objectmethod.esercizio.dao;

import java.util.List;

import it.objectmethod.esercizio.beans.Actor;

public interface ActorDao {
	
	public List<Actor> getAllActors();
	
	public List<Actor> getActorsByFilmName(String film);
	
	public void insertNewActor(Actor actor);
	
	public void modifyActor(Actor actor);
	
	public Actor getActorById(int id);
}
