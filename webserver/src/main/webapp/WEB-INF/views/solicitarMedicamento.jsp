<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate, java.time.format.DateTimeFormatter, com.soap.DtMedicamento" %>
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
    DtMedicamento medicamento = (DtMedicamento) request.getAttribute("medicamento");
    String minEntrega = (String) request.getAttribute("minEntrega");
    String error = (String) request.getAttribute("error");
    String entrega = (String) request.getAttribute("entregaIngresada");
    String direccion = (String) request.getAttribute("direccionIngresada");
    String pago = (String) request.getAttribute("pagoIngresado");
    if (pago == null) {
        pago = "general";
    }
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Solicitar medicamento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="icon" type="image/png" href="<%= ctx %>/img/FAVICON.png">
</head>
<body class="bg-body">
    <jsp:include page="/WEB-INF/templates/navbarMovil.jsp" />
    <main class="container py-3" style="max-width: 560px;">
        <h1 class="h4 mb-3">Solicitar a domicilio</h1>

        <div class="card shadow-sm border-0 rounded-4 mb-3">
            <div class="card-body">
                <h2 class="h5 mb-1"><%= h(medicamento.getNombre()) %></h2>
                <p class="mb-2"><%= h(medicamento.getPosologia()) %></p>
                <p class="text-muted small mb-0">
                    Recetado el <%= LocalDate.parse(medicamento.getIndicacion()).format(fmt) %>.
                    Se puede solicitar hasta el <%= LocalDate.parse(medicamento.getVencimiento()).format(fmt) %>.
                </p>
            </div>
        </div>

        <% if (error != null) { %>
            <div class="alert alert-danger" role="alert"><%= h(error) %></div>
        <% } %>

        <form method="post" action="<%= ctx %>/movil/medicamentos">
            <input type="hidden" name="nombre" value="<%= h(medicamento.getNombre()) %>">
            <input type="hidden" name="indicacion" value="<%= h(medicamento.getIndicacion()) %>">

            <div class="mb-3">
                <label for="entrega" class="form-label">Fecha de entrega</label>
                <input type="date" class="form-control form-control-lg" id="entrega" name="entrega" min="<%= h(minEntrega) %>" required value="<%= h(entrega) %>">
            </div>
            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección de entrega</label>
                <input type="text" class="form-control form-control-lg" id="direccion" name="direccion"  maxlength="200" required autocomplete="street-address" value="<%= h(direccion) %>">
            </div>

            <fieldset class="mb-4">
                <legend class="form-label fs-6">Forma de pago</legend>
                <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" name="pago" id="pagoGeneral" value="general" <%= "general".equals(pago) ? "checked" : "" %>>
                    <label class="form-check-label" for="pagoGeneral">
                        Pago general ($ <%= medicamento.getCostoMed() %>)
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="pago" id="pagoBeneficio" value="beneficio" <%= "beneficio".equals(pago) ? "checked" : "" %>>
                    <label class="form-check-label" for="pagoBeneficio">
                        Beneficio anual ($0.0)
                    </label>
                </div>
            </fieldset>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary btn-lg">Confirmar solicitud</button>
                <a class="btn btn-outline-secondary" href="<%= ctx %>/movil/medicamentos">Cancelar</a>
            </div>
        </form>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
