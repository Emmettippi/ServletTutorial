package it.objectmethod.esercizio.dao;

import java.util.List;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.beans.Film;

public interface FilmDao {
	public List<Film> getAllFilm();
	
	public List<Film> getFilmByActor(Actor actor);
	
	public void insertNewFilm(Film film);
	
	public void editFilm(Film film);
	
	public Film getFilmById(int id);
}
