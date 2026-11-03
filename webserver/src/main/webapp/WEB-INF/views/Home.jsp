<%@ page import="java.util.ArrayList" %>
<%@ page import="com.soap.DtDiaConsulta" %>
<%@ page import="com.gateway.delegators.ImageHandlerDelegator" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
        rel="stylesheet">

    <title>Salud.uy - Pagina principal</title>

    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/FAVICON.png">

    <style>

		  .bienvenida-movil {
		    min-height: calc(100vh - 100px);
		    display: flex;
		    justify-content: center;
		    align-items: flex-start;
		    text-align: center;
		
		    background-image: url('${pageContext.request.contextPath}/img/home-movil.png');
		    background-size: 100% 100%;
		    background-position: center;
		    background-repeat: no-repeat;
		}

		  @media (max-width: 991.98px) {
		
		    .bienvenida-movil {
		        width: 100%;
		        min-height: calc(100vh - 100px);
		        margin: 0;
		        padding: 0;
		        border-radius: 0;
		    }
		
		    .bienvenida-movil h2 {
		        margin-top: 150px;
		    }
		
		    main:has(.bienvenida-movil) {
		        display: block !important;
		        margin-top: 0 !important;
		        padding: 0 !important;
		    }
		}
    
		@media (max-width: 991.98px) {
			.menu-lateral-escritorio {
		        display: none !important;
		    }
		}
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
</head>

<body class="min-vh-100 container-fluid bg-body">

    <%
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    %>
    <%
    	String userAgent = request.getHeader("User-Agent");
    	boolean esMovil = userAgent != null &&
        	userAgent.matches("(?i).*android.*|.*iphone.*|.*ipad.*|.*mobile.*");
	%>

						<!-- NAVBAR -->
						<div class="d-none d-lg-block">
						    <% if ("Paciente".equals(tipoUsuario) || "Medico".equals(tipoUsuario)) { %>
						        <jsp:include page="/WEB-INF/templates/navbar.jsp" />
						    <% } else { %>
						        <jsp:include page="/WEB-INF/templates/navbarVisitante.jsp" />
						    <% } %>
						</div>
						
						<div class="d-block d-lg-none">
						    <jsp:include page="/WEB-INF/templates/navbarMovil.jsp" />
						</div>

    <!-- CONTENIDO PRINCIPAL -->
	<main class="container-fluid d-flex mt-5 gap-4">
	
			<% if (!esMovil) { %>

    <% if ("Paciente".equals(tipoUsuario)) { %>
        <jsp:include page="/WEB-INF/templates/menuLateralPaciente.jsp" />
    <% } else if ("Medico".equals(tipoUsuario)) { %>
        <jsp:include page="/WEB-INF/templates/menuLateralMedico.jsp" />
    <% } else { %>
        <jsp:include page="/WEB-INF/templates/menuLateralVisitante.jsp" />
    <% } %>

<% } %>


	<% if (esMovil) { %>

	    <div class="bienvenida-movil">
	    	<h2 class="fw-bold">¡Bienvenido/a a Salud.uy!</h2>
	    </div>
    <% } else { %>
        <!-- DIAS DE CONSULTA -->
        <div class="col-md-9 pe-5 d-flex flex-column gap-4">
 
         <%
    		ImageHandlerDelegator img = new ImageHandlerDelegator();

   			List<DtDiaConsulta> array = (List<DtDiaConsulta>) request.getAttribute("dtDiasConsulta");

    		if (array != null && !array.isEmpty()) {

        		for (DtDiaConsulta dtdc : array) {

            		String src = img.getImg64(dtdc.getNomD());

            		if (src == null) {
        				src = request.getContextPath() + "/img/LOGOGENERAL.png";
            		}
		%>

                <div class="card shadow rounded-4 border-0 bg-light p-4">

                    <div class="d-flex align-items-center mb-3">

                        <div class="col-3 text-center">
                            <img
                                style="max-height: auto; width: 128px; object-fit: contain;"
                                src="<%= src %>"
                                alt="Imagen de dia consulta">
                        </div>

                        <div class="col-9 ps-3">
                            <h4 class="m-0 fw-bold">
                                <%= dtdc.getNomD() %>
                            </h4>

                            <p class="mb-0 text-muted mt-2">
                                <%= dtdc.getDescD() %>
                            </p>
                        </div>

                    </div>

                    <div class="d-flex justify-content-center gap-2 pt-2 border-top">

                        <a href="${pageContext.request.contextPath}/ConsultaDiaConsultaServlet?nombreDia=<%= java.net.URLEncoder.encode(dtdc.getNomD(), "UTF-8") %>"
                           class="btn btn-outline-primary">
                            Ver Detalle
                        </a>

                        <a href="${pageContext.request.contextPath}/ConsultasDiaServlet?nombreDia=<%= java.net.URLEncoder.encode(dtdc.getNomD(), "UTF-8") %>"
                           class="btn btn-primary">
                            Ver Consultas Asociadas
                        </a>

                    </div>

                </div>

            <%
                    }

                } else {
            %>

                <!-- MENSAJE CUANDO NO HAY DIAS DE CONSULTA -->
                <section class="bg-light rounded-4 shadow p-4 p-sm-5 w-100">

                    <h2 class="mb-4">Días de Consulta</h2>

                    <div class="alert alert-info text-center mb-0">
                        No hay días de consulta disponibles.
                    </div>

                </section>

            <%
                }
            %>

        </div>
       	<% } %>

    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>