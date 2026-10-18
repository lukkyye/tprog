<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <title>Salud.uy - Pagina principal</title>
    <link rel="icon" type="image/png" href="/webserver/img/FAVICON.png">
    <style>
        .hoverable:hover {
            background-color: rgba(180, 180, 180, 0.8);
        }
        /* Personalización de la barra de scroll para la columna lateral */
        .sidebar-scroll::-webkit-scrollbar {
            width: 6px;
        }
        .sidebar-scroll::-webkit-scrollbar-thumb {
            background-color: rgba(0, 0, 0, 0.2);
            border-radius: 4px;
        }
    </style>
</head>
<body class="min-vh-100 container-fluid bg-body vh-100">

	<%
		String tipoUsuario = (String) session.getAttribute("tipoUsuario");
	%>
	
	<% if ("Paciente".equals(tipoUsuario) || "Medico".equals(tipoUsuario)) { %>
    	<jsp:include page="/WEB-INF/templates/navbar.jsp" />
	<% } else { %>
	    <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
	<% } %>
	

    <main class="container-fluid d-flex h-75 mt-5 gap-4">

		
		
		<% if ("Paciente".equals(tipoUsuario)) { %>
		    <jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
		<% } else if ("Medico".equals(tipoUsuario)) { %>
		    <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
		<% } else { %>
		    <jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
		<% } %>
        <div class="col-md-9 pe-5 d-flex flex-column gap-4">
            
            <div class="card shadow rounded-4 border-0 bg-light p-4">
                <div class="d-flex align-items-center mb-3">
                    <div class="col-3 text-center">
                        <img style="max-height: 100px; width: auto; object-fit: contain;" src="./img/CARDIOLOGIA.png" alt="Cardiología">
                    </div>
                    <div class="col-9 ps-3">
                        <h4 class="m-0 fw-bold">Cardiología - Lunes</h4>
                        <p class="mb-0 text-muted mt-2">Atención cardiológica semanal para los días lunes.</p>
                    </div>
                </div>
                <div class="d-flex justify-content-center gap-2 pt-2 border-top">
                    <a href="./consultas/consultaDiaConsultaVisitante.html" class="btn btn-outline-primary">Ver Detalle</a>
                    <a href="./consultas/listaConsultasDiaAprobadaVisitante.html" class="btn btn-primary">Ver Consultas Asociadas</a>
                </div>
            </div>
            
            <div class="card shadow rounded-4 border-0 bg-light p-4">
                <div class="d-flex align-items-center mb-3">
                    <div class="col-3 text-center">
                        <img style="max-height: 100px; width: auto; object-fit: contain;" src="./img/PEDIATRIA.png" alt="Pediatría">
                    </div>
                    <div class="col-9 ps-3">
                        <h4 class="m-0 fw-bold">Pediatría - Martes</h4>
                        <p class="mb-0 text-muted mt-2">Atención pediátrica semanal para los días martes.</p>
                    </div>
                </div>
                <div class="d-flex justify-content-center gap-2 pt-2 border-top">
                    <a href="./consultas/consultaDiaConsultaVisitante.html" class="btn btn-outline-primary">Ver Detalle</a>
                    <a href="./consultas/listaConsultasDiaAprobadaVisitante.html" class="btn btn-primary">Ver Consultas Asociadas</a>
                </div>
            </div>
            
            <div class="card shadow rounded-4 border-0 bg-light p-4 mb-4">
                <div class="d-flex align-items-center mb-3">
                    <div class="col-3 text-center">
                        <img style="max-height: 100px; width: auto; object-fit: contain;" src="./img/MEDICINAGENERAL.png" alt="Medicina General">
                    </div>
                    <div class="col-9 ps-3">
                        <h4 class="m-0 fw-bold">Medicina General Central - Miércoles</h4>
                        <p class="mb-0 text-muted mt-2">Consulta general semanal para los días miércoles.</p>
                    </div>
                </div>
                <div class="d-flex justify-content-center gap-2 pt-2 border-top">
                    <a href="./consultas/consultaDiaConsultaVisitante.html" class="btn btn-outline-primary">Ver Detalle</a>
                    <a href="./consultas/listaConsultasDiaAprobadaVisitante.html" class="btn btn-primary">Ver Consultas Asociadas</a>
                </div>
            </div>
        </div>
    </main>
</body>
</html>