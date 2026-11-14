<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.time.LocalDate, java.time.format.DateTimeFormatter, com.soap.DtMedicamento, com.soap.DtSolicitudMedicamento" %>
<%!
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static String h(Object valor) {
        if (valor == null) {
            return "";
        }
        return valor.toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    private static String fecha(String iso) {
        return LocalDate.parse(iso).format(FMT);
    }
%>
<%
    String ctx = request.getContextPath();
    @SuppressWarnings("unchecked")
    List<DtMedicamento> medicamentos = (List<DtMedicamento>) request.getAttribute("medicamentos");
    LocalDate hoy = LocalDate.parse((String) request.getAttribute("hoy"));
    String exito = (String) request.getAttribute("exito");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Mis medicamentos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="icon" type="image/png" href="<%= ctx %>/img/FAVICON.png">
</head>
<body class="bg-body">
    <jsp:include page="/WEB-INF/templates/navbarMovil.jsp" />
    <main class="container py-3" style="max-width: 560px;">
        <h1 class="h4 mb-3">Solicitar medicamento a domicilio</h1>

        <% if (exito != null) { %>
            <div class="alert alert-success" role="alert"><%= h(exito) %></div>
        <% } %>
        <% if (error != null) { %>
            <div class="alert alert-danger" role="alert"><%= h(error) %></div>
        <% } %>

        <% if (medicamentos == null || medicamentos.isEmpty()) { %>
            <div class="alert alert-info" role="alert">
                Todavía no tienes medicamentos recetados.
            </div>
        <% } else { %>
            <p class="text-muted">Estos son los medicamentos que te recetaron.</p>
            <% for (DtMedicamento m : medicamentos) {
                   LocalDate vencimiento = LocalDate.parse(m.getVencimiento());
                   boolean vencido = hoy.isAfter(vencimiento);
                   DtSolicitudMedicamento sol = m.getSolMed();
            %>
                <div class="card shadow-sm border-0 rounded-4 mb-3">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-start gap-2">
                            <h2 class="h5 mb-1"><%= h(m.getNombre()) %></h2>
                            <% if (m.isUsado()) { %>
                                <span class="badge text-bg-secondary">Solicitado</span>
                            <% } else if (vencido) { %>
                                <span class="badge text-bg-danger">Vencido</span>
                            <% } else { %>
                                <span class="badge text-bg-success">Disponible</span>
                            <% } %>
                        </div>
                        <p class="mb-2"><%= h(m.getPosologia()) %></p>
                        <p class="text-muted small mb-2">
                        	Consulta: <%= fecha(m.getNomC()) %>.
                            Recetado el <%= fecha(m.getIndicacion()) %>.
                            <% if (!m.isUsado()) { %>
                                Se puede solicitar hasta el <%= fecha(m.getVencimiento()) %>.
                            <% } %>
                        </p>

                        <% if (m.isUsado() && sol != null) { %>
                            <p class="small mb-0">
                                Entrega: <%= fecha(sol.getEntrega()) %> en <%= h(sol.getDireccion()) %><br>
                                Costo: <%= "$ " + sol.getCostoMed() + (sol.isUsaBenef() ? " (Beneficio Anual)" : "") %>
                            </p>
                        <% } else if (!vencido) { %>
                            <a class="btn btn-primary w-100"
                               href="<%= ctx %>/movil/medicamentos?nombre=<%= java.net.URLEncoder.encode(m.getNombre(), "UTF-8") %>&indicacion=<%= m.getIndicacion() %>">
                                Solicitar a domicilio
                            </a>
                        <% } %>
                    </div>
                </div>
            <% } %>
        <% } %>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
