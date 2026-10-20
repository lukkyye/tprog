<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="com.models.datatypes.DTConsulta" %>
<%@ page import="com.gateway.delegators.ClinicControllerDelegator" %>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%
    ClinicControllerDelegator clinicController =
        new ClinicControllerDelegator();

	String ctx = request.getContextPath();
    Set<String> especialidades = clinicController.listarEspecialidades();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario"); // hay que ajustar tipo usuario y todo eso 
    boolean esMedico = "Medico".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "Paciente".equalsIgnoreCase(tipoUsuario);
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture", new ImageHandlerDelegator().get_img64((String) session.getAttribute("nickname")));
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Mis Consultas</title>
    <link rel="icon" type="image/png" href="<%= ctx %>/img/FAVICON.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
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


        <section class="col-md-9 pe-5 d-flex flex-column gap-4">
            <h1 class="mb-4">Mis Consultas</h1>

            <form action="${pageContext.request.contextPath}/MisReservasServlet" 
                  method="get"> 

                <div class="mb-4">
                    <label for="clinica" class="form-label">
                        Seleccione una consulta:
                    </label>

                    <select id="consulta" name="consulta"
                            class="form-select" required>

                        <option value="" selected disabled>
                            Seleccione una consulta
                        </option>

                        <%
                            HashSet<DTConsulta> consultas =
                                (HashSet<DTConsulta>) request.getAttribute("consultas");

                            if (consultas != null && !consultas.isEmpty()) {
                                for (DTConsulta consulta : consultas) {
                        %>
                                    <option value="<%= consulta.nomC() %>">
                                        <%= consulta.nomC() %>  -  <%= consulta.fechaC() %>
                                    </option>
                        <%
                                }
                            } else {
                        %>
                                <option value="" disabled>
                                    No hay consultas asociadas
                                </option>
                        <%
                            }
                        %>

                    </select>
                </div>

                <button type="submit" class="btn btn-primary">
                    Ver datos
                </button>

                <a href="/webserver/home"
                   class="btn btn-secondary">
                    Volver
                </a>
            </form>
        </section>

    </main>
</body>
</html>
