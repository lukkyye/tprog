<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.soap.DtUsuario" %>
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
    String nomC = (String) request.getAttribute("consulta");
    String pacienteNickname = (String) request.getAttribute("nicknamePaciente");
    DtUsuario paciente = (DtUsuario) request.getAttribute("paciente");
    String exito = (String) request.getAttribute("exito");
    String error = (String) request.getAttribute("error");
    String nombre = (String) request.getAttribute("nombre");
    String posologia = (String) request.getAttribute("posologia");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Salud.uy - Recetar Medicamento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="icon" type="image/png" href="<%= ctx %>/img/FAVICON.png">
    <style>
        .detail-label {
            color: #6c757d;
            font-size: 0.95rem;
        }
        .detail-value {
            font-size: 1.05rem;
            overflow-wrap: anywhere;
        }
    </style>
</head>
<body class="min-vh-100 container-fluid bg-body">
    <jsp:include page="/WEB-INF/templates/navbar.jsp" />
    <main class="container-fluid d-flex mt-5 gap-4">
        <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
        <section class="flex-grow-1 pe-4" style="min-width: 0;">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h1>Recetar Medicamento</h1>
            </div>

            <div class="card shadow-sm border-0 rounded-4">
                <div class="card-body p-4 p-md-5">
                    <div class="row g-4 mb-4">
                        <div class="col-md-6">
                            <div class="detail-label">Consulta</div>
                            <div class="detail-value"><%= h(nomC) %></div>
                        </div>
                        <div class="col-md-6">
                            <div class="detail-label">Paciente</div>
                            <div class="detail-value">
                                <%= paciente == null ? "" : h(paciente.getNombre()) + " " + h(paciente.getApellido()) %>
                                (<%= h(pacienteNickname) %>)
                            </div>
                        </div>
                    </div>
                    <hr>

                    <% if (exito != null) { %>
                        <div class="alert alert-success" role="alert">
                            <%= h(exito) %> Puede recetar otro medicamento o terminar.
                        </div>
                    <% } %>
                    <% if (error != null) { %>
                        <div class="alert alert-danger" role="alert">
                            <%= h(error) %>
                        </div>
                    <% } %>

                    <form method="post" action="<%= ctx %>/RecetarMedicamentoServlet">
                        <input type="hidden" name="consulta" value="<%= h(nomC) %>">
                        <input type="hidden" name="nicknamePaciente" value="<%= h(pacienteNickname) %>">

                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre del medicamento</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" maxlength="100" required value="<%= h(nombre) %>">
                        </div>
                        <div class="mb-3">
                            <label for="posologia" class="form-label">Posología</label>
                            <textarea class="form-control" id="posologia" name="posologia" rows="3" maxlength="300" required placeholder="Ej.: 2 comprimidos cada 8 horas por 15 días"><%= h(posologia) %></textarea>
                        </div>
                        <p class="text-muted small">
                            La fecha de indicación es hoy. El paciente podrá solicitar el medicamento durante los 30 días siguientes.
                        </p>
                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-primary" >Recetar medicamento</button>
                            <a class="btn btn-outline-secondary" href="<%= ctx %>/ConsultaConsultaServlet?nombreConsulta=<%= java.net.URLEncoder.encode(nomC, "UTF-8") %>">
                                Terminar
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
