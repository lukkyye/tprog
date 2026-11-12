<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.soap.DtResAsistio" %>

<%
DtResAsistio reserva = (DtResAsistio) request.getAttribute("reserva");
	String tipoUsuario = (String) session.getAttribute("tipoUsuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Detalle de Consulta</title>

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
    if ("Medico".equals(tipoUsuario) || "Paciente".equals(tipoUsuario)) {
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
        if ("Medico".equals(tipoUsuario)) {
    %>
        <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
    <%
        } else if ("Paciente".equals(tipoUsuario)) {
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
                <h1>Detalle de Consulta</h1>
            </div>

            <div class="card shadow-sm border-0 rounded-4">
                <div class="card-body p-4 p-md-5">

                    <div class="d-flex justify-content-start row g-4">

                

                        <div class="container-fluid">
                            <h2 class="mb-4">
                            	Consulta</h2>

                            <div class="mb-3">
                                <div class="detail-label">Nombre Consulta</div>
                                <div class="detail-value">
                                    <%= reserva.getNomConsulta() %>
                                </div>
                            </div>

                            <div class="mb-3">
                                <div class="detail-label">Numero de Reserva</div>
                                <div class="detail-value">
                                    <%= reserva.getNumRes() %>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <div class="detail-label">Paciente</div>
                                <div class="detail-value">
                                    <%= reserva.getNicknamePaciente() %>
                                </div>
                            </div>

                            <div class="mb-3">
                                <div class="detail-label">Asistio</div>
                                <div class="detail-value">
                                    <%= reserva.isAsistio() ? "Sí" : "No" %>
                                </div>
                            </div>
                            
                           
                            <div class="mb-3">
                                <div class="detail-label">Motivo</div>
                                <div class="detail-value">
                                    <%= reserva.getMotivo() %>
                                </div>
                            </div>
                            
                            <div class="mb-3">
                                <div class="detail-label">Diagnostico</div>
                                <div class="detail-value">
                                <% if (reserva.getDiagnostico() != null) { %>
                                    <%= reserva.getDiagnostico() %>
                                 <% } else { %>
                                 	No se encuentra diagnostico asociado.
                                 <% } %>
                                </div>
                            </div>
                            
                        </div>

                    </div>
                </div>
            </div>
            <a href="#" onclick="history.back()"
                   class="btn btn-outline-secondary">
                    Volver a reservas
             </a>

        <% } else { %>

            <div class="alert alert-warning">
                No se encontraron los detalles de la consulta.
            </div>

            <a href="#" onclick="history.back()"
               class="btn btn-secondary">
                Volver a reservas.
            </a>

        <% } %>
 		</section>
    </main>
</body>
</html>