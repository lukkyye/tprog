<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ page import="com.models.datatypes.DTUsuario" %>
<%@ page import="com.models.datatypes.DTMedico" %>
<%@ page import="com.models.datatypes.DTConsulta" %>
<%@ page import="com.models.datatypes.EstadoConsulta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>

<%
    DTUsuario usuario = (DTUsuario) request.getAttribute("usuario");
    DTMedico medico = (DTMedico) request.getAttribute("datosMedico");

    List<String> especialidades =
            (List<String>) request.getAttribute("especialidades");

    Set<DTConsulta> consultas =
            (Set<DTConsulta>) request.getAttribute("consultas");

    boolean esMiPerfil =
            Boolean.TRUE.equals(request.getAttribute("esMiPerfil"));

    String fotoPerfil = null;

    if (usuario != null) {
        fotoPerfil = new com.gateway.delegators.ImageHandlerDelegator()
                .getImg64(usuario.nickname());
    }

    if (fotoPerfil == null || fotoPerfil.isBlank()) {
        fotoPerfil = request.getContextPath() + "/img/MEDICO.png";
    }

    String tipoUsuarioSesion =
            (String) session.getAttribute("tipoUsuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Salud.uy - Mi perfil</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        body {
            min-height: 100vh;
            background-color: #eef3f7;
        }

        .barra-superior {
            background-color: white;
        }

        .layout-perfil {
            align-items: flex-start;
        }

        .contenido-perfil {
            flex: 1;
            min-width: 0;
        }

        .perfil-card {
            width: 100%;
            border: 0;
            border-radius: 20px;
            box-shadow: 0 8px 26px rgba(22, 74, 123, 0.12);
        }

        .titulo-seccion {
            color: #164a7b;
        }

        .dato {
            padding: 12px 0;
            border-bottom: 1px solid #dee5eb;
        }

        .avatar {
            width: 150px;
            height: 150px;
            object-fit: contain;
        }

        .etiqueta-especialidad {
            display: inline-block;
            margin: 4px;
            padding: 8px 14px;
            border-radius: 20px;
            background-color: #e4f3fc;
            color: #164a7b;
        }

        .consulta-enlace {
            color: #212529;
            text-decoration: none;
        }

        .consulta-enlace:hover {
            color: #164a7b;
            background-color: #f8fbfd;
        }

        @media (max-width: 767px) {
            .layout-perfil {
                flex-direction: column;
            }

            .layout-perfil > aside {
                flex-basis: auto !important;
                width: 100%;
                max-width: 100% !important;
                max-height: 45vh !important;
            }
        }
    </style>
</head>

<body>

<% if (session.getAttribute("nickname") == null) { %>
    <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
<% } else { %>
    <jsp:include page="/WEB-INF/templates/navbar.jsp" />
<% } %>

<main class="container-fluid py-5">
    <div class="layout-perfil d-flex gap-4">

		<% if ("Medico".equals(tipoUsuarioSesion)) { %>
    		<jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
		<% } else if ("Paciente".equals(tipoUsuarioSesion)) { %>
    		<jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
		<% } else { %>
    		<jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
		<% } %>

        <div class="contenido-perfil">
            <div class="card perfil-card">
                <div class="card-body p-4 p-md-5">

                    <div class="row align-items-center g-4 mb-4">
                        <div class="col-md-3 text-center">
                            <img class="avatar"
                                 src="<%= fotoPerfil %>"
                                 alt="Perfil de médico">
                        </div>

                        <div class="col-md-9">
                            <p class="text-uppercase text-secondary fw-semibold mb-1">
                                Perfil de médico
                            </p>

                            <h1 class="titulo-seccion mb-3">
                                <% if (usuario != null) { %>
                                    <%= usuario.nombre() %> <%= usuario.apellido() %>
                                <% } else { %>
                                    Mis datos
                                <% } %>
                            </h1>

                            <% if (usuario != null) { %>
                                <p class="mb-1">
                                    <strong>Nickname:</strong>
                                    <%= usuario.nickname() %>
                                </p>

                                <p class="mb-0">
                                    <strong>Correo electrónico:</strong>
                                    <%= usuario.correo() %>
                                </p>
                            <% } %>
                        </div>
                    </div>

                    <hr>

                    <h2 class="titulo-seccion h4 mt-4 mb-3">
                        Datos del médico
                    </h2>

                    <% if (medico != null) { %>
                        <div class="dato">
                            <strong>Número de registro:</strong>
                            <%= medico.nroReg() %>
                        </div>

                        <div class="dato">
                            <strong>Mes de licencia:</strong>
                            <%= medico.mesLic() %>
                        </div>

                        <div class="dato">
                            <strong>Porcentaje adicional:</strong>
                            <%= medico.porcentajeCost() %>%
                        </div>

                        <div class="dato">
                            <strong>Sitio web:</strong>
                            <% if (medico.link() != null && !medico.link().isBlank()) { %>
                                <%= medico.link() %>
                            <% } else { %>
                                No cuenta con un sitio web.
                            <% } %>
                        </div>
                    <% } else { %>
                        <p class="text-secondary">
                            No se encontraron los datos del médico.
                        </p>
                    <% } %>

                    <h2 class="titulo-seccion h4 mt-4 mb-3">
                        Especialidades
                    </h2>

                    <% if (especialidades != null && !especialidades.isEmpty()) { %>
                        <% for (String especialidad : especialidades) { %>
                            <span class="etiqueta-especialidad">
                                <%= especialidad %>
                            </span>
                        <% } %>
                    <% } else { %>
                        <p class="text-secondary">
                            No tiene especialidades registradas.
                        </p>
                    <% } %>

                    <h2 class="titulo-seccion h4 mt-4 mb-3">
                        Consultas asociadas
                    </h2>

                    <div class="list-group">
                        <%
                            boolean hayConsultasVisibles = false;

                            if (consultas != null) {
                                for (DTConsulta consulta : consultas) {
                                    if (esMiPerfil
                                            || consulta.estado() == EstadoConsulta.Aceptada) {
                                        hayConsultasVisibles = true;
                        %>
                                            <a class="list-group-item list-group-item-action consulta-enlace mb-2 rounded"
                                               href="${pageContext.request.contextPath}/ConsultaConsultaServlet?nombreConsulta=<%= java.net.URLEncoder.encode(consulta.nomC(), "UTF-8") %>">

                                                <h5 class="mb-2">
                                                    <%= consulta.nomC() %>
                                                </h5>

                                                <p class="mb-1">
                                                    <strong>Fecha:</strong>
                                                    <%= consulta.fechaC() %>
                                                </p>

                                                <p class="mb-1">
                                                    <strong>Estado:</strong>
                                                    <%= consulta.estado() %>
                                                </p>

                                                <p class="mb-1">
                                                    <strong>Sala:</strong>
                                                    <%= consulta.sala() %>
                                                </p>

                                                <p class="mb-1">
                                                    <strong>Cantidad de números:</strong>
                                                    <%= consulta.cantNum() %>
                                                </p>

                                                <p class="mb-0">
                                                    <strong>Costo:</strong>
                                                    <%= consulta.costoC() %>
                                                </p>
                                            </a>
                        <%
                                    }
                                }
                            }

                            if (!hayConsultasVisibles) {
                        %>
                                <p class="text-secondary">
                                    No hay consultas para mostrar.
                                </p>
                        <%
                            }
                        %>
                    </div>

                    <div class="text-center mt-5">
                        <a href="${pageContext.request.contextPath}/home"
                           class="btn btn-primary px-4">
                            Volver al inicio
                        </a>
                    </div>

                </div>
            </div>
        </div>

    </div>
</main>

</body>
</html>