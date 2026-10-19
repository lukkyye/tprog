<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.models.datatypes.DTResConsultaDetalle" %>

<%
    DTResConsultaDetalle reserva = (DTResConsultaDetalle) request.getAttribute("reserva");
	String tipoUsuario = (String) session.getAttribute("tipoUsuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Detalle de Reserva</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="icon" type="image/png"
          href="${pageContext.request.contextPath}/img/FAVICON.png">

    <style>
        .clinic-image {
            width: 100%;
            max-height: 350px;
            object-fit: contain;
            border-radius: 12px;
            background-color: #f8f9fa;
        }

        .detail-label {
            color: #6c757d;
            font-size: 0.95rem;
        }

        .detail-value {
            font-size: 1.05rem;
            overflow-wrap: anywhere;
        }
    </style>
</head>

<body class="min-vh-100 container-fluid bg-body">
  
<%
    if ("MEDICO".equals(tipoUsuario) || "PACIENTE".equals(tipoUsuario)) {
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
        if ("MEDICO".equals(tipoUsuario)) {
    %>
        <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
    <%
        } else if ("PACIENTE".equals(tipoUsuario)) {
    %>
        <jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
    <%
        } else {
    %>
        <jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
    <%
        }
    %>

    <section class="flex-grow-1 pe-4" style="min-width: 0;">


        <% if (reserva != null) { %>

            <div class="d-flex justify-content-between align-items-center mb-4">
                <h1>Detalle de Reserva</h1>
            </div>

            <div class="card shadow-sm border-0 rounded-4">
                <div class="card-body p-4 p-md-5">

                    <div class="row g-4">

                        <div class="col-md-5">
                           
                        </div>

                        <div class="col-md-7">
                            <h2 class="mb-4">
                            	Reserva</h2>

                            <div class="mb-3">
                                <div class="detail-label">Fecha</div>
                                <div class="detail-value">
                                    <%= reserva.fechaRes() %>
                                </div>
                            </div>

                            <div class="mb-3">
                                <div class="detail-label">Numero</div>
                                <div class="detail-value">
                                    <%= reserva.numReserva() %>
                                </div>
                            </div>

                            <div class="mb-3">
                                <div class="detail-label">Aplica beneficio anual</div>
                                <div class="detail-value">
                                    <%= reserva.aplicaBenef() ? "Sí" : "No" %>
                                </div>
                            </div>
                            
                            <hr>
                            <h2 class="mb-4">
                            	Consulta Asociada</h2>
                            <div class="mb-3">
                                <div class="detail-label">Nombre</div>
                                <div class="detail-value">
                                    <%= reserva.nomC() %>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <div class="detail-label">Fecha</div>
                                <div class="detail-value">
                                    <%= reserva.fechaC() %>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <div class="detail-label">Sala</div>
                                <div class="detail-value">
                                    <%= reserva.salaC() %>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <div class="detail-label">Cantidad de Numeros</div>
                                <div class="detail-value">
                                    <%= reserva.cantNumC() %>
                                </div>
                            </div>

                            <hr>

                            <div class="row g-3">
                                <div class="col-sm-6">
                                    <div class="detail-label">Costo de consulta</div>
                                    <div class="detail-value fw-semibold">
                                        $ <%= reserva.costoC() %>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="detail-label">Costo de reserva</div>
                                    <div class="detail-value fw-semibold">
                                        $ <%= reserva.costoR() %>
                                    </div>
                                </div>
                            </div>
                            
                        </div>

                    </div>
                </div>
            </div>
            <a href="${pageContext.request.contextPath}/misReservas"
                   class="btn btn-outline-secondary">
                    Volver a reservas
             </a>

        <% } else { %>

            <div class="alert alert-warning">
                No se encontraron los datos de la reserva.
            </div>

            <a href="${pageContext.request.contextPath}/misReservas"
               class="btn btn-secondary">
                Volver a reservas.
            </a>

        <% } %>
 		</section>
    </main>
</body>
</html>