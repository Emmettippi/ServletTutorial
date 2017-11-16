package it.objectmethod.esercizio.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.objectmethod.esercizio.beans.Actor;
import it.objectmethod.esercizio.dao.ActorDao;

public class SetActorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		int id=Integer.parseInt(request.getParameter("id"));
		Actor actor=null;
		if(id!=0) {
			ActorDao dao = (ActorDao) context.getBean("actorTempl");
			actor=dao.getActorById(id);
		}
		else {
			actor = new Actor();
		}
		request.setAttribute("actor", actor);
		request.getRequestDispatcher("jsp/ModificationPage.jsp").forward(request, response);
	}
}