package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ClinicControllerDelegator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AltaDetalleReservaServlet")
public class AltaDetalleReservaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClinicControllerDelegator clinicController = new ClinicControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/altaDetalleReserva.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        String nomConsulta = request.getParameter("nomConsulta");
        String numResStr = request.getParameter("numRes");
        String asistioStr = request.getParameter("asistio");
        String motivo = request.getParameter("motivo");
        String diagnostico = request.getParameter("diagnostico");

        int numRes = 0;
        if (numResStr != null && !numResStr.isBlank()) {
            try {
                numRes = Integer.parseInt(numResStr);
            } catch (NumberFormatException e) {
                numRes = 0;
            }
        }

        boolean asistio = "true".equalsIgnoreCase(asistioStr);
        if (diagnostico == null) {
            diagnostico = "";
        }

        if (nomConsulta == null || nomConsulta.isBlank() || numRes <= 0 || motivo == null || motivo.isBlank()) {
            request.setAttribute("mensajeError", "Debe completar todos los campos obligatorios (*).");
            request.getRequestDispatcher("/WEB-INF/views/altaDetalleReserva.jsp").forward(request, response);
            return;
        }

        try {
            clinicController.altaDetalleReserva(nomConsulta, numRes, asistio, motivo, diagnostico);

            response.sendRedirect(request.getContextPath() + "/MisConsultasServlet");

        } catch (Exception e) {
            request.setAttribute("mensajeError", e.getMessage() != null ? e.getMessage() : "Ocurrió un error al registrar el detalle de la reserva.");
            request.getRequestDispatcher("/WEB-INF/views/altaDetalleReserva.jsp").forward(request, response);
        }
    }
}