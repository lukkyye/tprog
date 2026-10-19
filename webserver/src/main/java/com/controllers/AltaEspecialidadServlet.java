package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/altaEspecialidad")
public class AltaEspecialidadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserControllerDelegator userController = new UserControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.getRequestDispatcher("/WEB-INF/views/altaEspecialidad.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String porcentajeStr = request.getParameter("porcentaje");

        float porcentaje = 0.0f;
        if (porcentajeStr != null && !porcentajeStr.isBlank()) {
            try {
                porcentaje = Float.parseFloat(porcentajeStr);
            } catch (NumberFormatException e) {
                porcentaje = 0.0f;
            }
        }

        try {
            userController.altaEspecialidad(nombre, (int) (porcentaje));

            response.sendRedirect(request.getContextPath() + "/home");

        } catch (Exception e) {
            request.setAttribute("errorEspecialidad", e.getMessage() != null ? e.getMessage() : "La especialidad ya existe.");
            request.setAttribute("nombreIngresado", nombre);
            request.setAttribute("porcentajeIngresado", porcentajeStr);

            request.getRequestDispatcher("/WEB-INF/views/altaEspecialidad.jsp").forward(request, response);
        }
    }
}