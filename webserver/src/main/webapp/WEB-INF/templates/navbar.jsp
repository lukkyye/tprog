<%@page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<header class="mb-2">
        <nav class="navbar navbar-expand-lg" style="width: 100%; height: 5%;">
            <div class="container-fluid d-flex justify-content-between align-items-center">
                <a class="navbar-brand" href="/webserver/home"><img src="/webserver/img/LOGOGENERAL.png" width="120px"></a>
                
                <form class="d-flex mx-auto" style="max-width: 500px; width: 100%;">
                    <input class="form-control me-2" type="search" placeholder="Buscar..." aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Buscar</button>
                </form>
                
                <div class="d-flex flex-row user-section gap-2 align-items-center">
                    <a id="cerrarSesion"
   						href="${pageContext.request.contextPath}/logout">
                            Cerrar sesión
                        </a>
					<a href="${pageContext.request.contextPath}/misdatos"
   						class="btn btn-primary">
    					Mis Datos
					</a>
				
				<%
					ImageHandlerDelegator img = new ImageHandlerDelegator();
					String profilePicture = (String) img.getImg64((String)session.getAttribute("nickname"));
					if (profilePicture==null){
						profilePicture = "/webserver/img/VISITANTE.png";
					}			
				%>
					<div class="d-flex align-items-center gap-1">
	    					<img style="height: 38px;"
	         				class="rounded-circle border"
	         				src="<%=profilePicture%>">
	
	    				<a href="${pageContext.request.contextPath}/misdatos"
	       					class="text-decoration-none fw-bold text-dark"
	       					style="font-size: 10pt;">
	        				${sessionScope.nickname}
	    				</a>
					</div>
                    </div>
                </div>
        </nav>
        <script>
    document.getElementById("cerrarSesion").addEventListener("click", function(e) {
        e.preventDefault();

        const pantallaChica = window.innerWidth < 992;

        window.location.href =
            "${pageContext.request.contextPath}/logout?pantallaChica=" + pantallaChica;
    });
</script>
        
        <hr class="text-black m-0">
</header>