<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.soap.DtDiaConsulta" %>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>

<%
String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    DtDiaConsulta diaConsulta =
        (DtDiaConsulta) request.getAttribute("diaConsulta");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Detalle del dia de consulta</title>
	<link rel="icon" type="image/png"
          href="/webserver/img/FAVICON.png">
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet">
</head>

<body class="min-vh-100 container-fluid bg-body">

    <% if ("Medico".equals(tipoUsuario) || "Paciente".equals(tipoUsuario)) { %>
        <jsp:include page="/WEB-INF/templates/navbar.jsp" />
    <% } else { %>
        <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
    <% } %>

    <main class="container-fluid d-flex mt-5 gap-4">

        <% if ("Medico".equals(tipoUsuario)) { %>
            <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
        <% } else if ("Paciente".equals(tipoUsuario)) { %>
            <jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
        <% } else { %>
            <jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
        <% } %>

        <div class="col-md-9 pe-5 d-flex flex-column gap-4">

            <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">

                <h2 class="mb-4">Datos del dia de consulta</h2>

                <% if (diaConsulta != null) { %>
                <%
    ImageHandlerDelegator IMG = new ImageHandlerDelegator();
    String imagen = IMG.getImg64(diaConsulta.getNomD());
%>

                    <div class="row g-4">

                        <div class="col-12 col-md-4">
    <div class="border rounded-4 bg-white p-3 h-100
                d-flex align-items-center justify-content-center">

        <% if (imagen != null) { %>

            <img
                src="<%= imagen %>"
                alt="Imagen del dia de consulta"
                class="img-fluid rounded-3"
                style="max-height: 280px; object-fit: contain;">

        <% } else { %>

            <p class="text-secondary text-center mb-0">
                Imagen no disponible
            </p>

        <% } %>

    </div>
</div>

                        <div class="col-12 col-md-8">

                            <h4 class="mb-4">
                                Dia de Consulta:
                                <%= diaConsulta.getNomD() %>
                            </h4>

                            <div class="mb-3">
                                <strong>Nombre:</strong>
                                <p class="mb-0"><%= diaConsulta.getNomD() %></p>
                            </div>

                            <div class="mb-3">
                                <strong>Descripcion:</strong>
                                <p class="mb-0"><%= diaConsulta.getDescD() %></p>
                            </div>

                            <div class="mb-3">
                                <strong>Dia:</strong>
                                <p class="mb-0"><%= diaConsulta.getDia() %></p>
                            </div>

                            <div class="mb-3">
                                <strong>Hora de inicio:</strong>
                                <p class="mb-0"><%= diaConsulta.getHoraIni() %></p>
                            </div>

                            <div class="mb-3">
                                <strong>Costo:</strong>
                                <p class="mb-0">$ <%= diaConsulta.getCostoD() %></p>
                            </div>

                        </div>
                    </div>

                <% } else { %>

                    <div class="alert alert-warning" role="alert">
                        No se encontraron los datos del dia de consulta.
                    </div>

                <% } %>

                <div class="d-flex justify-content-end mt-4">
                    <button
                        type="button"
                        class="btn btn-secondary"
                        onclick="history.back()">
                        Volver
                    </button>
                </div>

            </section>

            <!-- Consultas asociadas: agregar cuando tengamos
                 disponible la lista desde el controlador o servlet. -->

        </div>
    </main>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
    </script>

</body>
</html>