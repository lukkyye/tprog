
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header class="mb-2">
    <nav class="navbar navbar-light bg-light">
        <div class="container-fluid">

            <!-- Logo -->
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <img src="${pageContext.request.contextPath}/img/LOGOGENERAL.png"
                     width="120px" alt="Salud.uy">
            </a>

            <!-- Boton hamburguesa -->
            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#menuMovil"
                    aria-controls="menuMovil"
                    aria-expanded="false"
                    aria-label="Abrir menu">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Opciones del menu -->
            <div class="collapse navbar-collapse" id="menuMovil">
                <ul class="navbar-nav ms-auto">

                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Valorar consultas
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">
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
