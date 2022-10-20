package controladores.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import servicios.ServicioUsuarios;

@Controller
@RequestMapping("admin/")
public class ControladorUsuarios {
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("listarUsuarios")
	public String listarUsuarios(Model model) {
		model.addAttribute("info", servicioUsuarios.obtenerUsuarios());
		return "admin/usuarios";
	}
	
	@RequestMapping("darBajaUsuario")
	public String darBajaUsuario(String id, Model model) {
		servicioUsuarios.darBajaUsuario(Integer.parseInt(id));
		return listarUsuarios(model);
	}
	
	@RequestMapping("darAltaUsuario")
	public String darAltausuario(String id, Model model) {
		servicioUsuarios.darAltaUsuario(Integer.parseInt(id));
		return listarUsuarios(model);
	}

}
