package com.controllers;

import java.io.IOException;
import com.gateway.delegators.UserControllerDelegator;
import com.models.excepciones.UserNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		UserControllerDelegator user_controller = new UserControllerDelegator();
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		if (nickname!=null && password!=null && user_controller.login(nickname, password)) {
		    HttpSession session = request.getSession();
		    String usertype="";
		    try {
		    	user_controller.seleccionarPaciente(nickname).nickname();
		    	usertype="Paciente";
		    } catch (UserNotFoundException e){
		    	usertype="Medico";
		    }
		    session.setAttribute("nickname", nickname);
		    session.setAttribute("userProfileImg", password);
		    session.setAttribute("usertype", usertype);
		    response.sendRedirect("/home");
		}
	}
}