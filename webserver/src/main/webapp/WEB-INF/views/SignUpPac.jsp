<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Salud.uy - Nuevo Paciente</title>
    <link rel="icon" type="image/png" href="/webserver/img/FAVICON.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="../scripts/login_scripts.js" defer></script>
    <link rel="stylesheet" href="../styles/singup_styles.css">
</head>

<body class="registro-paciente">

    <header class="border-bottom bg-white px-4 py-3">
        <a href="../index.html">
            <img src="../img/LOGOGENERAL.png" alt="salud.uy" width="150">
        </a>
    </header>

    <main class="container panel-registro">
        <h1 class="mb-3">Nuevo paciente</h1>

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
                <label for="contrasena" class="form-label">
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
                <input type="text" id="nombre" name="nombre" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="apellido" class="form-label">
                    Apellido <span class="text-danger">*</span>
                </label>
                <input type="text" id="apellido" name="apellido" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="fechaNacimiento" class="form-label">
                    Fecha de nacimiento
                    <span class="text-danger">*</span>
                </label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="documento" class="form-label">
                    Número de documento
                    <span class="text-danger">*</span>
                </label>
                <input type="text" id="documento" name="documento" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="antecedentes" class="form-label">
                    Antecedentes médicos
                    <span class="text-danger">*</span>
                </label>

                <textarea id="antecedentes" name="antecedentes" class="form-control" rows="3" aria-describedby="ayudaAntecedentes"></textarea>

                <div id="ayudaAntecedentes" class="form-text">
                    Un antecedente por linea, si no tenes, no escribas.
                </div>
            </div>

            <div class="mb-4">
                <label for="imagen" class="form-label">
                    Imagen de perfil (opcional)
                </label>
                <input type="file" id="imagen" name="imagen" class="form-control" accept="image/png,jpg">
            </div>

            <div class="d-flex justify-content-end gap-3">
                <a href="signUp.html" class="btn btn-secondary">
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