<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page import="com.soap.DtConsulta" %>
<%@ page import="com.soap.DtResConsulta" %>
<%
    String ctx = request.getContextPath();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    boolean esMedico = "Medico".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "Paciente".equalsIgnoreCase(tipoUsuario);
    
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture", new ImageHandlerDelegator().getImg64((String) session.getAttribute("nickname")));
    }

    // Obtener la reserva enviada desde AltaDetalleReservaServlet
    DtResConsulta reserva = (DtResConsulta) request.getAttribute("reserva");
    
    
    Float costoReserva = (Float) request.getAttribute("costoReserva");
    if (costoReserva == null && reserva != null) {
        costoReserva = reserva.getCostoR();
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Alta de Detalle de Reserva</title>
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

                <h1 class="h2 fw-bold mb-3">Alta de Detalle de Reserva</h1>
                <p class="text-secondary">Ingrese la asistencia, motivo y diagnóstico para registrar el detalle de la consulta.</p>

                <% if (request.getAttribute("mensajeError") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("mensajeError") %>
                    </div>
                <% } %>

                <form action="${pageContext.request.contextPath}/AltaDetalleReservaServlet" method="post">

                    <input type="hidden" name="nomC" value="<%= (reserva != null && reserva.getNomC() != null) ? reserva.getNomC() : "" %>">
                    <input type="hidden" name="num" value="<%= (reserva != null) ? reserva.getNumReserva() : "" %>">
                    <input type="hidden" name="nicknamePaciente" value="<%= (reserva != null) ? reserva.getNicknamePaciente() : "" %>">

                    <div class="row g-3 mb-3">
                        <div class="col-md-4">
                            <div class="detail-label">Consulta</div>
                            <div class="detail-value fw-bold">
                                <%= (reserva != null && reserva.getNomC() != null) ? reserva.getNomC() : "No seleccionada" %>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="detail-label">Fecha de Reserva</div>
                            <div class="detail-value">
                                <%= (reserva != null && reserva.getFechaRes() != null) ? reserva.getFechaRes() : "-" %>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="detail-label">Paciente</div>
                            <div class="detail-value">
                                <%= (reserva != null && reserva.getNicknamePaciente() != null) ? reserva.getNicknamePaciente() : "-" %>
                            </div>
                        </div>
                    </div>

                    <hr class="my-4">

                    <div class="mb-3">
                        <label for="asistio" class="form-label detail-label">Asistió <span class="text-danger">*</span></label>
                        <select id="asistio" name="asistio" class="form-select" required>
                            <option value="true" selected>Sí</option>
                            <option value="false">No</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="motivo" class="form-label detail-label">Motivo de la consulta <span class="text-danger">*</span></label>
                        <textarea id="motivo" name="motivo" class="form-control" rows="3" placeholder="Ingrese el motivo de la consulta..." required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="diagnostico" class="form-label detail-label">Diagnóstico <span class="text-muted">(Opcional)</span></label>
                        <textarea id="diagnostico" name="diagnostico" class="form-control" rows="3" placeholder="Ingrese el diagnóstico si corresponde..."></textarea>
                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4">
                        <button type="button" onclick="history.back()" class="btn btn-secondary">
                            Cancelar
                        </button>

                        <button type="submit" class="btn btn-primary">
                            Dar de alta los detalles de la reserva
                        </button>
                        
                        <a href="${pageContext.request.contextPath}/AltaMedicamentoServlet?consulta=<%= java.net.URLEncoder.encode(reserva.getNomC(), "UTF-8") %>&paciente=<%=reserva.getNicknamePaciente()%>" 
                       class="btn btn-outline-primary btn-sm">
                        <button type="submit" class="btn btn-secondary">
                            Dar de alta Medicamento
                        </button>
                        </a>
                    </div>

                </form>
            </section>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
