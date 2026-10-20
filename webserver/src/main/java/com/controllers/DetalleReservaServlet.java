
package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.models.datatypes.DTResConsultaDetalle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DetalleReserva")
public class DetalleReservaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserControllerDelegator userController =
            new UserControllerDelegator();
    private ClinicControllerDelegator clinicController =
            new ClinicControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	String nickname = (String) request.getSession().getAttribute("nickname");
    	String paramReserva = request.getParameter("reserva");
    	String consulta = request.getParameter("consulta");
    	int numRes = 0;

    	if (paramReserva != null && !paramReserva.isEmpty()) {
    	    try {
    	        numRes = Integer.parseInt(paramReserva);
    	    } catch (NumberFormatException e) {
    	        numRes = 0; 
    	    }
    	}

        DTResConsultaDetalle reserva = clinicController.listarDatosResConsultaCC(consulta, numRes);

        if (reserva == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "No se encontro la reserva.");
            return;
        }

        request.setAttribute("reserva", reserva);

        request.getRequestDispatcher(
                "/WEB-INF/views/detalleReserva.jsp")
                .forward(request, response);
    }
}
