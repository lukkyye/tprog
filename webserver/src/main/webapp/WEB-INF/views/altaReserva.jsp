<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page import="com.models.datatypes.DTConsulta" %>
<%
String ctx = request.getContextPath();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    boolean esMedico = "Medico".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "Paciente".equalsIgnoreCase(tipoUsuario);
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture", new ImageHandlerDelegator().getImg64((String) session.getAttribute("nickname")));
    }

    // Obtener los atributos enviados desde el Servlet
    DtConsulta consulta = (DtConsulta) request.getAttribute("consulta");
    Float costoReserva = (Float) request.getAttribute("costoReserva");
    if (costoReserva == null) {
        costoReserva = 0.0f;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Alta de Reserva</title>
    <link rel="icon" type="image/png" href="<%= ctx %>/img/FAVICON.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .detail-label {
            font-size: 0.9rem;
            color: #6c757d;
            font-weight: 600;
        }
        .detail-value {
            font-size: 1.1rem;
            color: #212529;
        }
    </style>
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

                <h1 class="h2 fw-bold mb-3">Alta de Reserva</h1>

                <p class="text-secondary">Nueva Reserva.</p>

                <% if (request.getAttribute("mensajeError") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("mensajeError") %>
                    </div>
                <% } %>

                <form action="${pageContext.request.contextPath}/AltaReservaServlet?nombreConsulta=<%= java.net.URLEncoder.encode(consulta.nomC(), "UTF-8") %>"" method="post">

                    <input type="hidden" name="consulta" value="<%= (consulta != null) ? consulta.nomC() : "" %>">

                    <div class="mb-3">
                        <div class="detail-label">Consulta</div>
                        <div class="detail-value fw-bold">
                            <%= (consulta != null) ? consulta.nomC() : "No seleccionada" %>
                        </div>
                    </div>

                    <div class="mb-3">
                        <div class="detail-label">Fecha</div>
                        <div class="detail-value">
                            <%= (consulta != null && consulta.fechaC() != null) ? consulta.fechaC() : "-" %>
                        </div>
                    </div>

                    <div class="mb-3">
                        <div class="detail-label">Sala</div>
                        <div class="detail-value">
                            <%= (consulta != null) ? consulta.sala() : "-" %>
                        </div>
                    </div>
                    
                    <!-- Selector para aplicar o no el beneficio anual -->
                    <div class="mb-3">
                        <label for="aplicaBenef" class="form-label detail-label">Aplica beneficio anual <span class="text-danger">*</span></label>
                        <select id="aplicaBenef" name="aplicaBenef" class="form-select" required>
                            <option value="false" selected>No</option>
                            <option value="true">Sí</option>
                        </select>
                    </div>

                    <hr>

                    <div class="row g-3">
                        <div class="col-sm-6">
                            <div class="detail-label">Costo de consulta</div>
                            <div class="detail-value fw-semibold">
                                $ <%= (consulta != null) ? consulta.costoC() : 0.0f %>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="detail-label">Costo de reserva</div>
                            <div class="detail-value fw-semibold text-primary">
                                $ <%= costoReserva %>
                            </div>
                        </div>
                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4">
                        <a href="${pageContext.request.contextPath}/home" onclick="history.back()" class="btn btn-secondary">
                            Cancelar
                        </a>

                        <button type="submit" class="btn btn-primary">
                            Reservar
                        </button>
                    </div>

                </form>
            </section>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>