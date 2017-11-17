package it.objectmethod.esercizio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.beans.Film;
import it.objectmethod.esercizio.dao.ActorDao;
import it.objectmethod.esercizio.dao.FilmDao;

@Controller
public class FilmController {
	
	@Autowired
	private ActorDao actordao;
	@Autowired
	private FilmDao filmdao;

	@RequestMapping("/show_film_list_by_actor")
	public ModelAndView getActorsByFilm(ModelMap map, @RequestParam("id") int id) {
		Actor actor=actordao.getActorById(id);
		map.put("actor", actor);
		List<Film> filmlist = filmdao.getFilmByActor(actor);
		map.put("films", filmlist);
		return new ModelAndView("FilmListPerActor");
	}
}
