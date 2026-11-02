package com.controllers;

import java.io.IOException;

import com.gateway.delegators.UserControllerDelegator;
import com.soap.BeneficioAlreadyExistsException_Exception;
import com.soap.DtPaciente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.soap.UserNotFoundException_Exception;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AltaBeneficioAnualServlet")
public class AltaBeneficioAnualServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VISTA = "/WEB-INF/views/altaBeneficioAnual.jsp";

    private final UserControllerDelegator userController = new UserControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!esMedico(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setAttribute("anioVal", String.valueOf(LocalDate.now().getYear()));
        if ("1".equals(request.getParameter("ok"))) {
            request.setAttribute("exito", "Beneficio anual dado de alta correctamente.");
        }
        cargarPacientes(request);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!esMedico(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setCharacterEncoding("UTF-8");

        String paciente = request.getParameter("paciente");
        String anioTexto = request.getParameter("anio");
        String ordenesTexto = request.getParameter("cantOrdenes");
        String ticketsTexto = request.getParameter("cantTicket");

        request.setAttribute("pacienteSel", paciente);
        request.setAttribute("anioVal", anioTexto);
        request.setAttribute("ordenesVal", ordenesTexto);
        request.setAttribute("ticketsVal", ticketsTexto);

        try {
            if (vacio(paciente) || vacio(anioTexto) || vacio(ordenesTexto) || vacio(ticketsTexto)) {
                throw new IllegalArgumentException("Debe completar todos los campos.");
            }

            int anio = Integer.parseInt(anioTexto.trim());
            int cantOrdenes = Integer.parseInt(ordenesTexto.trim());
            int cantTicket = Integer.parseInt(ticketsTexto.trim());

            if (anio < 1 || cantOrdenes < 0 || cantTicket < 0) {
                throw new IllegalArgumentException("El año debe ser positivo y las cantidades no pueden ser negativas.");
            }

            userController.altaBeneficioAnual(paciente, anio, cantOrdenes, cantTicket);

            response.sendRedirect(request.getContextPath() + "/AltaBeneficioAnualServlet?ok=1");
            return;

        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "El año y las cantidades deben ser números enteros.");
        } catch (BeneficioAlreadyExistsException_Exception e) {
            request.setAttribute("mensaje", e.getMessage() + ". Modifica el año o los datos, o cancela.");
        } catch (UserNotFoundException_Exception | IllegalArgumentException e) {
            request.setAttribute("mensaje", e.getMessage());
        }

        cargarPacientes(request);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }

    private void cargarPacientes(HttpServletRequest request) {
        List<DtPaciente> pacientes = new ArrayList<>(userController.listarPacientes());
        pacientes.sort(Comparator.comparing(dtp->dtp.getNickname()));
        request.setAttribute("pacientes", pacientes);
    }

    private static boolean esMedico(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        return sesion != null && sesion.getAttribute("nickname") != null && "Medico".equalsIgnoreCase((String) sesion.getAttribute("tipoUsuario"));
    }

    private static boolean vacio(String texto) {
        return texto == null || texto.isBlank();
    }
}
