<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page import="com.models.datatypes.DTBeneficioAnual" %>
<%!
    private static String h(Object valor) {
        if (valor == null) {
            return "";
        }
        return valor.toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    String ctx = request.getContextPath();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    boolean esMedico = "Medico".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "Paciente".equalsIgnoreCase(tipoUsuario);
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture", new ImageHandlerDelegator().get_img64((String) session.getAttribute("nickname")));
    }
%>
<%
    DTBeneficioAnual beneficio = (DTBeneficioAnual) request.getAttribute("beneficio");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Detalle del Beneficio Anual</title>
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
            <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">

                <% if (beneficio != null) { %>
                    <h1 class="h2 fw-bold mb-4">Beneficio Anual <%= beneficio.anio() %></h1>

                    <div class="row g-3">
                        <div class="col-12 col-md-6">
                            <div class="border rounded-4 bg-white p-3 h-100">
                                <strong>Órdenes de consulta gratuitas</strong>
                                <p class="mb-1 mt-2">Total: <%= beneficio.cantOrdenes() %></p>
                                <p class="mb-1">Usadas: <%= beneficio.ordenesUsadas() %></p>
                                <p class="mb-0">
                                    Disponibles:
                                    <span class="badge <%= beneficio.ordenesDisponibles() > 0 ? "bg-success" : "bg-secondary" %>">
                                        <%= beneficio.ordenesDisponibles() %>
                                    </span>
                                </p>
                            </div>
                        </div>
                        <div class="col-12 col-md-6">
                            <div class="border rounded-4 bg-white p-3 h-100">
                                <strong>Tickets de medicamento gratuitos</strong>
                                <p class="mb-0 mt-2">Total: <%= beneficio.cantTicket() %></p>
                            </div>
                        </div>
                    </div>
                <% } else { %>
                    <div class="alert alert-warning mb-0" role="alert">
                        No se encontraron los datos del beneficio.
                    </div>
                <% } %>

                <div class="d-flex justify-content-end mt-4">
                    <a href="<%= ctx %>/ListaBeneficiosServlet" class="btn btn-secondary">Volver</a>
                </div>
            </section>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
