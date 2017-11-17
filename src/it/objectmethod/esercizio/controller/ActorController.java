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
import it.objectmethod.esercizio.dao.ActorDao;

@Controller
public class ActorController {
	@Autowired
	private ActorDao actordao;
	
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
	
	public ActorDao getActordao() {
		return actordao;
	}

	public void setActordao(ActorDao actordao) {
		this.actordao = actordao;
	}
}
