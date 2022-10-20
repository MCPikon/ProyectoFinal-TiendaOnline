<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
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
	Actualiza los datos del videojuego:<br>
	<springform:form modelAttribute="videojuego" action="guardarCambiosVideojuego" enctype="multipart/form-data">
	
	nombre: <springform:input path="nombre"/> <span style="color: red"><springform:errors path="nombre"/></span> <br/>
	categoria: 
	<springform:select path="idCategoria">
		<springform:options items="${categorias}"/>
	</springform:select><br/>
	precio: <springform:input path="precio"/> <span style="color: red"><springform:errors path="precio"/></span> <br/>
	descripcion: <springform:textarea rows="3" cols="15" path="descripcion"/> <span style="color: red"><springform:errors path="descripcion"/></span> <br/>
	estado: 
	<springform:select path="estado">
		<springform:option value="Nuevo">Nuevo</springform:option>
		<springform:option value="Como Nuevo">Como Nuevo</springform:option>
		<springform:option value="En Buen Estado">En Buen Estado</springform:option>
	</springform:select><br/>
	formato: <springform:input path="formato"/> <span style="color: red"><springform:errors path="formato"/></span> <br/>
	editor: <springform:input path="editor"/> <span style="color: red"><springform:errors path="editor"/></span> <br/>
	plataforma: <springform:input path="plataforma"/> <span style="color: red"><springform:errors path="plataforma"/></span> <br/>
	alta: <springform:checkbox path="alta"/> <br/>
	imagen: <img src="../subidas/${videojuego.id}.jpg" style="height: 120px"/> <br>
	<springform:input path="imagen" type="file"/> <br>
	<springform:input path="imagen2" type="file"/> <br>
	<springform:hidden path="id"/>
	
	<input type="submit" value="Guardar Cambios">
	
	</springform:form>
</div>

</body>
</html>