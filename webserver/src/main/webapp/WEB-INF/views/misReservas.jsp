<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="com.models.datatypes.DTResConsulta" %>
<%	
	String tipoUsuario = (String) session.getAttribute("tipoUsuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Mis Reservas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="/webserver/img/FAVICON.png">

    <style>
        .hoverable:hover {
            background-color: rgba(180, 180, 180, 0.8);
        }

        .sidebar-scroll::-webkit-scrollbar {
            width: 6px;
        }

        .sidebar-scroll::-webkit-scrollbar-thumb {
            background-color: rgba(0, 0, 0, 0.2);
            border-radius: 4px;
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

        <section class="col-md-9 pe-5 d-flex flex-column gap-4">
            <h1 class="mb-4">Mis Reservas</h1>

<%
		HashSet<DTResConsulta> reservas = (HashSet<DTResConsulta>) request.getAttribute("reservas");
    if (reservas != null && !reservas.isEmpty()) {
        for (DTResConsulta reserva : reservas) {
%>
            <div class="card shadow rounded-4 border-0 bg-light p-4 mb-3">
                <div class="d-flex align-items-center mb-3">
                    
                    <%
                    	String coso = String.valueOf(reserva.numReserva()) + "  -  " + reserva.nomC();
                    %>
                    <div class="col-9 ps-3">
                        <h4 class="m-0 fw-bold">Reserva #<%=coso %></h4>
                        <p class="mb-0 text-muted mt-2"><strong>Fecha:</strong> <%= reserva.fechaRes() %></p>
                        <p class="mb-0 text-muted"><strong>Costo Total:</strong> $<%= reserva.costoR() %></p>
                        <p class="mb-0 text-muted"><strong>Aplica Beneficio Anual:</strong> 
                            <span class="badge <%= reserva.aplicaBenef() ? "bg-success" : "bg-secondary" %>">
                                <%= reserva.aplicaBenef() ? "Sí" : "No" %>
                            </span>
                        </p>
                    </div>
                </div>

                <div class="d-flex justify-content-end gap-2 pt-2 border-top">
                    <a href="${pageContext.request.contextPath}/DetalleReservaServlet?consulta=<%= java.net.URLEncoder.encode(reserva.nomC(), "UTF-8") %>&num=<%=reserva.numReserva()%>" 
                       class="btn btn-outline-primary btn-sm">
                        Ver Detalle
                    </a>
                </div>
            </div>
<%
        }
    } else {
%>
            <div class="alert alert-info shadow-sm rounded-4 text-center p-4 fs-5" role="alert">
                No tienes reservas registradas en este momento.
            </div>
<%
    }
%>
			
            <div>
<%
    if ("Medico".equals(tipoUsuario)) {
%>
    <a href="${pageContext.request.contextPath}/MisConsultasServlet" class="btn btn-secondary">
                    Volver
                </a>
<%
    } else {
%>
    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
                    Volver
                </a>
<%
    }
%>
        
            </div>
        </section>

    </main>
</body>
</html>