<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.soap.DtValoracionConsulta, java.util.List" %>
<%@ page import="java.net.URLEncoder, java.nio.charset.StandardCharsets" %>
<%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter" %>

<%!
    private static String escaparHtml(String texto) {
        return texto.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
%>

<%
    String ctx = request.getContextPath();
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @SuppressWarnings("unchecked")
    List<DtValoracionConsulta> consultas =
            (List<DtValoracionConsulta>) request.getAttribute("consultas");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy · Consultas asistidas</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>
<body class="bg-body-tertiary">
    <jsp:include page="/WEB-INF/templates/navbarMovil.jsp" />

    <main class="container py-3" style="max-width: 560px;">
        <h1 class="h4 mb-3">Consultas a las que asististe</h1>

        <% if (consultas.isEmpty()) { %>
            <p>Aún no tenés consultas con asistencia registrada.</p>
        <% } else { %>
            <ul class="list-group">
                <% for (DtValoracionConsulta consulta : consultas) {
                    String nombre = consulta.getNombreConsulta();
                    String nombreCodificado = URLEncoder.encode(nombre, StandardCharsets.UTF_8);
                    String fecha = LocalDate.parse(consulta.getFechaConsulta()).format(formatoFecha);
                %>
                    <li class="list-group-item p-0">
                        <a class="d-block px-3 py-3 text-decoration-none"
                           href="<%= ctx %>/movil/valorar-consulta?consulta=<%= nombreCodificado %>">
                            <span class="fw-semibold"><%= escaparHtml(nombre) %></span>
                            <span class="d-block text-body-secondary">
                                <%= fecha %> · <%= (consulta.getValoracionPaciente() == null
                                	    || consulta.getValoracionPaciente() == 0)
                                        ? "Pendiente de valorar" : "Ya valorada" %>
                            </span>
                        </a>
                    </li>
                <% } %>
            </ul>
        <% } %>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>