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
	Introduce los datos del nuevo videojuego:<br>
	<springform:form modelAttribute="nuevoVideojuego" action="guardarNuevoVideojuego" enctype="multipart/form-data">
	
	nombre: <springform:input path="nombre"/> <span style="color: red"><springform:errors path="nombre"/></span> <br/>
	categoria: 
	<springform:select path="idCategoria">
		<springform:options items="${categorias}"/>
	</springform:select><br/>
	precio: <springform:input path="precio"/> <span style="color: red"><springform:errors path="precio"/></span> <br/>
	descripcion: <springform:textarea rows="3" cols="15" path="descripcion"/> <br/><span style="color: red"><springform:errors path="descripcion"/></span> <br/>
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
	imagen: <springform:input path="imagen" type="file"/> <br/>
	imagen 2: <springform:input path="imagen2" type="file"/> <br/>
	
	<input type="submit" value="registrar videojuego">
	
	</springform:form>
</div>

</body>
</html>