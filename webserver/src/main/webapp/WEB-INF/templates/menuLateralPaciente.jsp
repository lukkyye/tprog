<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="com.gateway.delegators.ClinicControllerDelegator" %>

<%
    ClinicControllerDelegator clinicController =
        new ClinicControllerDelegator();
    Set<String> especialidades = clinicController.listarEspecialidades();
%>

<aside class="d-flex flex-column gap-4 ms-5 p-0 pe-2 sidebar-scroll"
       style="flex: 1 1 0%; max-width: 25%; max-height: 75vh; overflow-y: auto;">

    <!-- Dar de Alta -->
    <div class="card shadow-sm rounded-4">
        <div class="card-body">
            <h5 class="text-center mb-3">Dar de Alta</h5>
            <hr>

            <ul class="list-unstyled d-flex flex-column gap-3 mb-0">
                <li>
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/AltaDiaConsultaServlet">
                        Dia de Consulta
                    </a>
                </li>
                <li>
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/AltaConsultaServlet">
                        Consulta
                    </a>
                </li>
                <li>
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/AltaClinicaServlet">
                        Clinica
                    </a>
                </li>
                <li>
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/AltaBeneficioAnualServlet">
                        Beneficio Anual
                    </a>
                </li>
                <li>
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/AltaEspecialidadServlet">
                        Especialidad
                    </a>
                </li>
            </ul>
        </div>
    </div>
    
    <!-- Especialidades -->
    <div class="card shadow-sm rounded-4">
        <div class="card-body">
            <h5 class="text-center mb-3">Especialidades</h5>
            <hr>

            <ul class="list-unstyled d-flex flex-column gap-3 mb-0">
                <% if (especialidades != null && !especialidades.isEmpty()) {
                    for (String especialidad : especialidades) { %>
                        <li>
                            <a class="text-muted text-decoration-none"
                               href="${pageContext.request.contextPath}/ListarConsultasServlet?especialidad=<%= java.net.URLEncoder.encode(especialidad, "UTF-8") %>">
                                <%= especialidad %>
                            </a>
                        </li>
                        <hr class="w-75 mx-auto">
                <%  }
                   } else { %>
                    <li class="text-muted">No hay especialidades disponibles.</li>
                <% } %>
            </ul>
        </div>
    </div>
    
    <!-- Consultar -->
    <div class="card shadow-sm rounded-4">
        <div class="card-body">
            <h5 class="text-center mb-3">Consultar</h5>
            <hr>

            <ul class="list-unstyled d-flex flex-column gap-3 mb-0">
                <li>
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/UsuariosMedico">
                        Usuarios
                    </a>
                </li>
                <li>
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/ClinicasMedico">
                        Clinicas
                    </a>
                </li>
                <li>
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/MisConsultasServlet">
                        Mis Consultas
                    </a>
                </li>
            </ul>
        </div>
    </div>

</aside>