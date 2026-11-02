
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.soap.DtClinica" %>

<%
DtClinica clinica = (DtClinica) request.getAttribute("clinica");
	String tipoUsuario = (String) session.getAttribute("tipoUsuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Detalle de clinica</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="icon" type="image/png"
          href="/webserver/img/FAVICON.png">

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


        <% if (clinica != null) { %>

            <div class="d-flex justify-content-between align-items-center mb-4">
                <h1>Detalle de clinica</h1>
            </div>

            <div class="card shadow-sm border-0 rounded-4">
                <div class="card-body p-4 p-md-5">

                    <div class="row g-4">

                        <div class="col-md-5">
                            <%
                                String imagen = (new com.gateway.delegators.ImageHandlerDelegator()).getImg64(clinica.getNombre());
                                if (imagen != null && !imagen.isBlank()) {
                                    String src = imagen;
                            %>
                                <img class="clinic-image"
                                     src="<%= src %>"
                                     alt="Imagen de la clinica">
                            <%
                                } else {
                            %>
                                <div class="clinic-image d-flex align-items-center justify-content-center"
                                     style="height: 250px;">
                                    <span class="text-secondary">
                                        No hay imagen disponible
                                    </span>
                                </div>
                            <%
                                }
                            %>
                        </div>

                        <div class="col-md-7">
                            <h2 class="mb-4"><%= clinica.getNombre() %></h2>

                            <div class="mb-3">
                                <div class="detail-label">Descripcion</div>
                                <div class="detail-value">
                                    <%= clinica.getDesc() != null
                                            && !clinica.getDesc().isBlank()
                                            ? clinica.getDesc()
                                            : "Sin descripcion disponible" %>
                                </div>
                            </div>

                            <div class="mb-3">
                                <div class="detail-label">Direccion</div>
                                <div class="detail-value">
                                    <%= clinica.getDir() != null
                                            && !clinica.getDir().isBlank()
                                            ? clinica.getDir()
                                            : "Sin direccion disponible" %>
                                </div>
                            </div>

                            <div class="mb-3">
                                <div class="detail-label">Sitio web</div>
                                <div class="detail-value">
                                    <% if (clinica.getWeb() != null
                                            && !clinica.getWeb().isBlank()) { %>
                                        <a href="<%= clinica.getWeb() %>"
                                           target="_blank"
                                           rel="noopener noreferrer">
                                            <%= clinica.getWeb() %>
                                        </a>
                                    <% } else { %>
                                        Sin sitio web disponible
                                    <% } %>
                                </div>
                            </div>

                            <hr>

                            <div class="row g-3">
                                <div class="col-sm-6">
                                    <div class="detail-label">Costo del ticket</div>
                                    <div class="detail-value fw-semibold">
                                        $ <%= clinica.getCTicket() %>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="detail-label">Costo de consulta</div>
                                    <div class="detail-value fw-semibold">
                                        $ <%= clinica.getCConsulta() %>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                <div class="d-flex justify-content-center mt-5" >
                <a href="${pageContext.request.contextPath}/ListarClinicasServlet"
                   class="btn btn-secondary">
                    Volver a clinicas
        		</a>
        		</div>
                </div>

            </div>

        <% } else { %>

            <div class="alert alert-warning">
                No se encontraron los datos de la clinica.
            </div>

            <a href="${pageContext.request.contextPath}/ListarClinicasServlet"
               class="btn btn-secondary">
                Volver a clinicas
            </a>

        <% } %>
        
 		</section>
    </main>
</body>
</html>
