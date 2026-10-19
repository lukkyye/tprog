package com.controllers;

import java.io.IOException;
import java.util.HashSet;

import com.gateway.connectors.UserControllerConnector;
import com.models.datatypes.DTResConsulta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AltaReservaServlet")
public class AltaReservaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	UserControllerConnector userController = new UserControllerConnector();
        
        String nickname = (String) request.getSession().getAttribute("nickname");
        String nomConsulta = request.getParameter("consulta");
        HashSet<DTResConsulta> reservas = userController.listarResConsultas(nickname);
        
        
        boolean yaTieneReserva = true;
        boolean cupoLleno = false;       // clinicController.alcanzoMaximoCupo(nomConsulta);

        if (yaTieneReserva) {
            // Guardar el mensaje de error y el número/id de la reserva existente para poder editarla
            request.setAttribute("errorReserva", "ALREADY_EXISTS");
            request.setAttribute("mensajeError", "Ya tienes una reserva registrada para esta consulta.");
            request.setAttribute("numReservaExistente", 102); // ID/Número de su reserva previa
            
            request.getRequestDispatcher("/WEB-INF/views/altaReserva.jsp").forward(request, response);
            return;
        }

        if (cupoLleno) {
            request.setAttribute("errorReserva", "CUPO_FULL");
            request.setAttribute("mensajeError", "Lo sentimos, se ha alcanzado el número máximo de cupos disponibles para esta consulta.");
            
            request.getRequestDispatcher("/WEB-INF/views/altaReserva.jsp").forward(request, response);
            return;
        }

        // 2. Si todo es correcto: Dar de alta la reserva (con bonificación del 20% si es mayor a 60 años)
        // int numOtorgado = userController.altaReserva(nickname, nomConsulta, ...);
        
        // Redirigir a la vista de éxito o misReservas
        response.sendRedirect(request.getContextPath() + "/MisReservasServlet");
    }
}