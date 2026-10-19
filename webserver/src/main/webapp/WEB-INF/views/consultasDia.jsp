<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.models.datatypes.DTConsulta" %>
<%@ page import="com.gateway.delegators.ClinicControllerDelegator" %>
<%
    ClinicControllerDelegator clinicController =
        new ClinicControllerDelegator();

    Set<String> especialidades = clinicController.listarEspecialidades();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario"); // hay que ajustar tipo usuario y todo eso 
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Mis Consultas</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
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


        <section class="col-md-9 pe-5 d-flex flex-column gap-4">

    <h1 class="mb-2">Consultas del día</h1>

    <%
        Set<DTConsulta> consultas =
            (Set<DTConsulta>) request.getAttribute("consultas");

        if (consultas != null && !consultas.isEmpty()) {
            for (DTConsulta consulta : consultas) {
    %>

    <div class="card shadow-sm rounded-4 border-0 p-4">
        <h4 class="fw-bold"><%= consulta.nomC() %></h4>

        <p>Fecha: <%= consulta.fechaC() %></p>
        <p>Sala: <%= consulta.sala() %></p>
        <p>Cantidad de números: <%= consulta.cantNum() %></p>
        <p>Costo: $<%= consulta.costoC() %></p>

        <div class="d-flex gap-2">
			<a href="${pageContext.request.contextPath}/ConsultaConsultaServlet?nombreConsulta=<%= java.net.URLEncoder.encode(consulta.nomC(), "UTF-8") %>">
    			Ver detalle
			</a>
			<% if ("PACIENTE".equals(tipoUsuario)) { %>
    		<a href="${pageContext.request.contextPath}/AltaReservaServlet?nombreConsulta=<%= java.net.URLEncoder.encode(consulta.nomC(), "UTF-8") %>">
        		Reservar
    		</a>
			<% } %>
        </div>
    </div>

<% } %>

    <%
        } else {
    %>

        <div class="alert alert-info">
            No hay consultas aceptadas asociadas a este día.
        </div>

    <%
        }
    %>

    <a href="${pageContext.request.contextPath}/home"
       class="btn btn-secondary align-self-start">
        Volver
    </a>

</section>

    </main>
</body>
</html>