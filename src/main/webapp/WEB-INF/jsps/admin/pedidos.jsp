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

Listado de pedidos de la tienda:<br>
<c:forEach var="pedido" items="${info}">
	<div style="margin: 20px; padding: 10px; background-color: lightblue;">
		${pedido.nombreCompleto}<br>
		${pedido.direccion}<br>
		estado: ${pedido.estado}<br>
		<a href="verDetallesPedido?id=${pedido.id}">ver pedido</a>
	</div>
</c:forEach>

</div>

</body>
</html>