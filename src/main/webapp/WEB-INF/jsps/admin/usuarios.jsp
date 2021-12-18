<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="cabecera.jsp"></jsp:include>

<div class="container">

Listado de usuarios de la tienda:<br>
<c:forEach var="usuario" items="${info}">
	<div style="margin: 20px; padding: 10px; background-color: lightblue;">
		nombre: ${usuario.nombre}<br>
		email: ${usuario.email}<br>
		<c:if test="${usuario.alta == true}">
			<a href="darBajaUsuario?id=${usuario.id}">dar de baja</a>
		</c:if>
		<c:if test="${usuario.alta == false}">
			<a href="darAltaUsuario?id=${usuario.id}">dar de alta</a>
		</c:if>
<%-- 		<a href="borrarUsuario?id=${usuario.id}" onclick="return confirm('¿seguro?')">borrar</a> --%>
<%-- 		<a href="editarUsuario?id=${usuario.id}">editar</a> --%>
	</div>
</c:forEach>

</div>

</body>
</html>