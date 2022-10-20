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

<a href="registrarCategoria" class="btn btn-primary">nueva categoria</a><br>

Listado de categorias de la tienda:<br>
<c:forEach var="categoria" items="${info}">
	<div style="margin: 20px; padding: 10px; background-color: lightblue;">
		nombre: ${categoria.nombre}<br>
		descripcion: ${categoria.descripcion}<br>
		<c:if test="${categoria.alta == true}">
			<a href="darBajaCategoria?id=${categoria.id}">dar de baja</a>
		</c:if>
		<c:if test="${categoria.alta == false}">
			<a href="darAltaCategoria?id=${categoria.id}">dar de alta</a>
		</c:if>
		<a href="editarCategoria?id=${categoria.id}">editar</a>
	</div>
</c:forEach>

</div>

</body>
</html>