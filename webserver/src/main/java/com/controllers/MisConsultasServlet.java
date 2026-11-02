package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.gateway.delegators.UserControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtConsulta;
import com.soap.UserNotFoundException_Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/MisConsultasServlet")
public class MisConsultasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserControllerDelegator userController =
            new UserControllerDelegator();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	//Obtenemos nickname de medico
        String nickname = (String) request.getSession().getAttribute("nickname");
        
        Set<DtConsulta> consultas = null;
		try {
			consultas = userController.listarConsultasAsociadas(nickname);
		} catch (ConsultaNotFoundException_Exception | UserNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //Guardamos lista de consultas
        request.setAttribute("consultas", consultas);

        request.getRequestDispatcher("/WEB-INF/views/misConsultas.jsp").forward(request, response);
    }
}