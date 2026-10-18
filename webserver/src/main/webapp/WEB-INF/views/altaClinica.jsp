
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Salud.uy - Alta de Clinica</title>

    <link rel="icon" type="image/png"
          href="/webserver/img/FAVICON.png">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/styles/singup_styles.css">
</head>

<body class="alta-clinica">

    <jsp:include page="/WEB-INF/templates/navbar.jsp"></jsp:include>

    <main class="container panel-registro">
        <h1 class="mb-3">Alta de Clinica</h1>

        <p>
            Los campos marcados con
            <span class="text-danger">*</span>
            son obligatorios.
        </p>

        <% if (request.getAttribute("mensaje") != null) { %>
            <div class="alert alert-info" role="alert">
                <%= request.getAttribute("mensaje") %>
            </div>
        <% } %>

        <form action="${pageContext.request.contextPath}/AltaClinicaServlet"
              method="post"
              enctype="multipart/form-data">

            <h2 class="h5 mt-4 mb-3">Datos de la clinica</h2>

            <div class="mb-3">
                <label for="nombreClinica" class="form-label">
                    Nombre de la clinica <span class="text-danger">*</span>
                </label>

                <input type="text" id="nombreClinica" name="nombreClinica"
                       class="form-control" required>

                <div class="form-text">
                    El nombre debe ser unico en la plataforma.
                </div>
            </div>

            <div class="mb-3">
                <label for="descripcion" class="form-label">
                    Descripcion <span class="text-danger">*</span>
                </label>

                <textarea id="descripcion" name="descripcion"
                          class="form-control" rows="3" required></textarea>
            </div>

            <div class="mb-3">
                <label for="direccion" class="form-label">
                    Direccion <span class="text-danger">*</span>
                </label>

                <input type="text" id="direccion" name="direccion"
                       class="form-control"
                       placeholder="Calle, numero y localidad" required>
            </div>

            <h2 class="h5 mt-4 mb-3">Costos</h2>

            <div class="mb-3">
                <label for="costoTicket" class="form-label">
                    Costo del ticket de medicamento
                    <span class="text-danger">*</span>
                </label>

                <input type="number" id="costoTicket" name="costoTicket"
                       class="form-control" min="0" step="0.01" required>
            </div>

            <div class="mb-3">
                <label for="costoOrden" class="form-label">
                    Costo de la orden de consulta medica
                    <span class="text-danger">*</span>
                </label>

                <input type="number" id="costoOrden" name="costoOrden"
                       class="form-control" min="0" step="0.01" required>
            </div>

            <h2 class="h5 mt-4 mb-3">Informacion adicional</h2>

            <div class="mb-3">
                <label for="sitioWeb" class="form-label">
                    Sitio web (opcional)
                </label>

                <input type="url" id="sitioWeb" name="sitioWeb"
                       class="form-control"
                       placeholder="https://www.ejemplo.com">
            </div>
            
            <div class="mb-4">
				<label for="imagen" class="form-label">
					Imagen de la clinica (opcional)
				</label>

    			<input type="file"
           			id="imagen"
           			name="imagen"
           			class="form-control"
					accept="image/*">
			</div>

            <div class="d-flex justify-content-end gap-3 mt-4">
                <a href="${pageContext.request.contextPath}/home/indexMedico.html"
                   class="btn btn-secondary">
                    Cancelar
                </a>

                <button type="submit" class="btn btn-primary">
                    Registrar clinica
                </button>
            </div>

        </form>
    </main>

</body>
</html>
