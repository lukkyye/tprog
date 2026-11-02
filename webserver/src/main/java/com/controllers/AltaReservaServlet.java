package com.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.gateway.delegators.UserControllerDelegator;
import com.soap.BeneficioSinOrdenesException_Exception;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtConsulta;
import com.soap.DtResConsulta;
import com.soap.NoNumberAvailableException_Exception;
import com.soap.ReservaAlreadyExistsException_Exception;
import com.soap.UserNotFoundException_Exception;
import com.gateway.delegators.ClinicControllerDelegator;


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
        
        String nomConsulta = request.getParameter("nombreConsulta");
        String nickname = (String) request.getSession().getAttribute("nickname");
        DtConsulta consulta=null;
		try {
			consulta = clinicController.seleccionarConsulta(nomConsulta);
		} catch (ConsultaNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        float costoReserva = 0;
        
        try {
			if((2026 - LocalDate.parse(userController.listarDatosPaciente(nickname).getFechaNac()).getYear()) >= 60 ) {
				costoReserva = (float) (consulta.getCostoC() * 0.8);
			} else {
				costoReserva = consulta.getCostoC();
			}
		} catch (UserNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
        
        DtConsulta consulta = null;
		try {
			consulta = clinicController.seleccionarConsulta(nomConsulta);
		} catch (ConsultaNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Set<DtResConsulta> reservasUsuario = null;
		try {
			reservasUsuario = userController.listarResConsultas(nickname);
		} catch (UserNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        boolean yaTieneReserva = false;
        if (reservasUsuario != null) {
            for (DtResConsulta r : reservasUsuario) {
            	//ACA
            	try {
					if (nomConsulta != null && userController.listarDatosResConsulta(nomConsulta, nickname)!=null) {
						yaTieneReserva = true;
					    break;
					}
				} catch (ConsultaNotFoundException_Exception | UserNotFoundException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        
        float costoReserva = 0;
        try {
			if((2026 - LocalDate.parse(userController.listarDatosPaciente(nickname).getFechaNac()).getYear()) >= 60 ) {
				costoReserva = (float) (consulta.getCostoC() * 0.8f);
			}else {
				costoReserva = consulta.getCostoC();
			}
		} catch (UserNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Costo linea 70: " + String.valueOf(costoReserva));

        if (yaTieneReserva) {
            request.setAttribute("errorReserva", "ALREADY_EXISTS");
            request.setAttribute("mensajeError", "Ya tienes una reserva registrada para esta consulta.");
            request.setAttribute("numReservaExistente", 102);
            
            request.setAttribute("consulta", consulta);
            request.setAttribute("costoReserva", costoReserva);
          
            request.getRequestDispatcher("/WEB-INF/views/altaReserva.jsp").forward(request, response);
            return;
        }
        
        Set<DtResConsulta> reservasConsulta = null;
		try {
			reservasConsulta = clinicController.listarReservasConsulta(nomConsulta);
		} catch (ConsultaNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int cantReservas = (reservasConsulta != null) ? reservasConsulta.size() : 0;

        if (consulta != null && cantReservas >= consulta.getCantNum()) {
            request.setAttribute("errorReserva", "CUPO_FULL");
            request.setAttribute("mensajeError", "Lo sentimos, se ha alcanzado el número máximo de cupos disponibles para esta consulta.");
            
            request.setAttribute("consulta", consulta);
            request.setAttribute("costoReserva", costoReserva);
            
            request.getRequestDispatcher("/WEB-INF/views/altaReserva.jsp").forward(request, response);
            return;
        }

        String aplicaBenef = request.getParameter("aplicaBenef");
        boolean benef = "true".equalsIgnoreCase(aplicaBenef);

        try {
			clinicController.reservarConsulta(nickname, nomConsulta, LocalDate.now(), benef);
		} catch (BeneficioSinOrdenesException_Exception | ConsultaNotFoundException_Exception
				| NoNumberAvailableException_Exception | ReservaAlreadyExistsException_Exception
				| UserNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        response.sendRedirect(request.getContextPath() + "/MisReservasServlet");
    }
}