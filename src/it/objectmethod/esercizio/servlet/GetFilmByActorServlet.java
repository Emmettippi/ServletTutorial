package it.objectmethod.esercizio.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.beans.Film;
import it.objectmethod.esercizio.dao.ActorDao;
import it.objectmethod.esercizio.dao.FilmDao;

public class GetFilmByActorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ActorDao dao = (ActorDao) context.getBean("actorTempl");
		Actor actor = dao.getActorById(Integer.parseInt(request.getParameter("id")));
		FilmDao fdao = (FilmDao) context.getBean("filmTempl");
		List<Film> filmList = fdao.getFilmByActor(actor);
		int size=filmList.size();
		
		request.setAttribute("actor", actor);
		request.setAttribute("film", filmList);
		request.setAttribute("sizeList", size);
		request.getRequestDispatcher("jsp/FilmListPerActor.jsp").forward(request, response);
	}

}
