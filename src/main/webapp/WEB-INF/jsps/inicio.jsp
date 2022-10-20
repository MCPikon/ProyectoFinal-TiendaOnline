<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<div style="text-align: right; margin: 10px;">
	<a href="?lang=es" class="btn btn-danger">español</a>
	<a href="?lang=en" class="btn btn-primary">english</a>
</div>
<div class="container">
<div style="text-align: center;" class="display-6 m-2"><spring:message code="inicio.bienvenida"/></div>
<div id="mensaje_login" class="h5" style="text-align: center;"></div>
</div>
<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-primary mb-2">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">VideoGames Store <span class="material-icons">sports_esports</span></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="#" id="enlace_listado"><spring:message code="inicio.videojuegos"/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" id="enlace_carrito"><spring:message code="inicio.carrito"/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" id="enlace_registrarme"><spring:message code="inicio.registrarme"/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" id="enlace_pedidos"><spring:message code="inicio.pedidos"/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" id="enlace_mis_datos"><spring:message code="inicio.misDatos"/></a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="inicio.perfil"/></a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#" id="enlace_identificarme"><spring:message code="inicio.iniciarSesion"/></a>
            <a class="dropdown-item" href="#" id="enlace_logout"><spring:message code="inicio.cerrarSesion"/></a>
          </div>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-sm-2" type="text" placeholder='<spring:message code="inicio.buscar"/>'>
        <button class="btn btn-outline-light my-2 my-sm-0" type="submit"><spring:message code="inicio.buscar"/></button>
      </form>
    </div>
  </div>
</nav>
<div class="container">
<div id="contenedor">
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script type="text/javascript" src="librerias_javascript/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="librerias_javascript/mustache.js"></script>
<script type="text/javascript" src="librerias_javascript/js.cookie.min.js"></script>

<script type="text/javascript" src="js/carga_plantillas.js"></script>
<script type="text/javascript" src="js/validaciones.js"></script>
<script type="text/javascript">

//codigo del idioma
const idioma = '<spring:message code="codigo.idioma"/>'; 

//variable que indica el nombre del usuario
var nombre_login = "";

//parte de carga de las plantillas en variables
var plantillaListarVideojuegos = "";
var plantillaCarrito = "";
var plantillaRegistrarUsuario = "";
var plantillaIdentificarUsuario = "";
var plantillaDetallesVideojuego = "";
var checkout_1 = "";
var checkout_2 = "";
var checkout_3 = "";
var plantillaMisDatos = "";
var plantillaMisPedidos = "";
var plantillaProductosPedido = "";

cargar_plantillas_del_servidor(idioma); //carga_plantillas.js
obtener_listado();

//funciones ajax
function obtener_listado() {
// 	alert("comunicarnos con ServicioVideojuegos para que nos de" +
// 			" el json los videojuegos que en la tienda");
	//usando jquery podemos hacerlo usando la funcion $.ajax
	$.ajax("servicioWebVideojuegos/obtenerVideojuegos", 
			{
			//este es un objeto que configura la llamada por ajax a ServicioLibros
				success : function(data){
					//esto volcaría directamente el json recibido
					//$("#contenedor").html(data);
					//alert("recibido: " + data);
					var productos = JSON.parse(data);
					for (i in productos) {
						productos[i].fecha_hora_actual = new Date().getTime();
					}
					var texto_html = "";
					//así se hacía antes:
// 					for(p in productos){
// 						texto_html += "<div>";
// 						texto_html += productos[p].titulo;
// 						texto_html += " precio: ";
// 						texto_html += productos[p].precio;
// 						texto_html += ' <a href="#">comprar</a>';
// 						texto_html += "</div>";
// 					}//end forin
					//vamos a usar una plantilla de html con mustache
					texto_html = Mustache.render(plantillaListarVideojuegos, productos);
					$("#contenedor").html(texto_html);
					//vamos a decir que hacer cuando se haga click en un producto
					$(".enlace_detalles").click(function(e){
						var id = $(this).attr("id_producto");
// 						alert("pedir al servidor todos los detalles del producto de id: " 
// 								+ id + " para mostrarselos al usuario");
						$.ajax("servicioWebVideojuegos/obtenerDetallesVideojuego?id=" + id,{
							success: function(res){
// 								alert("recibido del server: " + res);
								var objeto_videojuego_recibido = JSON.parse(res);
								var texto_html = Mustache.render(plantillaDetallesVideojuego, objeto_videojuego_recibido);
								$("#contenedor").html(texto_html);
								$("#enlace_comprar").click(
									function(e) {
										comprar_producto($(this).attr("id_producto"));
									}
								);
								$("#enlace_like").click(
									function(e) {
										poner_like($(this).attr("id_producto"));
										$("#enlace_like").addClass("disabled");
									}
								);
							}
						});
					});//end click enlace_detalles
				}//end success
			}
	);//end ajax
}//end function

function comprar_producto(id) {
	var cantidad = $("#campo_cantidad").val();
	$.ajax("identificado/servicioWebCarrito/agregarVideojuego?id=" + id + "&cantidad=" + cantidad,{
		success: function(res) {
			if(res == "te has colado.") {
				alert("tienes que identificarte para poder comprar videojuegos");	
			}else {
				alert(res);
			}	
		}
	});
}//end comprar_producto

function poner_like(id) {
	$.ajax("identificado/servicioWebVideojuegos/darLike?id=" + id,{
		success: function(res) {
			if(res == "te has colado.") {
				alert("tienes que identificarte para poder dar like a videojuegos");	
			}else {
				alert(res);
				$.ajax("servicioWebVideojuegos/obtenerDetallesVideojuego?id=" + id,{
					success: function(res){
//							alert("recibido del server: " + res);
						var objeto_videojuego_recibido = JSON.parse(res);
						var texto_html = Mustache.render(plantillaDetallesVideojuego, objeto_videojuego_recibido);
						$("#contenedor").html(texto_html);
						$("#enlace_comprar").click(
							function(e) {
								comprar_producto($(this).attr("id_producto"));
							}
						);
						$("#enlace_like").click(
							function(e) {
								poner_like($(this).attr("id_producto"));
							}
						);
					}
				});
			}	
		}
	});
}

function obtenerProductosCarrito() {
	$.ajax("identificado/servicioWebCarrito/obtenerProductosCarrito",{
		success: function(res){
			if(res == "te has colado.") {
				alert("tienes que identificarte para acceder a tu carrito");	
			}else {
				//alert(res);
				var productos_carrito = JSON.parse(res);
				for (i in productos_carrito) {
					productos_carrito[i].fecha_hora_actual = new Date().getTime();
				}
				var html = Mustache.render(plantillaCarrito, productos_carrito);
				$("#contenedor").html(html);
				$(".enlace_borrar_producto").click(
					function(e){
						var id = $(this).attr("id-producto");
						alert("indicar al servicio web que quite del carrito el producto de id: " + id);
						$.post("identificado/servicioWebCarrito/borrarProductoCarrito",
								{
									idProducto: id
								}).done(function(res){
									if(res != "ok"){
										alert(res);
									}else {
										obtenerProductosCarrito();
									}
								});
					}
				);
				$(".input_cantidad").change(
					function(e){
						var cantidad = $(this).val();
						var id = $(this).attr("id-producto");
						alert("indicar al servicio web la nueva cantidad: " + cantidad + " sobre el producto de id: " + id);
						$.post("identificado/servicioWebCarrito/actualizarCantidadProductoCarrito",
							{
								cantidad: cantidad,
								id_producto: id
							}).done(function(res){
								alert(res);
							});
					}
				);
				$("#realizar_pedido").click(
					function(e){
						checkout_paso_0();
					}	
				);
			}
		}
	});
}

function checkout_paso_0() {
	$("#contenedor").html(checkout_1);
	$("#aceptar_paso_1").click(function(){
		checkout_paso_1_aceptar();
	})
}

function checkout_paso_1_aceptar() {
	//recoger los valores introducidos y mandarlos al servidor
	var nombre = $("#campo_nombre").val();
	var direccion = $("#campo_direccion").val();
	var provincia = $("#campo_provincia").find(":selected").val();
	var codigoPostal = $("#campo_codigo_postal").val();
	var pais = $("#campo_pais").val();
	
	//ahora lo suyo seria validar los valores recogidos
	//...
	
	//mandar los valores al servicio web
	$.post("identificado/servicioWebPedidos/paso1",
		{
			nombre: nombre,
			direccion: direccion,
			provincia: provincia,
			codigoPostal: codigoPostal,
			pais: pais
		}		
	).done(function(res){
		if(res == "ok") {
			//mostrar checkout_2.html
			$("#contenedor").html(checkout_2);
			$("#checkout2_aceptar").click(checkout_paso_2_aceptar);
		}else {
			alert(res);
		}
	});
}//end checkout_paso_1_aceptar

function checkout_paso_2_aceptar() {
	var tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val();
	var numero_tarjeta = $("#numero_tarjeta").val();
	var titular_tarjeta = $("#titular_tarjeta").val();
	$.post("identificado/servicioWebPedidos/paso2",{
			tarjeta: tipo_tarjeta, 
			numero: numero_tarjeta,
			titular: titular_tarjeta
	}).done(function(res){
		if(res.substring(0, 2) == "ok"){
			//mostrar el paso 3
			var json = JSON.parse(res.substring(3, res.length));
			var html = Mustache.render(checkout_3, json);
			
			$("#contenedor").html(html);
			$("#boton_confirmar_pedido").click(checkout_paso_3_confirmar);
		}
	});
}//end checkout_paso_2_aceptar

function checkout_paso_3_confirmar() {
	//llamar al servicio web de pedidos para que confirme el pedido
	//+cambiar el estado del pedido
	//+registrar las informarciones de ProductoCarrito a ProductoPedido - por crear...
	$.ajax("identificado/servicioWebPedidos/paso3",{
		success : function(res){
			alert("respuesta del servicio web: " + res);
			obtener_listado();
		}
	});
}

function mostrarMisDatos(){
	//alert("obteniendo los datos");
	$.ajax("identificado/servicioWebUsuarios/misDatos",{
		success: function(res){
			if(res == "te has colado.") {
				alert("tienes que identificarte para poder ver tus datos");	
			}else {
				//alert("datos de usuario: " + res);
				var usuario_recibido = JSON.parse(res);
				var texto_html = Mustache.render(plantillaMisDatos, usuario_recibido);
				$("#contenedor").html(texto_html);
				$("#boton_show_pass").click(showPass);
			}
		}
	});
}

function mostrarMisPedidos(){
	//alert("obteniendo los datos");
	$.ajax("identificado/servicioWebPedidos/misPedidos",{
		success: function(res){
			if(res == "te has colado.") {
				alert("tienes que identificarte para poder ver tus pedidos");	
			}else {
				//alert("datos de pedido: " + res);
				var pedidos_recibidos = JSON.parse(res);
				console.log(res);
				var texto_html = Mustache.render(plantillaMisPedidos, pedidos_recibidos);
				$("#contenedor").html(texto_html);
				$(".enlace_detalles_pedido").click(function(e){
						var id = $(this).attr("id_pedido");
						$.ajax("identificado/servicioWebPedidos/detallesPedido?id=" + id, {
							success: function(res) {
								var productos = JSON.parse(res);
								for(i in productos) {
									productos[i].fecha_hora_actual = new Date();
								}
								var texto_html = Mustache.render(plantillaProductosPedido, productos);
								$("#contenedor").html(texto_html);
							}
						});
				});
			}
		}
	});
}

function mostrarRegistroUsuario() {
	$("#contenedor").html(plantillaRegistrarUsuario);
	$("#form_registro_usuario").submit(function(e){
		var nombre = $("#nombre").val();
		var email = $("#email").val();
		var pass = $("#pass").val();
		console.log(nombre);
		console.log(email);
		console.log(pass);
		if(validarNombre(nombre) && validarEmail(email) && validarPass(pass)) {
			alert("todo ok, mandando información al servicio web...");
			//vamos a usar FormData para mandar el form al servicio web
			var formulario = document.forms[0];
			var formData = new FormData(formulario);
			$.post("servicioWebUsuarios/registrarUsuario", {
				nombre: nombre,
				email: email,
				pass: pass
			}).done(function(res){
				if(res == "ok") {
					alert("registrado correctamente, ya puedes identificarte");
					mostrarIdentificacionUsuario();
				}else {
					alert(res);
				}
			});
			
		}//end if validaciones
		e.preventDefault();
	});
}

var email = "";
var pass = "";
function mostrarIdentificacionUsuario() {
	$("#contenedor").html(plantillaIdentificarUsuario);
	//Si el usuario guardo sus datos, pues los cargamos de una cookie
	if(typeof(Cookies.get("email")) != "undefined") {
		$("#email").val(Cookies.get("email"));
	}
	if(typeof(Cookies.get("pass")) != "undefined") {
		$("#pass").val(Cookies.get("pass"));
	}
	
	$("#form_login").submit(function(e) {
		email = $("#email").val();
		pass = $("#pass").val();
		$.ajax("servicioWebUsuarios/identificarUsuario",{
			data: "email=" + email + "&pass=" + pass,
			success: function(res) {
				if(res.split(",")[0] == "ok") {
					nombre_login = res.split(",")[1];
					alert("identificado correctamente, ya puedes comprar productos " + nombre_login);
					//voy a ver si está activo el recordar datos para meter el login y el pass en una cookie
					if($("#recordar_datos").prop('checked')) {
						alert("guardar datos en cookie");
						Cookies.set('email', email, { expires: 365 });
						Cookies.set('pass', pass, { expires: 365 });	
					}
					
					$("#mensaje_login").html("( identificado como: " + nombre_login + " )");
					$("#contenedor").html("login ok");
				}else {
					alert(res);
				}
			}
		});
		e.preventDefault();
	});
}

//cerrar sesion
function logout() {
	$.ajax("servicioWebUsuarios/logout",{
		success: function(res) {
			if(res == "ok") {
				$("#contenedor").html("hasta pronto " + nombre_login);
				$("#mensaje_login").html("( no estas identificado )");
			}
		}
	});
}


function showPass() {
	var pass = document.getElementById("pass_mis_datos");
	if (pass.type === "password") {
	    pass.type = "text";
	} else {
	    pass.type = "password";
	}
}

//eventos de los enlaces:
$("#enlace_listado").click(obtener_listado);
$("#enlace_carrito").click(obtenerProductosCarrito);
$("#enlace_registrarme").click(mostrarRegistroUsuario);
$("#enlace_identificarme").click(mostrarIdentificacionUsuario);
$("#enlace_logout").click(logout);
$("#enlace_mis_datos").click(mostrarMisDatos);
$("#enlace_pedidos").click(mostrarMisPedidos);

//prueba de carga de jquery e idioma
if(idioma == "es") {
	console.log("hola desde jquery");
}else {
	console.log("hi from jquery");
}

//comprobar si el usuario actual sigue identificado
$.ajax("servicioWebUsuarios/comprobarIdentificacion",{
	success: function(res){
		if(res.split(",")[0] == "ok"){
			nombre_login = res.split(",")[1];
			if(idioma == "es") {
				$("#mensaje_login").html("( identificado como: " + nombre_login + " )");	
			}else {
				$("#mensaje_login").html("( identified as: " + nombre_login + " )");
			}
			
		}else {
			if(idioma == "es") {
				$("#mensaje_login").html("( no estas identificado )");
			}else {
				$("#mensaje_login").html("( you are not identified )");
			}
			
		}
	}
});

</script>
</div>
</body>
</html>