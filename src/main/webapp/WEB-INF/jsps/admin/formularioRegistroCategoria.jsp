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
	Introduce los datos de la nueva categoria:<br>
	<springform:form modelAttribute="nuevaCategoria" action="guardarNuevaCategoria" enctype="multipart/form-data">
	
	nombre: <springform:input path="nombre"/> <span style="color: red"><springform:errors path="nombre"/></span> <br/>
	descripcion: <springform:textarea rows="3" cols="15" path="descripcion"/> <br/><span style="color: red"><springform:errors path="descripcion"/></span> <br/>
	
	<input type="submit" value="registrar categoria">
	
	</springform:form>
</div>

</body>
</html>