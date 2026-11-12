package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.UserControllerDelegator;
import com.soap.ConsultaNotFoundException_Exception;
import com.soap.DtResConsultaDetalle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AltaDetalleReservaServlet")
public class AltaDetalleReservaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClinicControllerDelegator clinicController = new ClinicControllerDelegator();
    private UserControllerDelegator userController = new UserControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	String nomConsulta = request.getParameter("consulta");
        String numResStr = request.getParameter("numRes");

        int numRes = 0;
        if (numResStr != null && !numResStr.isBlank()) {
            try {
                numRes = Integer.parseInt(numResStr);
            } catch (NumberFormatException e) {
                numRes = 0;
            }
        }
        DtResConsultaDetalle reserva = null;
        boolean yaExisteDetalle = false;
        if (nomConsulta != null && !nomConsulta.isBlank() && numRes > 0) {
            try {
                reserva = clinicController.listarDatosResConsultaCC(nomConsulta, numRes);
                if (userController.getAltaDetalleReserva(nomConsulta, numRes).getMotivo() != "" || !userController.getAltaDetalleReserva(nomConsulta, numRes).getMotivo().isBlank()) {
                    yaExisteDetalle = true;
                }
            } catch (ConsultaNotFoundException_Exception e) {
                e.printStackTrace();
                yaExisteDetalle = false;
            }
        }

        request.setAttribute("reserva", reserva);
        request.setAttribute("yaExisteDetalle", yaExisteDetalle);

        request.getRequestDispatcher("/WEB-INF/views/altaDetalleReserva.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	request.setCharacterEncoding("UTF-8");

        String nomConsulta = request.getParameter("consulta");
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
        if (!asistio) {
            motivo = "No asistio a la consulta.";
            diagnostico = "No dispone de un diagnostico";
        } else if (diagnostico == null || diagnostico.isBlank()) {
            diagnostico = "No dispone de un diagnostico";
        }

        // Reenviamos los datos por si hay que volver a mostrar el formulario tras un error
        request.setAttribute("consulta", nomConsulta);
        request.setAttribute("numRes", numRes);

        DtResConsultaDetalle reserva = null;
        try {
            reserva = clinicController.listarDatosResConsultaCC(nomConsulta, numRes);
            request.setAttribute("reserva", reserva);
        } catch (ConsultaNotFoundException_Exception e) {
            e.printStackTrace();
        }

        if (nomConsulta == null || nomConsulta.isBlank() || numRes <= 0 || (asistio && (motivo == null || motivo.isBlank()))) {
            request.setAttribute("mensajeError", "Debe completar todos los campos obligatorios (*).");
            request.getRequestDispatcher("/WEB-INF/views/altaDetalleReserva.jsp").forward(request, response);
            return;
        }
        
        if (reserva == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontro la reserva.");
            return;
        }

        try {
        	userController.altaDetalleReserva(reserva.getNomC(), reserva.getNumReserva(), reserva.getNicknamePaciente(), asistio, motivo, diagnostico);
        	String consultaCodificada = java.net.URLEncoder.encode(reserva.getNomC(), java.nio.charset.StandardCharsets.UTF_8);
            
            response.sendRedirect(request.getContextPath() 
                + "/DetalleReservaAsistioServlet?nomConsulta=" + consultaCodificada 
                + "&numRes=" + reserva.getNumReserva());
        } catch (Exception e) {
            request.setAttribute("mensajeError", e.getMessage() != null ? e.getMessage() : "Ocurrió un error al registrar el detalle de la reserva.");
            request.getRequestDispatcher("/WEB-INF/views/altaDetalleReserva.jsp").forward(request, response);
        }
    
    }
}