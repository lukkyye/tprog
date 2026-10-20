package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.gateway.delegators.UserControllerDelegator;
import com.models.datatypes.DTBeneficioAnual;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ListaBeneficiosServlet")
public class ListaBeneficiosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserControllerDelegator userController =
            new UserControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        String nickname = sesion == null ? null : (String) sesion.getAttribute("nickname");
        if (nickname == null || !"Paciente".equalsIgnoreCase((String) sesion.getAttribute("tipoUsuario"))) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<DTBeneficioAnual> beneficios = new ArrayList<>(userController.listarBeneficiosAnuales(nickname));
        beneficios.sort(Comparator.comparingInt(DTBeneficioAnual::anio));

        request.setAttribute("beneficios", beneficios);
        request.getRequestDispatcher("/WEB-INF/views/listaBeneficios.jsp").forward(request, response);
    }
}
