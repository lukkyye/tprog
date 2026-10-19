<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.models.datatypes.DTUsuario" %>
<%@ page import="com.models.datatypes.DTMedico" %>
<%@ page import="java.util.List" %>

<%
    DTUsuario usuario = (DTUsuario) request.getAttribute("usuario");
    DTMedico medico = (DTMedico) request.getAttribute("datosMedico");
    List<String> especialidades =
            (List<String>) request.getAttribute("especialidades");
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

        .perfil-card {
            max-width: 950px;
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

        .dato:last-child {
            border-bottom: 0;
        }

        .avatar {
            width: 150px;
            height: 150px;
            object-fit: contain;
        }

        .etiqueta-especialidad {
            background-color: #e4f3fc;
            color: #164a7b;
            border-radius: 20px;
            padding: 8px 14px;
            display: inline-block;
            margin: 4px;
        }
    </style>
</head>

<body>

<header class="barra-superior border-bottom px-4 py-3">
    <nav class="d-flex justify-content-between align-items-center">
        <a href="${pageContext.request.contextPath}/home">
            <img src="${pageContext.request.contextPath}/img/LOGOGENERAL.png"
                 alt="Salud.uy"
                 width="120">
        </a>

        <div class="d-flex align-items-center gap-3">
            <span class="fw-bold text-dark">
                ${sessionScope.nickname}
            </span>

            <a href="${pageContext.request.contextPath}/logout"
               class="btn btn-secondary">
                Cerrar sesión
            </a>
        </div>
    </nav>
</header>

<main class="container py-5">

    <div class="card perfil-card mx-auto">
        <div class="card-body p-4 p-md-5">

            <div class="row align-items-center g-4 mb-4">
                <div class="col-md-3 text-center">
                    <img class="avatar"
                         src="${pageContext.request.contextPath}/img/MEDICO.png"
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

            <h2 class="titulo-seccion h4 mt-4 mb-3">Datos del médico</h2>

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
                        No cuenta con un sitio web
                    <% } %>
                </div>
                
                
            <% } else { %>
                <p class="text-secondary">No se encontraron los datos del médico.</p>
            <% } %>

            <h2 class="titulo-seccion h4 mt-4 mb-3">Especialidades</h2>

            <% if (especialidades != null && !especialidades.isEmpty()) { %>
                <% for (String especialidad : especialidades) { %>
                    <span class="etiqueta-especialidad">
                        <%= especialidad %>
                    </span>
                <% } %>
            <% } else { %>
                <p class="text-secondary">No tiene especialidades registradas.</p>
            <% } %>

            <div class="text-center mt-5">
                <a href="${pageContext.request.contextPath}/home"
                   class="btn btn-primary px-4">
                    Volver al inicio
                </a>
            </div>

        </div>
    </div>

</main>

</body>
</html>