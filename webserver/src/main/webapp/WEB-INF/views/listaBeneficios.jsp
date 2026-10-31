<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page import="java.util.List" %>
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
        request.setAttribute("profilePicture", new ImageHandlerDelegator().getImg64((String) session.getAttribute("nickname")));
    }
%>
<%
@SuppressWarnings("unchecked")
    List<DtBeneficioAnual> beneficios = (List<DtBeneficioAnual>) request.getAttribute("beneficios");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Mis Beneficios Anuales</title>
    <link rel="icon" type="image/png" href="<%=ctx%>/img/FAVICON.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="min-vh-100 container-fluid bg-body">

    <%
    if (esMedico || esPaciente) {
    %>
        <jsp:include page="/WEB-INF/templates/navbar.jsp" />
    <%
    } else {
    %>
        <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
    <%
    }
    %>

    <main class="container-fluid d-flex mt-5 gap-4">

        <%
        if (esMedico) {
        %>
            <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
        <%
        } else if (esPaciente) {
        %>
            <jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
        <%
        } else {
        %>
            <jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
        <%
        }
        %>

        <div class="col-md-9 pe-5 d-flex flex-column gap-4">
            <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">

                <h1 class="h2 fw-bold mb-4">Mis Beneficios Anuales</h1>

                <%
                if (beneficios != null && !beneficios.isEmpty()) {
                %>
                    <div class="table-responsive">
                        <table class="table table-hover align-middle mb-0">
                            <thead>
                                <tr>
                                    <th>Año</th>
                                    <th>Órdenes (usadas / total)</th>
                                    <th>Tickets de medicamento</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%
                            for (DtBeneficioAnual b : beneficios) {
                            %>
                                <tr>
                                    <td class="fw-bold"><%= b.anio() %></td>
                                    <td><%= b.ordenesUsadas() %> / <%= b.cantOrdenes() %></td>
                                    <td><%= b.cantTicket() %></td>
                                </tr>
                            <% } %>
                            </tbody>
                        </table>
                    </div>
                <% } else { %>
                    <div class="alert alert-info text-center mb-0" role="alert">
                        El paciente no tiene beneficios anuales.
                    </div>
                <% } %>

				<div class="d-flex justify-content-center mt-4">
                    <a href="/webserver/home" class="btn btn-secondary">Volver</a>
                </div>
            </section>
        </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
