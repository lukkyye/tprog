<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Salud.uy - Nuevo Médico</title>
    <link rel="icon" type="image/png" href="/webserver/img/FAVICON.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="../scripts/login_scripts.js" defer> </script>
    <link rel="stylesheet" href="../styles/singup_styles.css">
</head>

<body class="registro-medico">

    <header class="border-bottom bg-white px-4 py-3">
        <a href="../index.html">
            <img src="../img/LOGOGENERAL.png" alt="salud.uy" width="150">
        </a>
    </header>

    <main class="container panel-registro">
        <h1 class="mb-3">Nuevo médico</h1>

        <p>
            Los campos marcados con
            <span class="text-danger">*</span>
            son obligatorios.
        </p>
		<% if (request.getAttribute("success")!=null) { %>
            <div class="alert alert-info" role="alert">
                <%= request.getAttribute("success") %>
            </div>
        <% } %>
        <form method="POST" enctype="multipart/form-data">

            <h2 class="h5 mt-4 mb-3">Crear nueva cuenta</h2>

            <div class="mb-3">
                <label for="nickname" class="form-label">
                    Nickname <span class="text-danger">*</span>
                </label>
                <input type="text" id="nickname" name="nickname" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="correo" class="form-label">
                    Correo electrónico
                    <span class="text-danger">*</span>
                </label>
                <input type="email" id="correo" name="correo" class="form-control" placeholder="tuemail@ejemplo.com" required>
            </div>

            <div class="mb-3">
                <label for="contrasena"  class="form-label">
                    Contraseña <span class="text-danger">*</span>
                </label>
                <input type="password" id="contrasena" name="contrasena" class="form-control" autocomplete="new-password" required>
            </div>

            <div class="mb-3">
                <label for="confirmacion" class="form-label">
                    Confirmar contraseña
                    <span class="text-danger">*</span>
                </label>
                <input type="password" id="confirmacion" name="confirmacion" class="form-control" autocomplete="new-password" required>
            </div>

            <h2 class="h5 mt-4 mb-3">Datos personales</h2>

            <div class="mb-3">
                <label for="nombre" class="form-label">
                    Nombre <span class="text-danger">*</span>
                </label>
                <input type="text" id="nombre" name="nombre"
                       class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="apellido" class="form-label">
                    Apellido <span class="text-danger">*</span>
                </label>
                <input type="text" id="apellido" name="apellido" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="numeroRegistro" class="form-label">
                    Número de registro
                    <span class="text-danger">*</span>
                </label>
                <input type="text" id="numeroRegistro" name="numeroRegistro" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="mesLicenciresponsea" class="form-label">
                    Mes de licencia <span class="text-danger">*</span>
                </label>

                <select id="mesLicencia" name="mesLicencia" class="form-select" required>
                    <option value="" selected disabled>
                        Seleccioná un mes
                    </option>
                    <option value="Enero">Enero</option>
                    <option value="Febrero">Febrero</option>
                    <option value="Marzo">Marzo</option>
                    <option value="Abril">Abril</option>
                    <option value="Mayo">Mayo</option>
                    <option value="Junio">Junio</option>
                    <option value="Julio">Julio</option>
                    <option value="Agosto">Agosto</option>
                    <option value="Septiembre">Septiembre</option>
                    <option value="Octubre">Octubre</option>
                    <option value="Noviembre">Noviembre</option>
                    <option value="Diciembre">Diciembre</option>
                </select>
            </div>

            <fieldset class="mb-3">
                <legend class="fs-6">
                    Especialidades <span class="text-danger">*</span>
                </legend>

                <p class="form-text mt-0">
                    Seleccioná al menos una especialidad.
                </p>

                <div class="row g-2">
                    <% 
                    ArrayList<String> specs = (ArrayList<String>)request.getAttribute("specs");
                    if (specs!=null) {
                    	for (String s: specs) {%>
                    	<div class="col-sm-6">
                        	<div class="form-check">
                            	<input class="form-check-input" type="checkbox" id="<%=s %>" name="specs" value="<%=s %>">
                            	<label class="form-check-label" for="<%=s %>">
                                	<%=s %>
                            	</label>
                       	 	</div>
                    	</div>
                    <%}};%>
                </div>
            </fieldset>

            <div class="mb-3">
                <label for="porcentajeAdicional" class="form-label">
                    Porcentaje adicional para el costo de consultas
                    <span class="text-danger">*</span>
                </label>

                <div class="input-group">
                    <input type="number" id="porcentajeAdicional" name="porcentajeAdicional" class="form-control" min="0" step="1" required>
                    <span class="input-group-text">%</span>
                </div>
            </div>

            <div class="mb-3">
                <label for="sitioWeb" class="form-label">
                    Sitio web (opcional)
                </label>
                <input type="url" id="sitioWeb" name="sitioWeb" class="form-control" placeholder="https://www.ejemplo.com">
            </div>

            <div class="mb-4">
                <label for="imagen" class="form-label">
                    Imagen de perfil (opcional)
                </label>
                <input type="file" id="imagen" name="imagen" class="form-control" accept="image/*">
            </div>

            <div class="d-flex justify-content-end gap-3">
			<a href="${pageContext.request.contextPath}/home"
   				class="btn btn-secondary">
    			Volver
			</a>

                <button type="submit" id="ok" class="btn btn-primary">
                    Registrarse
                </button>
            </div>

        </form>
    </main>

</body>
</html>