<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.models.datatypes.DTUsuario" %>
<%@ page import="com.models.datatypes.DTPaciente" %>
<%@ page import="com.models.datatypes.DTMedico" %>

<%
    DTUsuario usuario = (DTUsuario) request.getAttribute("usuario");
    DTPaciente paciente = (DTPaciente) request.getAttribute("datosPaciente");
    DTMedico medico = (DTMedico) request.getAttribute("datosMedico");
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mis datos</title>
</head>
<body>
    <h1>Mis datos</h1>

    <% if (usuario != null) { %>
        <p><strong>Nickname:</strong> <%= usuario.nickname() %></p>
        <p><strong>Nombre:</strong> <%= usuario.nombre() %></p>
        <p><strong>Apellido:</strong> <%= usuario.apellido() %></p>
        <p><strong>Correo:</strong> <%= usuario.correo() %></p>
    <% } %>

    <% if (paciente != null) { %>
        <h2>Datos de paciente</h2>
        <p><strong>Documento:</strong> <%= paciente.doc() %></p>
        <p><strong>Fecha de nacimiento:</strong> <%= paciente.fechaNac() %></p>
        <p><strong>Fecha de ingreso:</strong> <%= paciente.fechaIngreso() %></p>
        <p><strong>Antecedentes de salud:</strong> <%= paciente.antSalud() %></p>
    <% } %>

    <% if (medico != null) { %>
        <h2>Datos de médico</h2>
        <p><strong>Número de registro:</strong> <%= medico.nroReg() %></p>
        <p><strong>Mes de licencia:</strong> <%= medico.mesLic() %></p>
        <p><strong>Porcentaje de costo:</strong> <%= medico.porcentajeCost() %></p>
        <p><strong>Enlace:</strong> <%= medico.link() %></p>
    <% } %>

    <a href="<%= request.getContextPath() %>/home">Volver al inicio</a>
</body>
</html>