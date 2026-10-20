<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>

<%
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");

    if (tipoUsuario == null || tipoUsuario.isBlank()) {
        tipoUsuario = "VISITANTE";
    }

    Set<String> clinicas =
        (Set<String>) request.getAttribute("clinicas");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Clinicas</title>

    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>

<body class="min-vh-100 container-fluid bg-body">

    <!-- NAVBAR -->
    <%
        if ("MEDICO".equalsIgnoreCase(tipoUsuario)
                || "PACIENTE".equalsIgnoreCase(tipoUsuario)) {
    %>
        <jsp:include page="/WEB-INF/templates/navbar.jsp" />
    <%
        } else {
    %>
        <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
    <%
        }
    %>

    <!-- CONTENIDO PRINCIPAL -->
    <main class="container-fluid d-flex mt-5 gap-4">

        <!-- MENU LATERAL -->
        <%
            if ("MEDICO".equalsIgnoreCase(tipoUsuario)) {
        %>
            <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
        <%
            } else if ("PACIENTE".equalsIgnoreCase(tipoUsuario)) {
        %>
            <jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
        <%
            } else {
        %>
            <jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
        <%
            }
        %>

        <!-- CONTENIDO DE LA PAGINA -->
        <div class="col-md-9 pe-5 d-flex flex-column gap-4">

            <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">

                <h2 class="mb-4">Clinicas</h2>

                <p class="text-muted">
                    Consulta las clinicas disponibles.
                </p>

                <% if (clinicas != null && !clinicas.isEmpty()) { %>

                    <div class="list-group">

                        <% for (String nombreClinica : clinicas) { %>

                            <div class="list-group-item d-flex
                                        justify-content-between
                                        align-items-center">

                                <span>
                                    <%= nombreClinica %>
                                </span>

                                <a class="btn btn-outline-primary btn-sm"
                                   href="<%= request.getContextPath() %>/DetalleClinicaServlet?nombre=<%= java.net.URLEncoder.encode(nombreClinica, "UTF-8") %>">
                                    Ver
                                </a>

                            </div>

                        <% } %>

                    </div>

                <% } else { %>

                    <div class="alert alert-info mb-0">
                        No hay clinicas disponibles para mostrar.
                    </div>

                <% } %>

            </section>

        </div>

    </main>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
    </script>

</body></html>
