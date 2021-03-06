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

public class ConfirmActorModificationServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		int id=Integer.parseInt(request.getParameter("id"));
		String newfirstname=request.getParameter("firstname");
		String newlastname=request.getParameter("lastname");
		Actor actor=new Actor();
		actor.setLastname(newlastname);
		actor.setFirstname(newfirstname);
		actor.setId(id);
		ActorDao dao = (ActorDao) context.getBean("actorTempl");
		if(id==0) {
			dao.insertNewActor(actor);
		}
		else {
			dao.modifyActor(actor);
		}
		request.getRequestDispatcher("show_actor_list").forward(request, response);
	}

}
