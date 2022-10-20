package serviciosWEB.identificado;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import modelo.Usuario;
import modelo.Videojuego;
import servicios.ServicioVideojuegos;

@Controller("servicioWebVideojuegosIdentificado")
@RequestMapping("/identificado/servicioWebVideojuegos/")
public class ServicioWebVideojuegos {
	
	@Autowired
	private ServicioVideojuegos servicioVideojuegos;
	
	@RequestMapping("darLike")
	public ResponseEntity<String> darLike(String id, HttpServletRequest request) {
		String respuesta = "";
		if(request.getSession().getAttribute("usuario") != null) {
			Usuario u = (Usuario) request.getSession().getAttribute("usuario");
			servicioVideojuegos.registrarLikeVideojuego(Integer.parseInt(id), u);
			respuesta = "like registrado en servidor";
		}else {
			respuesta = "tienes que identificarte para poder dar like a productos";
		}
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

}
