package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.models.datatypes.TipoMes;
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
		//UserControllerDelegator cu = new UserControllerDelegator();
		//cu.altaEspecialidad("Cardiología", 2);
		//ArrayList<String> a = new ArrayList<String>();
		//a.add("Cardiología");
		//cu.ingresarPaciente("mateo", "1234", "mateo", "perez", "asd@gmail.com", LocalDate.of(2003, 04, 1), LocalDate.of(2004, 04, 1), 54849456, new ArrayList<String>());
		//cu.ingresarMedico("lukkyye", "123456#","Lucas", "Icasetti", "lucas.icasetti@fing.edu.uy", "REG1001", TipoMes.Enero, 1, "", a);
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
		    session.setAttribute("tipoUsuario", usertype);
		    response.sendRedirect("/webserver/home");
		} else {
			request.setAttribute("error", "Usuario o contraseña inválidas");
			request.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(request, response);
		}
	}
}