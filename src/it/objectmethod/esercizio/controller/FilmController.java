package it.objectmethod.esercizio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	private ActorDao actordaoinfilm;
	@Autowired
	private FilmDao filmdao;

	@RequestMapping("/show_film_list_by_actor")
	public ModelAndView getActorsByFilm(@RequestParam("id") int id) {
		Map<String,Object> map = new HashMap<String,Object>();
		Actor actor=actordaoinfilm.getActorById(id);
		map.put("actor", actor);
		List<Film> filmlist = filmdao.getFilmByActor(actor);
		map.put("film", filmlist);
		map.put("sizeList", filmlist.size());
		return new ModelAndView("FilmListPerActor","ritorno",map);
	}
}
