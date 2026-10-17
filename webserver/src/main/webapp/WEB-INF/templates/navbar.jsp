<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<header class="mb-2">
        <nav class="navbar navbar-expand-lg" style="width: 100%; height: 5%;">
            <div class="container-fluid d-flex justify-content-between align-items-center">
                <a class="navbar-brand" href="/home"><img src="/webserver/img/LOGOGENERAL.png" width="120px"></a>
                
                <form class="d-flex mx-auto" style="max-width: 500px; width: 100%;">
                    <input class="form-control me-2" type="search" placeholder="Buscar..." aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Buscar</button>
                </form>
                
                <div class="d-flex flex-row user-section gap-2 align-items-center">
                    <a href="../index.html" class="btn btn-secondary">Cerrar Sesión</a>
                    <a href="/home/my_profile" class="btn btn-primary">Mis Datos</a>
                    <div class="d-flex align-items-center gap-1">
                   		<%String nickname = (String) request.getAttribute("nickname");%>
                        <img style="height: 38px;" class="rounded-circle border" src="<%=(new com.gateway.delegators.ImageHandlerDelegator()).get_img64(nickname)%>" alt="Perfil">
                        <a href="../users/HAYQHACERLO.html" class="text-decoration-none fw-bold text-dark" style="font-size: 10pt;"></a>
                    </div>
                </div>
            </div>
        </nav>
        <hr class="text-black m-0">
</header>