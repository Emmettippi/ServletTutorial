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
import it.objectmethod.esercizio.dao.ActorDao;

public class ActorsFilmServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		String strfilm = request.getParameter("filmname");
		ActorDao dao = (ActorDao) context.getBean("actorTempl");
		List<Actor> actorList = dao.getActorsByFilmName(strfilm);

		request.setAttribute("actors", actorList);
		request.setAttribute("film", strfilm);
		request.getRequestDispatcher("jsp/ActorsFilm.jsp").forward(request, response);
	}
}
