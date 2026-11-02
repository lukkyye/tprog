package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtResConsulta;
import com.soap.UserNotFoundException_Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


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
        Set<DtResConsulta> reservas = new HashSet<>();;
        
        if (nombreC == null || nombreC.isBlank()) {
        	try {
				reservas = userController.listarResConsultas(nickname);
			} catch (UserNotFoundException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else {
        	//debo quedarme con las reservas asociadas a la consulta 
        	try {
				reservas = clinicController.listarReservasConsulta(nombreC);
			} catch (ConsultaNotFoundException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        //Guardamos lista de reservas en reservas
        request.setAttribute("reservas", reservas);

        request.getRequestDispatcher("/WEB-INF/views/misReservas.jsp").forward(request, response);
    }
}