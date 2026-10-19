package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

import com.gateway.delegators.UserControllerDelegator;
import com.gateway.delegators.ClinicControllerDelegator;
import com.models.datatypes.DTConsulta;
import com.models.datatypes.DTResConsulta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AltaReservaServlet")
public class AltaReservaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        ClinicControllerDelegator clinicController = new ClinicControllerDelegator();
        UserControllerDelegator userController = new UserControllerDelegator();
        
        String nomConsulta = request.getParameter("consulta");
        String nickname = (String) request.getSession().getAttribute("nickname");
        DTConsulta consulta = clinicController.seleccionarConsulta(nomConsulta);
        float costoReserva;
        
        if((2026 - userController.listarDatosPaciente(nickname).fechaNac().getYear()) >= 60 ) {
        	costoReserva = (float) (consulta.costoC() * 0.8);
        }else {
        	costoReserva = consulta.costoC();
        }
        request.setAttribute("consulta", consulta);
        request.setAttribute("costoReserva", costoReserva);
        
        request.getRequestDispatcher("/WEB-INF/views/altaReserva.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	UserControllerDelegator userController = new UserControllerDelegator();
    	ClinicControllerDelegator clinicController = new ClinicControllerDelegator();
        
        String nickname = (String) request.getSession().getAttribute("nickname");
        String nomConsulta = request.getParameter("consulta");
        
        DTConsulta consulta = clinicController.seleccionarConsulta(nomConsulta);
        
        HashSet<DTResConsulta> reservasUsuario = userController.listarResConsultas(nickname);
        boolean yaTieneReserva = false;
        if (reservasUsuario != null) {
            for (DTResConsulta r : reservasUsuario) {
            	if (nomConsulta != null && nomConsulta.equals(userController.listarDatosResConsulta(nickname, r.numReserva()).nomC())) {
            		yaTieneReserva = true;
                    break;
            	}
            }
        }
        
        float costoReserva;
        if((2026 - userController.listarDatosPaciente(nickname).fechaNac().getYear()) >= 60 ) {
        	costoReserva = (float) (consulta.costoC() * 0.8);
        }else {
        	costoReserva = consulta.costoC();
        }

        if (yaTieneReserva) {
            request.setAttribute("errorReserva", "ALREADY_EXISTS");
            request.setAttribute("mensajeError", "Ya tienes una reserva registrada para esta consulta.");
            request.setAttribute("numReservaExistente", 102);
            
            request.setAttribute("consulta", consulta);
            request.setAttribute("costoReserva", costoReserva);
          
            request.getRequestDispatcher("/WEB-INF/views/altaReserva.jsp").forward(request, response);
            return;
        }
        
        HashSet<DTResConsulta> reservasConsulta = clinicController.listarReservasConsulta(nomConsulta);
        int cantReservas = (reservasConsulta != null) ? reservasConsulta.size() : 0;

        if (consulta != null && cantReservas >= consulta.cantNum()) {
            request.setAttribute("errorReserva", "CUPO_FULL");
            request.setAttribute("mensajeError", "Lo sentimos, se ha alcanzado el número máximo de cupos disponibles para esta consulta.");
            
            request.setAttribute("consulta", consulta);
            request.setAttribute("costoReserva", costoReserva);
            
            request.getRequestDispatcher("/WEB-INF/views/altaReserva.jsp").forward(request, response);
            return;
        }

        String aplicaBenef = request.getParameter("aplicaBenef");
        boolean benef = "true".equalsIgnoreCase(aplicaBenef);

        clinicController.reservarConsulta(nickname, nomConsulta, LocalDate.now(), benef);

        response.sendRedirect(request.getContextPath() + "/MisReservasServlet");
    }
}