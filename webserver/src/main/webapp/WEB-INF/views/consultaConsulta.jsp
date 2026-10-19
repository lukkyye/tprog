<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.models.datatypes.DTConsultaDetalle" %>
<%@ page import="com.models.datatypes.DTResConsulta" %>
<%@ page import="com.models.datatypes.EstadoConsulta" %>
<%!
    // Escapa texto para mostrarlo en HTML (tambien dentro de atributos entre comillas).
    private static String h(Object valor) {
        if (valor == null) {
            return "";
        }
        return valor.toString().replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    String ctx = request.getContextPath();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    boolean esMedico = "Medico".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "Paciente".equalsIgnoreCase(tipoUsuario);
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture",
            new ImageHandlerDelegator().get_img64((String) session.getAttribute("nickname")));
    }
%>
<%
    DTConsultaDetalle consulta = (DTConsultaDetalle) request.getAttribute("consulta");
    String imagen = (String) request.getAttribute("imagen");
    boolean esMedicoDeLaConsulta =
        Boolean.TRUE.equals(request.getAttribute("esMedicoDeLaConsulta"));
    @SuppressWarnings("unchecked")
    List<DTResConsulta> reservas = (List<DTResConsulta>) request.getAttribute("reservas");
    DTResConsulta miReserva = (DTResConsulta) request.getAttribute("miReserva");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Detalle de la consulta</title>
    <link rel="icon" type="image/png" href="<%= ctx %>/img/FAVICON.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="min-vh-100 container-fluid bg-body">

    <% if (esMedico || esPaciente) { %>
        <jsp:include page="/WEB-INF/templates/navbar.jsp" />
    <% } else { %>
        <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
    <% } %>

    <main class="container-fluid d-flex mt-5 gap-4">

        <% if (esMedico) { %>
            <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
        <% } else if (esPaciente) { %>
            <jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
        <% } else { %>
            <jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
        <% } %>

        <div class="col-md-9 pe-5 d-flex flex-column gap-4">
        <% if (consulta == null) { %>
            <div class="alert alert-warning" role="alert">
                No se encontraron los datos de la consulta.
            </div>
        <% } else {
               String nomCodificado = java.net.URLEncoder.encode(consulta.nomC(), java.nio.charset.StandardCharsets.UTF_8); %>

            <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">
                <h2 class="mb-4">Datos de la consulta</h2>
                <div class="row g-4">
                    <div class="col-12 col-md-4">
                        <div class="border rounded-4 bg-white p-3 h-100
                                    d-flex align-items-center justify-content-center">
                            <% if (imagen != null && !imagen.isEmpty()) { %>
                                <img src="<%= imagen %>" alt="Imagen de la consulta"
                                     class="img-fluid rounded-3"
                                     style="max-height: 280px; object-fit: contain;">
                            <% } else { %>
                                <p class="text-secondary text-center mb-0">Imagen no disponible</p>
                            <% } %>
                        </div>
                    </div>
                    <div class="col-12 col-md-8">
                        <h4 class="mb-3"><%= h(consulta.nomC()) %>
                            <% if (esMedicoDeLaConsulta) {
                                   EstadoConsulta e = consulta.estado(); %>
                                <span class="badge fs-6 align-middle
                                    <%= e == EstadoConsulta.Aceptada ? "bg-success"
                                      : e == EstadoConsulta.Rechazada ? "bg-danger"
                                      : "bg-warning text-dark" %>">
                                    <%= e %>
                                </span>
                            <% } %>
                        </h4>
                        <div class="mb-2"><strong>Médico:</strong> <%= h(consulta.medico()) %></div>
                        <div class="mb-2"><strong>Fecha:</strong> <%= consulta.fechaC() %></div>
                        <div class="mb-2"><strong>Sala:</strong> <%= consulta.sala() %></div>
                        <div class="mb-2"><strong>Cantidad de números:</strong> <%= consulta.cantNum() %></div>
                        <div class="mb-0"><strong>Costo:</strong> $<%= String.format("%.2f", consulta.costoC()) %></div>
                    </div>
                </div>
            </section>

            <% if (esMedicoDeLaConsulta) { %>
            <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">
                <h3 class="mb-3">Reservas de la consulta</h3>
                <% if (reservas != null && !reservas.isEmpty()) { %>
                    <div class="table-responsive">
                        <table class="table table-hover align-middle mb-0">
                            <thead>
                                <tr>
        							<th>Nº</th>
        							<th>Fecha de reserva</th>
        							<th>Costo</th>
        							<th>Beneficio</th>
        							<th></th>
    							</tr>
                            </thead>
                            <tbody>
                            <% for (DTResConsulta r : reservas) { %>
                                <tr>
                                    <td class="fw-bold"><%= r.numReserva() %></td>
                                    <td><%= r.fechaRes() %></td>
                                    <td>$<%= String.format("%.2f", r.costoR()) %></td>
                                    <td><%= r.aplicaBenef() ? "Sí" : "No" %></td>
                                    <td class="text-end">
                                        <a class="btn btn-sm btn-primary"
                                           href="<%= ctx %>/DetalleReservaServlet?consulta=<%= nomCodificado %>&num=<%= r.numReserva() %>">
                                            Ver reserva
                                        </a>
                                    </td>
                                </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                <% } else { %>
                    <p class="text-muted mb-0">Esta consulta todavía no tiene reservas.</p>
                <% } %>
            </section>
            <% } %>

            <% if (miReserva != null) { %>
            <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">
                <h3 class="mb-3">Mi reserva</h3>
                <div class="mb-2"><strong>Número:</strong> <%= miReserva.numReserva() %></div>
                <div class="mb-2"><strong>Fecha de reserva:</strong> <%= miReserva.fechaRes() %></div>
                <div class="mb-2"><strong>Costo:</strong> $<%= String.format("%.2f", miReserva.costoR()) %></div>
                <div class="mb-3"><strong>Beneficio anual:</strong> <%= miReserva.aplicaBenef() ? "Sí" : "No" %></div>
                <a class="btn btn-primary" href="<%= ctx %>/DetalleReserva?consulta=<%= nomCodificado %>&num=<%= miReserva.numReserva() %>">
                    Ver detalle de mi reserva
                </a>
            </section>
            <% } %>

        <% } %>

            <div class="mb-4">
                <button type="button" class="btn btn-secondary" onclick="history.back()">Volver</button>
            </div>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
