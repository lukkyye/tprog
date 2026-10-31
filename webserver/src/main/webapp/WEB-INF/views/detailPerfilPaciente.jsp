<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ page import="com.models.datatypes.DTUsuario" %>
<%@ page import="com.models.datatypes.DTPaciente" %>
<%@ page import="com.models.datatypes.DTResConsulta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>

<%
DtUsuario usuario = (DtUsuario) request.getAttribute("usuario");
    DtPaciente paciente = (DtPaciente) request.getAttribute("datosPaciente");

    List<String> antecedentes = null;
    if (paciente != null) {
        antecedentes = paciente.antSalud();
    }

    Set<DtResConsulta> reservas =
            (Set<DtResConsulta>) request.getAttribute("reservas");

    boolean esMiPerfil =
            Boolean.TRUE.equals(request.getAttribute("esMiPerfil"));

    String tipoUsuarioSesion =
            (String) session.getAttribute("tipoUsuario");

    String fotoPerfil = null;
    if (usuario != null) {
        fotoPerfil = new com.gateway.delegators.ImageHandlerDelegator()
                .getImg64(usuario.nickname());
    }

    if (fotoPerfil == null || fotoPerfil.isBlank()) {
        fotoPerfil = request.getContextPath() + "/img/PACIENTE.png";
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Salud.uy - Perfil de paciente</title>

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

        .avatar {
            width: 150px;
            height: 150px;
            object-fit: cover;
            border: 4px solid white;
            box-shadow: 0 4px 14px rgba(0, 0, 0, 0.15);
        }

        .dato {
            padding: 13px 0;
            border-bottom: 1px solid #dee5eb;
        }

        .antecedente {
            display: inline-block;
            background-color: #e4f3fc;
            color: #164a7b;
            border-radius: 20px;
            padding: 8px 14px;
            margin: 4px;
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

<%
if (session.getAttribute("nickname") == null) {
%>
    <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
<%
} else {
%>
    <jsp:include page="/WEB-INF/templates/navbar.jsp" />
<%
}
%>

<main class="container-fluid py-5">
    <div class="layout-perfil d-flex gap-4">

		<%
		if ("Medico".equals(tipoUsuarioSesion)) {
		%>
    		<jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
		<%
		} else if ("Paciente".equals(tipoUsuarioSesion)) {
		%>
    		<jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
		<%
		} else {
		%>
    		<jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
		<%
		}
		%>

        <div class="contenido-perfil">
            <div class="card perfil-card">
                <div class="card-body p-4 p-md-5">

                    <div class="row align-items-center g-4 mb-4">
                        <div class="col-md-3 text-center">
                            <img class="avatar rounded-circle"
                                 src="<%=fotoPerfil%>"
                                 alt="Foto de perfil">
                        </div>

                        <div class="col-md-9">
                            <p class="text-uppercase text-secondary fw-semibold mb-1">
                                Perfil de paciente
                            </p>

                            <h1 class="titulo-seccion mb-3">
                                <%
                                if (usuario != null) {
                                %>
                                    <%=usuario.nombre()%> <%=usuario.apellido()%>
                                <%
                                } else {
                                %>
                                    Mis datos
                                <%
                                }
                                %>
                            </h1>

                            <%
                            if (usuario != null) {
                            %>
                                <p class="mb-1">
                                    <strong>Nickname:</strong>
                                    <%=usuario.nickname()%>
                                </p>

                                <p class="mb-0">
                                    <strong>Correo electrónico:</strong>
                                    <%=usuario.correo()%>
                                </p>
                            <%
                            }
                            %>
                        </div>
                    </div>

                    <hr>

                    <h2 class="titulo-seccion h4 mt-4 mb-3">
                        Datos del paciente
                    </h2>

                    <%
                    if (paciente != null) {
                    %>
                        <div class="dato">
                            <strong>Documento:</strong>
                            <%=paciente.doc()%>
                        </div>

                        <div class="dato">
                            <strong>Fecha de nacimiento:</strong>
                            <%=paciente.fechaNac()%>
                        </div>

                        <div class="dato">
                            <strong>Fecha de ingreso:</strong>
                            <%=paciente.fechaIngreso()%>
                        </div>

                        <div class="dato">
                            <strong>Órdenes acumuladas:</strong>
                            <%=paciente.cantOrdenesAcum()%>
                        </div>

                        <div class="dato">
                            <strong>Tickets acumulados:</strong>
                            <%=paciente.cantTicketAcum()%>
                        </div>

                        <h2 class="titulo-seccion h4 mt-4 mb-3">
                            Antecedentes de salud
                        </h2>

                        <%
                        if (antecedentes != null && !antecedentes.isEmpty()) {
                        %>
                            <%
                            for (String antecedente : antecedentes) {
                            %>
                                <span class="antecedente">
                                    <%=antecedente%>
                                </span>
                            <%
                            }
                            %>
                        <%
                        } else {
                        %>
                            <p class="text-secondary">
                                No tiene antecedentes registrados.
                            </p>
                        <%
                        }
                        %>
                    <%
                    } else {
                    %>
                        <p class="text-secondary">
                            No se encontraron los datos del paciente.
                        </p>
                    <%
                    }
                    %>

                    <%
                    if (esMiPerfil) {
                    %>
                        <h2 class="titulo-seccion h4 mt-4 mb-3">
                            Mis reservas de consultas
                        </h2>

                        <%
                        if (reservas != null && !reservas.isEmpty()) {
                        %>
                            <div class="list-group">
                                <%
                                for (DtResConsulta reserva : reservas) {
                                %>
                                    <a class="list-group-item list-group-item-action mb-2 rounded"
                                       href="${pageContext.request.contextPath}/DetalleReservaServlet?consulta=<%= java.net.URLEncoder.encode(reserva.nomC(), "UTF-8") %>&num=<%= reserva.numReserva() %>">

                                        <h5 class="mb-2">
                                            Reserva número <%= reserva.numReserva() %>
                                        </h5>

										<p class="mb-1">
                                            <strong>Consulta:</strong>
                                            <%= reserva.nomC() %>
                                        </p>

                                        <p class="mb-1">
                                            <strong>Fecha de reserva:</strong>
                                            <%= reserva.fechaRes() %>
                                        </p>

                                        <p class="mb-1">
                                            <strong>Aplica beneficio:</strong>
                                            <%= reserva.aplicaBenef() ? "Sí" : "No" %>
                                        </p>

                                        <p class="mb-0">
                                            <strong>Costo:</strong>
                                            <%= reserva.costoR() %>
                                        </p>
                                    </a>
                                <% } %>
                            </div>
                        <% } else { %>
                            <p class="text-secondary">
                                No tenés reservas de consultas.
                            </p>
                        <% } %>
                    <% } %>

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