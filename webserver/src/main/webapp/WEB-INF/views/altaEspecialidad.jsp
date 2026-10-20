<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%
    String ctx = request.getContextPath();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    boolean esMedico = "MEDICO".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "PACIENTE".equalsIgnoreCase(tipoUsuario);
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture", new ImageHandlerDelegator().getImg64((String) session.getAttribute("nickname")));
    }

    String errorEspecialidad = (String) request.getAttribute("errorEspecialidad");
    String nombreIngresado = (String) request.getAttribute("nombreIngresado");
    String porcentajeIngresado = (String) request.getAttribute("porcentajeIngresado");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Alta de Especialidad</title>
    <link rel="icon" type="image/png" href="<%= ctx %>/img/FAVICON.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
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

                <h1 class="h2 fw-bold mb-3">Alta de Especialidad</h1>

                <p class="text-secondary">Ingrese los datos para registrar una nueva especialidad medica.</p>

                <p>
                    Los campos marcados con <span class="text-danger">*</span> son obligatorios.
                </p>

                <% if (errorEspecialidad != null) { %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Error:</strong> <%= errorEspecialidad %>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                <% } %>

                <form action="${pageContext.request.contextPath}/AltaEspecialidadServlet" method="post">

                    <div class="mb-3">
                        <label for="nombre" class="form-label fw-bold">Nombre de la Especialidad <span class="text-danger">*</span></label>
                        <input type="text" 
                               class="form-control" 
                               id="nombre" 
                               name="nombre" 
                               placeholder="Ej. Cardiologia" 
                               value="<%= nombreIngresado != null ? nombreIngresado : "" %>" 
                               required>
                    </div>

                    <div class="mb-3">
                        <label for="porcentaje" class="form-label fw-bold">Porcentaje adicional (%) <span class="text-danger">*</span></label>
                        <input type="number" 
                               step="0.01" 
                               min="0" 
                               max="100" 
                               class="form-control" 
                               id="porcentaje" 
                               name="porcentaje" 
                               placeholder="Ej. 10.5" 
                               value="<%= porcentajeIngresado != null ? porcentajeIngresado : "0" %>" 
                               required>
                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4">
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">
                            Cancelar
                        </a>

                        <button type="submit" class="btn btn-primary">
                            Aceptar
                        </button>
                    </div>

                </form>
            </section>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>