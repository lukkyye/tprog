package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.models.datatypes.DTClinica;
import com.models.datatypes.DTResConsulta;

@WebServlet("/MisReservasServlet")
public class MisReservasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserControllerDelegator userController =
            new UserControllerDelegator();
    
    private ClinicControllerDelegator clinicController =
            new ClinicControllerDelegator();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	//Obtenemos nickname de paciente
        String nickname = (String) request.getSession().getAttribute("nickname");
        
        //Obtenemos nombre de consulta
        String nombreC = request.getParameter("consulta");
        HashSet<DTResConsulta> reservas = new HashSet<>();;
        
        if (nombreC == null || nombreC.isBlank()) {
        	reservas = userController.listarResConsultas(nickname);
        }else {
        	//debo quedarme con las reservas asociadas a la consulta 
        	reservas = clinicController.listarReservasConsulta(nombreC);
        }

        //Guardamos lista de reservas en reservas
        request.setAttribute("reservas", reservas);

        request.getRequestDispatcher("/WEB-INF/views/misReservas.jsp").forward(request, response);
    }
}