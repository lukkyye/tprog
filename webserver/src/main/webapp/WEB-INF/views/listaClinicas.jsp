<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Consulta de Clinicas</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/FAVICON.png">

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

    <header class="mb-2">
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid d-flex justify-content-between align-items-center">

                <a class="navbar-brand"
                   href="${pageContext.request.contextPath}/index.html">
                    <img src="${pageContext.request.contextPath}/img/LOGOGENERAL.png"
                         width="120" alt="Salud.uy">
                </a>

                <form class="d-flex mx-auto"
                      style="max-width: 500px; width: 100%;"
                      action="${pageContext.request.contextPath}/Buscar"
                      method="get">
                    <input class="form-control me-2"
                           type="search"
                           name="busqueda"
                           placeholder="Buscar..."
                           aria-label="Buscar">
                    <button class="btn btn-outline-success" type="submit">
                        Buscar
                    </button>
                </form>

                <div class="d-flex gap-2 align-items-center">
                    <a href="${pageContext.request.contextPath}/auth/login.html"
                       class="btn btn-secondary">Iniciar sesion</a>

                    <a href="${pageContext.request.contextPath}/auth/signUp.html"
                       class="btn btn-primary">Registrarse</a>

                    <img style="height: 38px;"
                         class="rounded-circle border"
                         src="${pageContext.request.contextPath}/img/VISITANTE.png"
                         alt="Perfil visitante">
                </div>
            </div>
        </nav>
        <hr class="text-black m-0">
    </header>

    <main class="container-fluid d-flex mt-5 gap-4">

        <aside class="d-flex flex-column gap-4 ms-5 p-0 pe-2 sidebar-scroll"
               style="flex: 1 1 0%; max-width: 25%; max-height: 75vh; overflow-y: auto;">

            <div class="container d-flex p-0 flex-column rounded-4 bg-light shadow align-items-center">
                <h5 class="pt-3">Especialidades</h5>

                <ul class="nav nav-fill d-flex flex-column gap-3 w-100 pb-3">
                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/consultas/listaConsultasDiaAprobadaVisitante.html">Cardiologia</a>
                    </li>
                    <hr class="w-75 m-auto">

                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/consultas/listaConsultasDiaAprobadaVisitante.html">Pediatria</a>
                    </li>
                    <hr class="w-75 m-auto">

                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/consultas/listaConsultasDiaAprobadaVisitante.html">Fisioterapia</a>
                    </li>
                    <hr class="w-75 m-auto">

                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/consultas/listaConsultasDiaAprobadaVisitante.html">Nutricion</a>
                    </li>
                    <hr class="w-75 m-auto">

                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/consultas/listaConsultasDiaAprobadaVisitante.html">Ginecologia</a>
                    </li>
                    <hr class="w-75 m-auto">

                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/consultas/listaConsultasDiaAprobadaVisitante.html">Oncologia</a>
                    </li>
                    <hr class="w-75 m-auto">

                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/consultas/listaConsultasDiaAprobadaVisitante.html">Medicina General</a>
                    </li>
                    <hr class="w-75 m-auto">

                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/consultas/listaConsultasDiaAprobadaVisitante.html">Oftalmologia</a>
                    </li>
                </ul>
            </div>

            <div class="container d-flex p-0 flex-column rounded-4 bg-light shadow align-items-center">
                <h5 class="pt-3">Consultar</h5>

                <ul class="nav nav-fill d-flex flex-column gap-3 w-100 pb-3">
                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/users/listaUsuariosVisitante.html">Usuarios</a>
                    </li>
                    <hr class="w-75 m-auto">

                    <li class="nav-item hoverable">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/ClinicasVisitante">Clinicas</a>
                    </li>
                </ul>
            </div>
        </aside>

        <section class="col-md-9 pe-5 d-flex flex-column gap-4">
            <h1 class="mb-4">Clinicas</h1>

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

                <a href="${pageContext.request.contextPath}/index.html"
                   class="btn btn-secondary">
                    Volver
                </a>
            </form>
        </section>

    </main>
</body>
</html>
