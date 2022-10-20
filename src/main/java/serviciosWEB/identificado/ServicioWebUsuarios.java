package serviciosWEB.identificado;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import modelo.Usuario;
import servicios.ServicioUsuarios;

@Controller("servicioWebUsuariosIdentificado")
@RequestMapping("/identificado/servicioWebUsuarios/")
public class ServicioWebUsuarios {
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("misDatos")
	public ResponseEntity<String> misDatos(HttpServletRequest request) {
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		String json = new Gson().toJson(servicioUsuarios.obtenerUsuarioPorId(u.getId()));
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}

}
