
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
   
   <%
    //ImageHandlerDelegator img = new ImageHandlerDelegator();

    //String profilePicture = (String) img.getImg64(
    //    (String) session.getAttribute("nickname")
    //);

   // if (profilePicture == null) {
       //profilePicture = request.getContextPath() + "/img/VISITANTE.png";
    //}
   String profilePicture = request.getContextPath() + "/img/VISITANTE.png";
%>

<header class="mb-2">
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">
 <!-- Boton hamburguesa -->
            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#menuMovil"
                    aria-controls="menuMovil"
                    aria-expanded="false"
                    aria-label="Abrir menu">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <!-- Logo -->
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/img/LOGOGENERAL.png"
                     width="120px" alt="Salud.uy">
            </a>
            
           <!-- Foto de perfil y nombre de usuario -->
<div class="d-flex align-items-center gap-2">
    <img src="<%= profilePicture %>"
         alt="Foto de perfil"
         class="rounded-circle border"
         style="width: 38px; height: 38px; object-fit: cover;">

    <% if (session.getAttribute("nickname") != null) { %>
        <span class="fw-bold">
            <%= session.getAttribute("nickname") %>
        </span>
    <% } %>
</div>

           

            <!-- Opciones del menu -->
            <div class="collapse navbar-collapse" id="menuMovil">
                <ul class="navbar-nav ms-auto">

                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Valorar consultas
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/movil/medicamentos">
                            Solicitar medicamentos
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                            Cerrar sesion
                        </a>
                    </li>

                </ul>
            </div>
        </div>
    </nav>
    <hr class="text-black m-0">
</header>
