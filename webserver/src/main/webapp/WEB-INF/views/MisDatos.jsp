<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.soap.DtUsuario" %>
<%@ page import="com.soap.DtPaciente" %>
<%@ page import="com.soap.DtMedico" %>

<%
DtUsuario usuario = (DtUsuario) request.getAttribute("usuario");
    DtPaciente paciente = (DtPaciente) request.getAttribute("datosPaciente");
    DtMedico medico = (DtMedico) request.getAttribute("datosMedico");
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
        <p><strong>Nickname:</strong> <%= usuario.getNickname() %></p>
        <p><strong>Nombre:</strong> <%= usuario.getNombre() %></p>
        <p><strong>Apellido:</strong> <%= usuario.getApellido() %></p>
        <p><strong>Correo:</strong> <%= usuario.getCorreo() %></p>
    <% } %>

    <% if (paciente != null) { %>
        <h2>Datos de paciente</h2>
        <p><strong>Documento:</strong> <%= paciente.getDoc() %></p>
        <p><strong>Fecha de nacimiento:</strong> <%= paciente.getFechaNac() %></p>
        <p><strong>Fecha de ingreso:</strong> <%= paciente.getFechaIngreso() %></p>
        <p><strong>Antecedentes de salud:</strong> <%= paciente.getAntSalud() %></p>
    <% } %>

    <% if (medico != null) { %>
        <h2>Datos de médico</h2>
        <p><strong>Número de registro:</strong> <%= medico.getNroReg() %></p>
        <p><strong>Mes de licencia:</strong> <%= medico.getMesLic() %></p>
        <p><strong>Porcentaje de costo:</strong> <%= medico.getPorcentajeCost() %></p>
        <p><strong>Enlace:</strong> <%= medico.getLink() %></p>
    <% } %>

    <a href="<%= request.getContextPath() %>/home">Volver al inicio</a>
</body>
</html>