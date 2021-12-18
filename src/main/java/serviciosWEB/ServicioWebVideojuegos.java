package serviciosWEB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import servicios.ServicioVideojuegos;

@Controller
@RequestMapping("servicioWebVideojuegos/")
public class ServicioWebVideojuegos {
	
	@Autowired
	private ServicioVideojuegos servicioVideojuegos;
	
	@RequestMapping("obtenerVideojuegos")
	public ResponseEntity<String> obtenerVideojuegos() {
		//cuando hay asociaciones, en este caso libro-categoria
		//podemos tener problemas para transformarlo a json
		//String json = new Gson().toJson(servicioVideojuegos.obtenerVideojuegos());
		
		String json = new Gson().toJson(servicioVideojuegos.obtenerVideojuegosParaListado());
		
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@RequestMapping("obtenerDetallesVideojuego")
	public ResponseEntity<String> obtenerDetallesVideojuego(String id) {
		String json = new Gson().toJson(servicioVideojuegos.obtenerDetallesVideojuego(Integer.parseInt(id)));
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	

}
