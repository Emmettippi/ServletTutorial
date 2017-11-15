package it.objectmethod.esercizio.servlet;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.dao.ActorDao;
import it.objectmethod.esercizio.dao.impl.ActorDaoImpl;

public class AllActorsServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ActorDao dao = new ActorDaoImpl();
		List<Actor> actorlist = dao.getAllActors();
		request.setAttribute("actors", actorlist);
		request.getRequestDispatcher("jsp/AllActorList.jsp").forward(request, response);
	}
}
