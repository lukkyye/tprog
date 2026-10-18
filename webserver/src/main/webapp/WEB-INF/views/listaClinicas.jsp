<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
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
    <title>Salud.uy - Consulta de Clinicas</title>

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
            <h1 class="mb-4">Clínicas</h1>

            <form action="${pageContext.request.contextPath}/DetalleClinicaVisitante"
                  method="get">

                <div class="mb-4">
                    <label for="clinica" class="form-label">
                        Seleccione una clinica:
                    </label>

                    <select id="clinica" name="clinica"
                            class="form-select" required>

                        <option value="" selected disabled>
                            Seleccione una clinica
                        </option>

                        <%
                            Set<String> clinicas =
                                (Set<String>) request.getAttribute("clinicas");

                            if (clinicas != null && !clinicas.isEmpty()) {
                                for (String nombreClinica : clinicas) {
                        %>
                                    <option value="<%= nombreClinica %>">
                                        <%= nombreClinica %>
                                    </option>
                        <%
                                }
                            } else {
                        %>
                                <option value="" disabled>
                                    No hay clinicas disponibles
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
