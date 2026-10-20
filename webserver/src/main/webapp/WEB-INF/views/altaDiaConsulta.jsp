<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%
    String ctx = request.getContextPath();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    boolean esMedico = "Medico".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "Paciente".equalsIgnoreCase(tipoUsuario);
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture", new ImageHandlerDelegator().getImg64((String) session.getAttribute("nickname")));
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Alta de Dia de Consulta</title>
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

    
<div class="col-md-9 pe-5 d-flex flex-column gap-4">
    <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">

        <h1 class="h2 fw-bold mb-3">Alta de Dia Consulta</h1>

        <p class="text-secondary">
            Nuevo Dia de Consulta.
        </p>

        <p>
            Los campos marcados con
            <span class="text-danger">*</span>
            son obligatorios.
        </p>


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
                            Nombre del dia de consulta <span class="text-danger">*</span>
                        </label>
                        <input type="text" id="nombre" name="nombre"
                               class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">
                            Descripcion <span class="text-danger">*</span>
                        </label>
                        <textarea id="descripcion" name="descripcion"
                                  class="form-control" rows="3"
                                  required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="dia" class="form-label">
                            Dia de la semana <span class="text-danger">*</span>
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
                            Hora de inicio <span class="text-danger">*</span>
                        </label>
                        <input type="time" id="horaInicio" name="horaInicio"
                               class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="nombreEspecialidad" class="form-label">
                            Especialidad <span class="text-danger">*</span>
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
                            Clinica <span class="text-danger">*</span>
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

                    <p class="form-text">
                        El costo del dia de consulta se calcula automaticamente
                        a partir del costo de consulta de la clinica y el
                        porcentaje adicional de la especialidad.
                    </p>

                    <div class="d-flex justify-content-end gap-2 mt-4">
                        <a href="${pageContext.request.contextPath}/home"
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
         </section>
    </main>

</body>
</html>