<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Salud.uy - Iniciar Sesión</title>
    <link rel="icon" type="image/png" href="/websever/img/FAVICON.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
	
	
    <style>
		    @media (max-width: 767.98px) {
		    main {
		        min-height: calc(100vh - 80px);
		        display: flex;
		        align-items: center;
		        justify-content: center;
		    }
		
		    main .row {
		        width: 100%;
		        margin: 0;
		    }
		}
    
        body {
        
        	
            min-height: 100vh;
            margin: 0;
            background-color: #eef3f7;
        }

        .tarjeta-login {
            width: 100%;
            max-width: 1000px;
            border-radius: 24px;
            overflow: hidden;
            box-shadow: 0 12px 35px rgba(0, 0, 0, 0.15);
        }

        .panel-logo {
            background-color: #e4f3fc;
            padding: 64px 40px;
        }

        .logo-grande {
            width: 100%;
            max-width: 360px;
            height: auto;
        }

        .panel-formulario {
            background-color: #164a7b;
            color: #ffffff;
            padding: 48px;
        }

        .panel-formulario .form-control {
            padding: 12px 16px;
            border-radius: 10px;
        }

        .boton-entrar {
            background-color: #55bceb;
            border-color: #55bceb;
            color: #102f4d;
            font-weight: 600;
        }

        .boton-entrar:hover {
            background-color: #83d0f2;
            border-color: #83d0f2;
            color: #102f4d;
        }

        @media (max-width: 767px) {
            .panel-logo {
                padding: 32px;
            }

            .logo-grande {
                max-width: 240px;
            }

            .panel-formulario {
                padding: 32px 24px;
            }
        }
    </style>
</head>
 <%
    	String userAgent = request.getHeader("User-Agent");
    	boolean esMovil = userAgent != null &&
        	userAgent.matches("(?i).*android.*|.*iphone.*|.*ipad.*|.*mobile.*");
	%>

<body class="d-flex flex-column">
 	<% if (!esMovil) { %>
		<header class="bg-white border-bottom px-4 py-3">
		    <nav class="d-flex justify-content-between align-items-center">
		        <a href="/webserver/home">
		            <img src="/webserver/img/LOGOGENERAL.png"
		                 alt="salud.uy"
		                 width="120">
		        </a>
		
		    </nav>
		</header>
	<% } %>
    <main class="container py-5">
        <div class="tarjeta-login mx-auto">
            <div class="row g-0">

                <div class="col-md-6 panel-logo d-flex
                            flex-column align-items-center
                            justify-content-center text-center">

                    <a href="/home"
                       class="d-block w-100">
                        <img src="/webserver/img/LOGOGENERAL.png"
                             alt="salud.uy"
                             class="logo-grande">
                    </a>

                </div>

                <div class="col-md-6 panel-formulario">
                    <h1 class="h2 fw-bold mb-3">
                        Iniciar sesión
                    </h1>

					<% if (request.getAttribute("error") != null) { %>
						<div class="alert alert-danger fw-bold" role="alert">
                	<%= request.getAttribute("error") %>
            			</div>
        			<% } %>
                    <form method="POST" action="${pageContext.request.contextPath}/login">

                        <div class="mb-3">
                            <label for="nickname" class="form-label">
                                Nickname
                            </label>

                            <input type="text"
                                   id="nickname"
                                   name="nickname"
                                   class="form-control"
                                   placeholder="Tu nickname"
                                   autocomplete="username"
                                   required>
                        </div>

                        <div class="mb-4">
                            <label for="contrasena" class="form-label">
                                Contraseña
                            </label>

                            <input type="password"
                                   id="contrasena"
                                   name="password"
                                   class="form-control"
                                   placeholder="Tu contraseña"
                                   autocomplete="current-password"
                                   required>
                        </div>

                        <button type="submit"
                                class="btn boton-entrar btn-lg w-100">
                            Entrar
                        </button>

                        <a href="/webserver/home"
                           class="btn btn-outline-light w-100 mt-3">
                            Cancelar
                        </a>
                    </form>

                    <p class="text-center mt-4 mb-0">
                        ¿No tenés cuenta?
                        <a href="/webserver/signup"
                           class="text-white fw-bold">
                            Registrate
                        </a>
                    </p>
                </div>

            </div>
        </div>
    </main>

</body>
</html>