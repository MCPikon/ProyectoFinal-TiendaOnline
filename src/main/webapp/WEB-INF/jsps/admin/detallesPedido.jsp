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
	<div style="text-align: center;">detalles del pedido:</div>
	<div>
	nombre: ${pedido.nombreCompleto}<br>
	direccion: ${pedido.direccion}<br>
	provincia: ${pedido.provincia}<br>
	codigo postal: ${pedido.codigoPostal}<br>
	pais: ${pedido.pais}<br>
	<br>
	tipo tarjeta: ${pedido.tipoTarjeta}<br>
	titular: ${pedido.titularTarjeta}<br>
	numero tarjeta: ${pedido.numeroTarjeta}<br>
	</div>
	
	<div style="text-align: center;">productos del pedido:</div>
	<c:forEach var="productoPedido" items="${pedido.productosPedido}">
	<div style="margin: 20px; padding: 10px; background-color: lightblue">
		nombre: ${productoPedido.videojuego.nombre}<br>
		precio/unidad: ${productoPedido.videojuego.precio}<br>
		cantidad: ${productoPedido.cantidad}<br>
	</div>
	</c:forEach>
	
	<input type="hidden" id="id_pedido" value="${pedido.id}"/>
	<br>
	ESTADO DEL PEDIDO:
	<select id="select_estado">
		<c:forEach var="estado" items="${estados}">
			<option 
				<c:if test="${estado.key == pedido.estado}">
					selected="selected"
				</c:if>  
			 value="${estado.key}">${estado.value}</option>
		</c:forEach>
	</select>
</div>
<script type="text/javascript" src="../librerias_javascript/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
	$("#select_estado").change(function(e){
		//obtener el estado seleccionado y mandarlo a un servicio web
		var estado = $("#select_estado").find(":selected").val();
		var idPedido = $("#id_pedido").val();
		$.post("servicioWebPedidos/actualizarEstadoPedido",{
			id : idPedido,
			estado : estado
		}).done(function(res){
			if(res != "ok") {
				alert(res);
			}
		});
	});
	</script>
</body>
</html>