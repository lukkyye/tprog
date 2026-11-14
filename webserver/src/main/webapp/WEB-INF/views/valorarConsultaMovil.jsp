<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.soap.DtValoracionConsulta, java.util.Locale" %>
<%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter" %>

<%!
    private static String escaparHtml(String texto) {
        return texto.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
%>

<%
    String ctx = request.getContextPath();
    DtValoracionConsulta consulta =
            (DtValoracionConsulta) request.getAttribute("consulta");
    String nombre = escaparHtml(consulta.getNombreConsulta());
    String fecha = LocalDate.parse(consulta.getFechaConsulta())
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    Float promedio = consulta.getPromedioConsulta();
    Float puntaje = consulta.getValoracionPaciente();
    Boolean guardada = (Boolean) request.getAttribute("valGuardada");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy · Valorar consulta</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css">
</head>
<body class="bg-body-tertiary">
    <jsp:include page="/WEB-INF/templates/navbarMovil.jsp" />

    <main class="container py-3" style="max-width: 560px;">
        <a href="<%= ctx %>/movil/consultas-asistidas">Volver a mis consultas</a>

        <section class="card shadow-sm mt-3">
            <div class="card-body">
                <h1 class="h4">Valorar consulta</h1>
                <h2 class="h5"><%= nombre %></h2>
                <p class="text-body-secondary"><%= fecha %></p>

                <p>
                    <strong>Puntaje promedio</strong><br>
                    <%= promedio == null ? "Aún sin valoraciones"
                            : String.format(Locale.forLanguageTag("es-UY"), "%.1f de 5", promedio) %>
                </p>

                <% if (guardada != null) { %>
                    <p class="alert <%= guardada ? "alert-success" : "alert-info" %>" role="status">
                        <%= guardada ? "Se guardó tu valoración."
                                : "Ya habías valorado esta consulta." %>
                    </p>
                <% } %>

                <% if (puntaje != null) { %>
                    <p class="mb-0"><strong>Tu puntaje</strong> <%= puntaje.intValue() %> de 5</p>
                <% } else { %>
                    <form method="post" action="<%= ctx %>/movil/valorar-consulta">
                        <input type="hidden" name="consulta" value="<%= nombre %>">
                        <input type="hidden" name="token"
                               value="<%= escaparHtml((String) session.getAttribute("tokenValorarConsulta")) %>">

                        <fieldset class="mb-3">
                            <legend class="h6">Tu puntaje (1 a 5)</legend>
                            <% for (int i = 1; i <= 5; i++) { %>
                                <input class="btn-check" type="radio" name="puntaje"
                                       id="puntaje<%= i %>" value="<%= i %>" required>
                                <label class="btn btn-outline-primary mb-1" for="puntaje<%= i %>">
                                    <%= i %> ★
                                </label>
                            <% } %>
                        </fieldset>

                        <button type="submit" class="btn btn-primary w-100">
                            Guardar valoración
                        </button>
                    </form>
                <% } %>
            </div>
        </section>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>