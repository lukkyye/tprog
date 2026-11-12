	
package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtResAsistio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DetalleReservaAsistioServlet")
public class DetalleReservaAsistioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserControllerDelegator userController =
            new UserControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	String nickname = (String) request.getSession().getAttribute("nickname");
    	String consulta = request.getParameter("nomConsulta");
    	int numRes = 0;

    	try {    		
    		numRes = Integer.valueOf(request.getParameter("numRes"));
    	} catch (NumberFormatException e) {
    		e.printStackTrace();
    	}
    	
        DtResAsistio reserva = null;
        
		try {
			reserva = userController.getAltaDetalleReserva(consulta, numRes);
		} catch (ConsultaNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (reserva == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "No se encontro la reserva.");
            return;
        }

        request.setAttribute("reserva", reserva);

        request.getRequestDispatcher(
                "/WEB-INF/views/detalleReservaAsistio.jsp")
                .forward(request, response);
    }
}
