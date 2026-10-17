<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="com.gateway.delegators.ClinicControllerDelegator" %>

<%
    ClinicControllerDelegator clinicController =
        new ClinicControllerDelegator();
    Set<String> especialidades = clinicController.listarEspecialidades();
%>

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

<aside class="d-flex flex-column gap-4 ms-5 p-0 pe-2 sidebar-scroll"
       style="flex: 1 1 0%; max-width: 25%; max-height: 75vh; overflow-y: auto;">

    <!-- Especialidades -->
    <div class="container d-flex p-0 flex-column rounded-4 bg-light shadow
                justify-content-center align-items-center">
        <div class="pt-2">
            <h5 class="pt-2">Especialidades</h5>
        </div>

        <div class="m-0 p-0 w-100">
            <ul class="nav nav-link nav-fill d-flex flex-column gap-3
                       mx-0 mt-0 pb-3" style="width: 100%;">

                <% if (especialidades != null && !especialidades.isEmpty()) {
                    for (String especialidad : especialidades) { %>

                    <li class="nav-item hoverable w-100">
                        <a class="text-muted text-decoration-none"
                           href="${pageContext.request.contextPath}/ListarConsultasDiaAprobadasVisitante?especialidad=<%= java.net.URLEncoder.encode(especialidad, "UTF-8") %>"
                           style="font-size: 11pt;">
                            <%= especialidad %>
                        </a>
                    </li>

                    <hr class="w-75" style="margin: auto;">

                <%  }
                   } else { %>

                    <li class="nav-item text-muted">
                        No hay especialidades disponibles.
                    </li>

                <% } %>
            </ul>
        </div>
    </div>

    <!-- Consultar -->
    <div class="container d-flex p-0 flex-column rounded-4 bg-light shadow
                justify-content-center align-items-center mb-2">
        <div class="pt-2">
            <h5 class="pt-2">Consultar</h5>
        </div>

        <div class="m-0 p-0 w-100">
            <ul class="nav nav-link nav-fill d-flex flex-column gap-3
                       mx-0 mt-0 pb-3" style="width: 100%;">

                <li class="nav-item hoverable w-100">
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/UsuariosVisitante"
                       style="font-size: 11pt;">
                        Usuarios
                    </a>
                </li>

                <hr class="w-75" style="margin: auto;">

                <li class="nav-item hoverable w-100">
                    <a class="text-muted text-decoration-none"
                       href="${pageContext.request.contextPath}/ClinicasVisitante"
                       style="font-size: 11pt;">
                        Clinicas
                    </a>
                </li>
            </ul>
        </div>
    </div>

</aside>