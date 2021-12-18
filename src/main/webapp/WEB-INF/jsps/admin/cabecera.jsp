<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<div style="text-align: center;" class="display-6 m-2">Zona Administración</div>
<div style="text-align: center;">
	<a href="listarVideojuegos" class="btn btn-primary m-1">gestionar videojuegos</a>
	<a href="listarPedidos" class="btn btn-success m-1">gestionar pedidos</a>
	<a href="listarUsuarios" class="btn btn-danger m-1">gestionar usuarios</a>
	<a href="listarCategorias" class="btn btn-warning m-1">gestionar categorías</a>
	<a href="logOut" class="btn btn-info m-1">cerrar sesion</a>
</div>
<div style="text-align: center;">
	valor de la variable admin en la sesión del usuario: <b>${sessionScope.admin}</b>
</div>
