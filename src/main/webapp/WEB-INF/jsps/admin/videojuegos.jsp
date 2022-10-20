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
<a href="registrarVideojuego" class="btn btn-primary">Nuevo Videojuego</a><br>

<form action="listarVideojuegos" class="mt-3">
	<div class="row g-3 align-items-center">
		<div class="col-auto">
			<input type="text" name="nombre" value="${nombre}" placeholder="Escribe aquí para buscar..." class="form-control"/>
		</div>
		<div class="col-auto">
			<input type="submit" value="BUSCAR" class="btn btn-secondary"/>
		</div>
	</div>
</form>
<br>

<div style="text-align: center;">
	<h5>Total de Videojuegos: ${total}</h5> <br>
	<c:if test="${anterior >= 0}">
		<a href="listarVideojuegos?comienzo=${anterior}&nombre=${nombre}" class="btn btn-primary">Anterior</a>
		&nbsp;
	</c:if>
	<c:if test="${siguiente < total}">
		<a href="listarVideojuegos?comienzo=${siguiente}&nombre=${nombre}" class="btn btn-primary">Siguiente</a>
	</c:if>
</div>

Listado de videojuegos de la tienda:<br>
<div class="row gy-3">
<c:forEach var="videojuego" items="${info}">
	<div class="col-lg-3 col-md-3 col-sm-12">
				<div class="card border-primary h-100" style="width: 18rem;">
				  <img src="../subidas/${videojuego.id}.jpg?t=${fecha_hora_actual}" class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title">${videojuego.nombre}</h5>
				    <h5 class="card-title mb-2">${videojuego.precio} &euro;</h5>
				    <p class="card-text">${videojuego.descripcion}</p>
				    <p class="card-text"><b>categoria:</b> ${videojuego.categoria.nombre}</p>
				    <p class="card-text"><b>estado:</b> ${videojuego.estado}</p>
				    <p class="card-text"><b>formato:</b> ${videojuego.formato}</p>
				    <p class="card-text"><b>editor:</b> ${videojuego.editor}</p>
				    <p class="card-text"><b>plataforma:</b> ${videojuego.plataforma}</p>
				    <p class="card-text"><b>likes:</b> ${videojuego.likes}</p>
				    <p class="card-text"><b>alta:</b> ${videojuego.alta}</p>
				    <c:if test="${videojuego.alta == true}">
						<a href="darBajaVideojuego?id=${videojuego.id}">dar de baja</a>
					</c:if>
					<c:if test="${videojuego.alta == false}">
						<a href="darAltaVideojuego?id=${videojuego.id}">dar de alta</a>
					</c:if>
					<a href="editarVideojuego?id=${videojuego.id}">editar</a>
				  </div>
				</div>
		</div>
</c:forEach>
</div>

</div>
</body>
</html>