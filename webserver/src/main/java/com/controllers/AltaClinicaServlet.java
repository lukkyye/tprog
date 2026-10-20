
package com.controllers;

import java.io.IOException;

import com.gateway.delegators.ClinicControllerDelegator;
import com.gateway.delegators.ImageHandlerDelegator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

@WebServlet("/AltaClinicaServlet")
@MultipartConfig
public class AltaClinicaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ClinicControllerDelegator clinicController =
            new ClinicControllerDelegator();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/altaClinica.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombreClinica");
        String descripcion = request.getParameter("descripcion");
        String direccion = request.getParameter("direccion");
        String costoTicketTexto = request.getParameter("costoTicket");
        String costoOrdenTexto = request.getParameter("costoOrden");
        String sitioWeb = request.getParameter("sitioWeb");

        Part archivoImagen = request.getPart("imagen");

        if (archivoImagen != null && archivoImagen.getSize() > 0) {
        	String tipoContenido = archivoImagen.getContentType();

        	if (tipoContenido == null || !tipoContenido.startsWith("image/")) {
        		request.setAttribute("mensaje",
        				"El archivo seleccionado no es una imagen.");
        		request.getRequestDispatcher("/WEB-INF/views/altaClinica.jsp")
        			.forward(request, response);
        		return;
        	}
        }


        try {
            if (nombre == null || nombre.isBlank()
                    || descripcion == null || descripcion.isBlank()
                    || direccion == null || direccion.isBlank()
                    || costoTicketTexto == null || costoTicketTexto.isBlank()
                    || costoOrdenTexto == null || costoOrdenTexto.isBlank()) {

                request.setAttribute("mensaje",
                        "Completa todos los campos obligatorios.");
                request.getRequestDispatcher(
                        "/WEB-INF/views/altaClinica.jsp")
                       .forward(request, response);
                return;
            }

            float costoTicket = Float.parseFloat(costoTicketTexto);
            float costoOrden = Float.parseFloat(costoOrdenTexto);

            if (!Float.isFinite(costoTicket) || !Float.isFinite(costoOrden)
                    || costoTicket < 0 || costoOrden < 0) {
                throw new NumberFormatException();
            }

            boolean ingresada = clinicController.ingresarClinica(
                    nombre.trim(),
                    descripcion.trim(),
                    direccion.trim(),
                    costoTicket,
                    costoOrden,
                    sitioWeb == null ? "" : sitioWeb.trim()	
            );
            
            ImageHandlerDelegator ihd = new ImageHandlerDelegator();
            ihd.addImg(archivoImagen, nombre);

            if (ingresada) {
                request.setAttribute("mensaje",
                        "La clinica se registro correctamente.");
            } else {
                request.setAttribute("mensaje",
                        "No se pudo registrar la clinica. "
                        + "Verifica que el nombre no este repetido.");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensaje",
                    "Los costos deben ser numeros validos y no negativos.");
        } catch (RuntimeException e) {
            log("Error al registrar clinica", e);
            request.setAttribute("mensaje",
                    "Ocurrio un error al registrar la clinica.");
        }

        request.getRequestDispatcher("/WEB-INF/views/altaClinica.jsp")
               .forward(request, response);
    }
}
