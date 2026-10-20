<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.models.datatypes.DTDiaConsulta" %>
<%!
    private static String h(Object valor) {
        if (valor == null) {
            return "";
        }
        return valor.toString().replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    String ctx = request.getContextPath();
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    boolean esMedico = "Medico".equalsIgnoreCase(tipoUsuario);
    boolean esPaciente = "Paciente".equalsIgnoreCase(tipoUsuario);
    if (esMedico || esPaciente) {
        request.setAttribute("profilePicture",
            new ImageHandlerDelegator().getImg64((String) session.getAttribute("nickname")));
    }
%>
<%
    @SuppressWarnings("unchecked")
    List<DTDiaConsulta> dias = (List<DTDiaConsulta>) request.getAttribute("dias");
    String diaSel = (String) request.getAttribute("diaSel");
    String mensaje = (String) request.getAttribute("mensaje");
    String exito = (String) request.getAttribute("exito");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Alta de Consulta</title>
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

                <h1 class="h2 fw-bold mb-3">Alta de Consulta</h1>
                <p class="text-secondary">
                    Los campos marcados con <span class="text-danger">*</span>
                    son obligatorios. La consulta quedará en estado
                    <strong>Ingresada</strong> hasta que el administrador la acepte.
                </p>

                <% if (exito != null) { %>
                    <div class="alert alert-success" role="alert"><%= h(exito) %></div>
                <% } %>

                <% if (mensaje != null) { %>
                    <div class="alert alert-danger" role="alert"><%= h(mensaje) %></div>
                <% } %>
                <div id="errorCliente" class="alert alert-danger d-none" role="alert"></div>

                <% if (dias == null || dias.isEmpty()) { %>
                    <div class="alert alert-warning" role="alert">
                        Debe existir al menos un día de consulta dado de alta.
                        Podés crearlo desde
                        <a href="<%= ctx %>/AltaDiaConsultaServlet">Alta de Día de Consulta</a>.
                    </div>
                <% } else { %>

                <form id="formConsulta" action="<%= ctx %>/AltaConsultaServlet"
                      method="post" enctype="multipart/form-data"
                      onsubmit="return validarConsulta();">

                    <div class="mb-3">
                        <label for="dia" class="form-label">
                            Día de consulta <span class="text-danger">*</span>
                        </label>
                        <select id="dia" name="dia" class="form-select" required>
                            <option value="" data-dia="">Seleccionar día de consulta</option>
                            <% for (DTDiaConsulta d : dias) { %>
                                <option value="<%= h(d.nomD()) %>"
                                        data-dia="<%= d.dia() %>"
                                        <%= d.nomD().equals(diaSel) ? "selected" : "" %>>
                                    <%= h(d.nomD()) %> (<%= d.dia() %>, <%= d.horaIni() %>)
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="nombre" class="form-label">
                            Nombre de la consulta <span class="text-danger">*</span>
                        </label>
                        <input type="text" id="nombre" name="nombre" class="form-control"
                               required maxlength="100"
                               value="<%= h(request.getAttribute("nombreVal")) %>">
                    </div>

                    <div class="mb-3">
                        <label for="fecha" class="form-label">
                            Fecha <span class="text-danger">*</span>
                        </label>
                        <input type="date" id="fecha" name="fecha" class="form-control" required
                               value="<%= h(request.getAttribute("fechaVal")) %>">
                        <div class="form-text">
                            Debe corresponder al día de la semana del día de consulta elegido.
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-12 col-md-6 mb-3">
                            <label for="sala" class="form-label">
                                Número de sala <span class="text-danger">*</span>
                            </label>
                            <input type="number" id="sala" name="sala" class="form-control"
                                   min="1" step="1" required
                                   value="<%= h(request.getAttribute("salaVal")) %>">
                        </div>
                        <div class="col-12 col-md-6 mb-3">
                            <label for="cantNum" class="form-label">
                                Cantidad de números <span class="text-danger">*</span>
                            </label>
                            <input type="number" id="cantNum" name="cantNum" class="form-control"
                                   min="1" step="1" required
                                   value="<%= h(request.getAttribute("cantVal")) %>">
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="imagen" class="form-label">Imagen (opcional)</label>
                        <input type="file" id="imagen" name="imagen" class="form-control"
                               accept="image/*">
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
        var DIAS = ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'];

        function validarConsulta() {
            var combo = document.getElementById('dia');
            var diaSemana = combo.options[combo.selectedIndex].getAttribute('data-dia');
            var fecha = document.getElementById('fecha').value;
            var sala = Number(document.getElementById('sala').value);
            var cant = Number(document.getElementById('cantNum').value);
            var mensaje = '';

            if (!combo.value) {
                mensaje = 'Selecciona un día de consulta.';
            } else if (!fecha) {
                mensaje = 'La fecha ingresada no es válida.';
            } else if (!Number.isInteger(sala) || sala < 1
                    || !Number.isInteger(cant) || cant < 1) {
                mensaje = 'La sala y la cantidad de números deben ser enteros mayores a 0.';
            } else {
                var p = fecha.split('-');
                var nombreDia = DIAS[new Date(Number(p[0]), Number(p[1]) - 1, Number(p[2])).getDay()];
                if (diaSemana && nombreDia !== diaSemana) {
                    mensaje = 'La fecha elegida cae ' + nombreDia
                            + ' y el día de consulta es ' + diaSemana + '.';
                }
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
