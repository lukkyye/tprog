<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Alta de Dia de Consulta</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

    <header class="bg-white border-bottom p-3">
        <div class="container d-flex justify-content-between align-items-center">
            <h2 class="text-primary mb-0">salud.uy</h2>
            <a href="${pageContext.request.contextPath}/ListarClinicasServlet"
               class="btn btn-outline-secondary">
                Volver a clinicas
            </a>
        </div>
    </header>

    <main class="container py-5">
        <div class="card shadow-sm mx-auto" style="max-width: 750px;">
            <div class="card-body p-4">

                <h1 class="h3 mb-4">Alta de Dia de Consulta</h1>

                <% if (request.getAttribute("mensaje") != null) { %>
                    <div class="alert alert-info" role="alert">
                        <%= request.getAttribute("mensaje") %>
                    </div>
                <% } %>

                <form action="${pageContext.request.contextPath}/AltaDiaConsultaServlet"
                      method="post"
                      enctype="multipart/form-data">

                    <div class="mb-3">
                        <label for="nombre" class="form-label">
                            Nombre del dia de consulta *
                        </label>
                        <input type="text" id="nombre" name="nombre"
                               class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">
                            Descripcion *
                        </label>
                        <textarea id="descripcion" name="descripcion"
                                  class="form-control" rows="3"
                                  required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="dia" class="form-label">
                            Dia de la semana *
                        </label>
                        <select id="dia" name="dia" class="form-select" required>
                            <option value="">Seleccionar dia</option>
                            <option value="Lunes">Lunes</option>
                            <option value="Martes">Martes</option>
                            <option value="Miercoles">Miercoles</option>
                            <option value="Jueves">Jueves</option>
                            <option value="Viernes">Viernes</option>
                            <option value="Sabado">Sabado</option>
                            <option value="Domingo">Domingo</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="horaInicio" class="form-label">
                            Hora de inicio *
                        </label>
                        <input type="time" id="horaInicio" name="horaInicio"
                               class="form-control" required>
                    </div>
					<div class="mb-3">
    					<label for="imagen" class="form-label">
        					Imagen (opcional)
    					</label>
    					<input type="file"
           					id="imagen"
           					name="imagen"
           					class="form-control"
           					accept="image/*">
					</div>
                    <div class="mb-3">
                        <label for="nombreEspecialidad" class="form-label">
                            Especialidad *
                        </label>
                        <select id="nombreEspecialidad" name="nombreEspecialidad"
                                class="form-select" required>
                            <option value="">Seleccionar especialidad</option>
                            <%
                                java.util.Set<String> especialidades =
                                    (java.util.Set<String>) request.getAttribute("especialidades");

                                if (especialidades != null) {
                                    for (String especialidad : especialidades) {
                            %>
                                <option value="<%= especialidad %>">
                                    <%= especialidad %>
                                </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="nombreClinica" class="form-label">
                            Clinica *
                        </label>
                        <select id="nombreClinica" name="nombreClinica"
                                class="form-select" required>
                            <option value="">Seleccionar clinica</option>
                            <%
                                java.util.Set<String> clinicas =
                                    (java.util.Set<String>) request.getAttribute("clinicas");

                                if (clinicas != null) {
                                    for (String clinica : clinicas) {
                            %>
                                <option value="<%= clinica %>">
                                    <%= clinica %>
                                </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <p class="form-text">
                        El costo del dia de consulta se calcula automaticamente
                        a partir del costo de consulta de la clinica y el
                        porcentaje adicional de la especialidad.
                    </p>

                    <div class="d-flex justify-content-end gap-2 mt-4">
                        <a href="${pageContext.request.contextPath}/ListarClinicasServlet"
                           class="btn btn-secondary">
                            Cancelar
                        </a>

                        <button type="submit" class="btn btn-primary">
                            Registrar dia de consulta
                        </button>
                    </div>

                </form>
            </div>
        </div>
    </main>

</body>
</html>
```