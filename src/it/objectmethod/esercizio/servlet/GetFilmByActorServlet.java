package it.objectmethod.esercizio.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.beans.Film;
import it.objectmethod.esercizio.dao.ActorDao;
import it.objectmethod.esercizio.dao.FilmDao;
import it.objectmethod.esercizio.dao.impl.ActorDaoImpl;
import it.objectmethod.esercizio.dao.impl.FilmDaoImpl;

public class GetFilmByActorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Actor actor = new ActorDaoImpl().getActorById(Integer.parseInt(request.getParameter("id")));
		List<Film> filmList = new FilmDaoImpl().getFilmByActor(actor);
		int size=filmList.size();
		
		request.setAttribute("actor", actor);
		request.setAttribute("film", filmList);
		request.setAttribute("sizeList", size);
		request.getRequestDispatcher("jsp/FilmListPerActor.jsp").forward(request, response);
	}

}
