<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="com.models.datatypes.DTUsuario" %>

<%
    HashSet<DTUsuario> usuarios =
            (HashSet<DTUsuario>) request.getAttribute("usuarios");

    String error = (String) request.getAttribute("error");
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    String nicknameSesion = (String) session.getAttribute("nickname");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Salud.uy - Consultar usuario</title>

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

        .contenido {
            flex: 1;
            min-width: 0;
        }

        .tarjeta-consulta {
            max-width: 850px;
            border: 0;
            border-radius: 20px;
            box-shadow: 0 8px 26px rgba(22, 74, 123, 0.12);
        }

        .titulo {
            color: #164a7b;
        }

        @media (max-width: 767px) {
            .layout-consulta {
                flex-direction: column;
            }

            .layout-consulta > aside {
                flex-basis: auto !important;
                width: 100%;
                max-width: 100% !important;
                margin-left: 0 !important;
            }
        }
    </style>
</head>

<body>

<% if ("Medico".equals(tipoUsuario) || "Paciente".equals(tipoUsuario)) { %>
	<jsp:include page="/WEB-INF/templates/navbar.jsp" />
<% } else { %>
    <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
<% } %>

<main class="container-fluid py-4">
    <div class="layout-consulta d-flex align-items-start gap-4">

        <% if ("Medico".equals(tipoUsuario)) { %>
            <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
        <% } else if ("Paciente".equals(tipoUsuario)) { %>
            <jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
        <% } %>

        <section class="contenido">
            <div class="card tarjeta-consulta mx-auto">
                <div class="card-body p-4 p-md-5">

                    <h1 class="titulo mb-4">Consultar usuario</h1>

                    <% if (error != null) { %>
                        <div class="alert alert-danger">
                            <%= error %>
                        </div>
                    <% } %>

                    <% if (usuarios != null && !usuarios.isEmpty()) { %>
                        <form action="${pageContext.request.contextPath}/consultaUsuario"
                              method="get">

                            <label for="nickname" class="form-label">
                                Elegí un usuario
                            </label>

                            <select id="nickname"
                                    name="nickname"
                                    class="form-select mb-4"
                                    required>
                                <option value="">Seleccionar...</option>

                                <% for (DTUsuario u : usuarios) { %>
                                    <option value="<%= u.nickname() %>">
                                        <%= u.nombre() %> <%= u.apellido() %>
                                        — <%= u.nickname() %>
                                    </option>
                                <% } %>
                            </select>

                            <button type="submit" class="btn btn-primary">
                                Ver perfil
                            </button>
                        </form>
                    <% } else { %>
                        <p class="text-secondary">
                            No hay usuarios para mostrar.
                        </p>
                    <% } %>

                    <a href="${pageContext.request.contextPath}/home"
                       class="btn btn-outline-secondary mt-3">
                        Volver al inicio
                    </a>

                </div>
            </div>
        </section>

    </div>
</main>

</body>
</html>