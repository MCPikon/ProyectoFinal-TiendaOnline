package serviciosWEB.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import servicios.ServicioPedidos;

@Controller("servicioWebPedidosAdmin")
@RequestMapping("admin/servicioWebPedidos/")
public class ServicioWebPedidos {
	
	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("actualizarEstadoPedido")
	public ResponseEntity<String> actualizarEstadoPedido(String id, String estado) {
		String respuesta = "";
		servicioPedidos.actualizarEstadoPedido(Integer.parseInt(id), estado);
		respuesta = "ok";
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

}
