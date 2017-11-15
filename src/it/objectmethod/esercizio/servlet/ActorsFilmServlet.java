package it.objectmethod.esercizio.servlet;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.dao.ActorDao;
import it.objectmethod.esercizio.dao.impl.ActorDaoImpl;

public class ActorsFilmServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String strfilm = request.getParameter("filmname");
		ActorDao dao = new ActorDaoImpl();
		List<Actor> actorList = dao.getActorsByFilmName(strfilm);

		request.setAttribute("actors", actorList);
		request.setAttribute("film", strfilm);
		request.getRequestDispatcher("jsp/ActorsFilm.jsp").forward(request, response);
	}
}
