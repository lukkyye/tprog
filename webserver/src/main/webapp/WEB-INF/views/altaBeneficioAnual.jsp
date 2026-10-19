<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.models.datatypes.DTPaciente" %>
<%!
    private static String h(Object valor) {
        if (valor == null) {
            return "";
        }
        return valor.toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    String ctx = request.getContextPath();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    boolean esMedico = "Medico".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "Paciente".equalsIgnoreCase(tipoUsuario);
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture", new ImageHandlerDelegator().get_img64((String) session.getAttribute("nickname")));
    }
%>
<%
    @SuppressWarnings("unchecked")
    List<DTPaciente> pacientes = (List<DTPaciente>) request.getAttribute("pacientes");
    String pacienteSel = (String) request.getAttribute("pacienteSel");
    String mensaje = (String) request.getAttribute("mensaje");
    String exito = (String) request.getAttribute("exito");
    boolean hayPacientes = pacientes != null && !pacientes.isEmpty();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Alta de Beneficio Anual</title>
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

                <h1 class="h2 fw-bold mb-3">Alta de Beneficio Anual</h1>

                <% if (exito != null) { %>
                    <div class="alert alert-success" role="alert"><%= h(exito) %></div>
                <% } %>

                <% if (mensaje != null) { %>
                    <div class="alert alert-danger" role="alert"><%= h(mensaje) %></div>
                <% } %>

                <% if (!hayPacientes) { %>
                    <div class="alert alert-warning" role="alert">
                        Debe existir al menos un paciente dado de alta.
                    </div>
                <% } else { %>

                <p class="text-secondary">
                    Los campos marcados con <span class="text-danger">*</span>
                    son obligatorios.
                </p>
                <div id="errorCliente" class="alert alert-danger d-none" role="alert"></div>

                <form id="formBeneficio" action="<%= ctx %>/AltaBeneficioAnualServlet"
                      method="post" onsubmit="return validarBeneficio();">

                    <div class="mb-3">
                        <label for="paciente" class="form-label">
                            Paciente <span class="text-danger">*</span>
                        </label>
                        <select id="paciente" name="paciente" class="form-select" required>
                            <option value="">Seleccionar paciente</option>
                            <% for (DTPaciente p : pacientes) { %>
                                <option value="<%= h(p.nickname()) %>"
                                    <%= p.nickname().equals(pacienteSel) ? "selected" : "" %>>
                                    <%= h(p.nickname()) %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="anio" class="form-label">
                            Año <span class="text-danger">*</span>
                        </label>
                        <input type="number" id="anio" name="anio" class="form-control"
                               min="1" step="1" required
                               value="<%= h(request.getAttribute("anioVal")) %>">
                    </div>

                    <div class="mb-3">
                        <label for="cantOrdenes" class="form-label">
                            Órdenes de consulta gratuitas <span class="text-danger">*</span>
                        </label>
                        <input type="number" id="cantOrdenes" name="cantOrdenes"
                               class="form-control" min="0" step="1" required
                               value="<%= h(request.getAttribute("ordenesVal")) %>">
                    </div>

                    <div class="mb-3">
                        <label for="cantTicket" class="form-label">
                            Tickets de medicamento gratuitos <span class="text-danger">*</span>
                        </label>
                        <input type="number" id="cantTicket" name="cantTicket"
                               class="form-control" min="0" step="1" required
                               value="<%= h(request.getAttribute("ticketsVal")) %>">
                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4">
                        <a href="<%= ctx %>/home" class="btn btn-secondary">Cancelar</a>
                        <button type="submit" class="btn btn-primary">Aceptar</button>
                    </div>
                </form>

                <% } %>
            </section>
        </div>
    </main>
    <script>
        function validarBeneficio() {
            var anio = Number(document.getElementById('anio').value);
            var ordenes = Number(document.getElementById('cantOrdenes').value);
            var tickets = Number(document.getElementById('cantTicket').value);
            var mensaje = '';

            if (!Number.isInteger(anio) || !Number.isInteger(ordenes) || !Number.isInteger(tickets)) {
                mensaje = 'El año y las cantidades deben ser números enteros.';
            } else if (anio < 1 || ordenes < 0 || tickets < 0) {
                mensaje = 'El año debe ser positivo y las cantidades no pueden ser negativas.';
            }

            var caja = document.getElementById('errorCliente');
            if (mensaje) {
                caja.textContent = mensaje;
                caja.classList.remove('d-none');
                return false;
            }
            caja.classList.add('d-none');
            return true;
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
