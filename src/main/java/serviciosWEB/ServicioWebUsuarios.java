package serviciosWEB;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import modelo.Usuario;
import servicios.ServicioUsuarios;
import utilidadesArchivos.GestorArchivos;

@Controller
@RequestMapping("servicioWebUsuarios/")
public class ServicioWebUsuarios {
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("registrarUsuario")
	public ResponseEntity<String> registrarUsuario(String nombre, String email, String pass, HttpServletRequest request) {
		String respuesta = "";
		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setEmail(email);
		u.setPass(pass);
		System.out.println("usuario a registrar: " + u);
		if(servicioUsuarios.comprobarEmail(u.getEmail())) {
			respuesta = "email ya registrado, usa otro";
		}else {
			respuesta = "ok";
			servicioUsuarios.registrarUsuario(u);
		}
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping("identificarUsuario")
	public ResponseEntity<String> identificarUsuario(String email, String pass, HttpServletRequest request) {
		Usuario u = servicioUsuarios.obtenerUsuarioPorEmailYpass(email, pass);
		String respuesta = "";
		if(u != null) {
			request.getSession().setAttribute("usuario", u);
			respuesta = "ok," + u.getNombre();
		}else {
			respuesta = "email o pass incorrecto";
		}
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping("comprobarIdentificacion")
	public ResponseEntity<String> comprobarIdentificacion(HttpServletRequest request) {
		String respuesta = "";
		if(request.getSession().getAttribute("usuario") != null) {
			respuesta = "ok," + ((Usuario)request.getSession().getAttribute("usuario")).getNombre();
		}else {
			respuesta = "no identificado";
		}
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}
	
	@RequestMapping("logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		String respuesta = "";
		request.getSession().invalidate();
		respuesta = "ok";
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

}