package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.soap.UserNotFoundException;
import com.soap.UserNotFoundException_Exception;

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
		    	user_controller.seleccionarPaciente(nickname).getNickname();
		    	usertype="Paciente";
		    } catch (UserNotFoundException_Exception e){
		    	usertype="Medico";
		    }
		    session.setAttribute("nickname", nickname);
		    session.setAttribute("tipoUsuario", usertype);
		    response.sendRedirect("/webserver/home");
		} else {
			request.setAttribute("error", "Usuario o contraseña inválidas");
			request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
		}
	}
}