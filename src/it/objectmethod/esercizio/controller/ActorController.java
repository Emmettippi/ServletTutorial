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
public class ActorController {
	@Autowired
	private ActorDao actordao;
	@Autowired
	private FilmDao filmdao;
	
	@RequestMapping("/show_actor_list")
	public ModelAndView getAllActor() {
		List<Actor> actorlist = actordao.getAllActors();
		return new ModelAndView("AllActorList", "actors",actorlist);
	}
	
	@RequestMapping("/insert_film_name")
	public String showInsertFilmNamePage() {
		return "InsertFilmName";
	}
	
	@RequestMapping("/actors_by_film")
	public ModelAndView getActorsByFilm(@RequestParam("filmname") String film) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("film", film);
		map.put("actors", actordao.getActorsByFilmName(film));
		return new ModelAndView("ActorsFilm","ritorno",map);
	}
	
	@RequestMapping("/set_actors")
	public ModelAndView showSetActorPage(@RequestParam("id") int id) {
		Actor actor = new Actor();
		if(id!=0) {
			actor = actordao.getActorById(id);
		}
		return new ModelAndView("ModificationPage","actor",actor);
	}
	
	@RequestMapping("/modification_complete")
	public ModelAndView setActor(@RequestParam("id") int id,@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname) {
		Actor actor = new Actor();
		actor.setLastname(lastname);
		actor.setFirstname(firstname);
		actor.setId(id);
		if(id==0) {
			actordao.insertNewActor(actor);
		}
		else {
			actordao.modifyActor(actor);
		}
		return getAllActor();
	}
	
	@RequestMapping("/show_film_list_by_actor")
	public ModelAndView getActorsByFilm(@RequestParam("id") int id) {
		Map<String,Object> map = new HashMap<String,Object>();
		Actor actor=actordao.getActorById(id);
		map.put("actor", actor);
		List<Film> filmlist = filmdao.getFilmByActor(actor);
		map.put("film", filmlist);
		map.put("sizeList", filmlist.size());
		return new ModelAndView("FilmListPerActor","ritorno",map);
	}
	
	public ActorDao getActordao() {
		return actordao;
	}

	public void setActordao(ActorDao actordao) {
		this.actordao = actordao;
	}

	public FilmDao getFilmdao() {
		return filmdao;
	}

	public void setFilmdao(FilmDao filmdao) {
		this.filmdao = filmdao;
	}

}
