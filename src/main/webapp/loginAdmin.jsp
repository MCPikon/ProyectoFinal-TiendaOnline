<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<div class="container">
	Indentificate admin!
	<form method="post" action="admin/">
	pass: <input type="password" value="${sessionScope.campo_pass}" name="pass" id="pass"/> <br>
	<input type="checkbox" checked="checked" name="recordar_pass"/> recordar contraseña (aún no operativo) <br>
	
	<input type="submit" class="btn btn-primary"/>
	</form>
</div>

<script type="text/javascript" src="librerias_javascript/js.cookie.min.js"></script>
<script type="text/javascript">
if(typeof(Cookies.get("pass_admin")) != "undefined") {
	document.getElementById("pass").value = Cookies.get("pass_admin");
}
</script>
</body>
</html>