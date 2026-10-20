
package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ClinicControllerDelegator;
import com.models.datatypes.DTClinica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DetalleClinicaServlet")
public class DetalleClinicaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClinicControllerDelegator clinicController =
            new ClinicControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");

        if (nombre == null || nombre.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Debe seleccionar una clinica.");
            return;
        }

        DTClinica clinica = clinicController.seleccionarClinica(nombre);

        if (clinica == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "No se encontro la clinica.");
            return;
        }

        request.setAttribute("clinica", clinica);

        request.getRequestDispatcher(
                "/WEB-INF/views/detalleClinica.jsp")
                .forward(request, response);
    }
}

