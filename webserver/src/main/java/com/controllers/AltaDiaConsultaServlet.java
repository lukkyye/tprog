package com.controllers;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Set;

import com.gateway.connectors.ImageHandlerConnector;
import com.gateway.delegators.ClinicControllerDelegator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.gateway.delegators.ImageHandlerDelegator;
import com.soap.DiaConsultaAlreadyExistsException_Exception;
import com.soap.DtDiaSemana;
import com.soap.SpecialityNotFoundException_Exception;

@WebServlet("/AltaDiaConsultaServlet")
@MultipartConfig
public class AltaDiaConsultaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ClinicControllerDelegator clinicController =
            new ClinicControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        cargarDatos(request);
        request.getRequestDispatcher(
                "/WEB-INF/views/altaDiaConsulta.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        DtDiaSemana diaSemana = DtDiaSemana.valueOf(request.getParameter("dia"));
        String horaTexto = request.getParameter("horaInicio");
        String nombreEspecialidad =
                request.getParameter("nombreEspecialidad");
        String nombreClinica = request.getParameter("nombreClinica");

        try {
            if (nombre == null || nombre.isBlank()
                    || descripcion == null || descripcion.isBlank()
                    || diaSemana == null
                    || horaTexto == null || horaTexto.isBlank()
                    || nombreEspecialidad == null
                    || nombreEspecialidad.isBlank()
                    || nombreClinica == null || nombreClinica.isBlank()) {

                request.setAttribute("mensaje",
                        "Completa todos los campos obligatorios.");
                cargarDatos(request);
                request.getRequestDispatcher(
                        "/WEB-INF/views/altaDiaConsulta.jsp")
                       .forward(request, response);
                return;
            }
            LocalTime horaInicio = LocalTime.parse(horaTexto);

            clinicController.ingresarDiaConsulta(
                    nombre.trim(),
                    descripcion.trim(),
                    diaSemana,
                    horaInicio,
                    nombreEspecialidad,
                    nombreClinica    
            );
            
           
    		ImageHandlerDelegator IMG = new ImageHandlerDelegator();
            Part archivoImagen = request.getPart("imagen");

    		IMG.addImg(archivoImagen, nombre);

            request.setAttribute("mensaje",
                    "El dia de consulta se registro correctamente.");

        } catch (IllegalArgumentException e) {
            request.setAttribute("mensaje",
                    "El dia o la hora ingresados no son validos.");
        } catch (RuntimeException e) {
            log("Error al registrar dia de consulta", e);
            request.setAttribute("mensaje",
                    "No se pudo registrar el dia de consulta: "
                    + e.getMessage());
        } catch (DiaConsultaAlreadyExistsException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SpecialityNotFoundException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        cargarDatos(request);
        request.getRequestDispatcher(
                "/WEB-INF/views/altaDiaConsulta.jsp")
               .forward(request, response);
    }

    private void cargarDatos(HttpServletRequest request) {
        Set<String> clinicas = clinicController.listarClinicas();
        Set<String> especialidades =
                clinicController.listarEspecialidades();

        request.setAttribute("clinicas", clinicas);
        request.setAttribute("especialidades", especialidades);
    }
}