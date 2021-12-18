package controladores.admin;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import modelo.Videojuego;
import servicios.ServicioCategorias;
import servicios.ServicioVideojuegos;
import utilidadesArchivos.GestorArchivos;

@Controller
@RequestMapping("admin/")
public class ControladorVideojuegos {
	
	@Autowired
	private ServicioVideojuegos servicioVideojuegos;
	
	@Autowired
	private ServicioCategorias servicioCategorias;
	
	@RequestMapping("listarVideojuegos")
	public String listarVideojuegos(@RequestParam(defaultValue = "") String nombre, Integer comienzo, Model model) {
		System.out.println("mostrar los videojuegos con el nombre: " + nombre);
		
		int comienzo_int = 0;
		if(comienzo != null) {
			comienzo_int = comienzo.intValue();
		}
		
		System.out.println("mostrar desde el resultado: " + comienzo_int);
		
		model.addAttribute("info", servicioVideojuegos.obtenerVideojuegos(nombre, comienzo_int));
		model.addAttribute("fecha_hora_actual", new Date().getTime());
		model.addAttribute("siguiente", comienzo_int + 10);
		model.addAttribute("anterior", comienzo_int - 10);
		model.addAttribute("total", servicioVideojuegos.obtenerTotalDeVideojuegos(nombre));
		model.addAttribute("nombre", nombre);
		return "admin/videojuegos";
	}
	
	@RequestMapping("registrarVideojuego")
	public String registrarVideojuego(Model model) {
		Videojuego nuevo = new Videojuego();
		nuevo.setAlta(true);
		model.addAttribute("nuevoVideojuego", nuevo);
		
		Map<String, String> mapCategorias = servicioCategorias.obtenerCategoriasParaDesplegable();
		model.addAttribute("categorias", mapCategorias);
		return "admin/formularioRegistroVideojuego";
	}
	
	@RequestMapping("guardarNuevoVideojuego")
	public String guardarNuevoVideojuego( @ModelAttribute("nuevoVideojuego") @Valid Videojuego nuevoVideojuego, BindingResult br, Model model, HttpServletRequest request) {

		System.out.println("id de la categoria del videojuego: " + nuevoVideojuego.getIdCategoria());
		
		if(!br.hasErrors()) {
			//prueba puntual para practicar paginacion
//			String nombreOriginal = nuevoVideojuego.getNombre();
//			
//			for (int i = 0; i < 50; i++) {
//				nuevoVideojuego.setNombre(nombreOriginal + " " + i);
//				servicioVideojuegos.registrarVideojuego(nuevoVideojuego);
//				System.out.println("archivo de imagen: " + nuevoVideojuego.getImagen());
//				String rutaRealDelProyecto = request.getServletContext().getRealPath("");
//				GestorArchivos.guardarImagenVideojuego(nuevoVideojuego, rutaRealDelProyecto);
//			}
			servicioVideojuegos.registrarVideojuego(nuevoVideojuego);
			System.out.println("archivo de imagen: " + nuevoVideojuego.getImagen());
			String rutaRealDelProyecto = request.getServletContext().getRealPath("");
			GestorArchivos.guardarImagenVideojuego(nuevoVideojuego, rutaRealDelProyecto);
			return "admin/registroVideojuegoOk";
		} else {
			Map<String, String> mapCategorias = servicioCategorias.obtenerCategoriasParaDesplegable();
			model.addAttribute("categorias", mapCategorias);
			model.addAttribute("nuevoVideojuego", nuevoVideojuego);
			return "admin/formularioRegistroVideojuego";
		}
	}
	
	@RequestMapping("borrarVideojuego")
	public String borrarVideojuego(String id, Model model) {
		servicioVideojuegos.borrarVideojuego(Integer.parseInt(id));
		return listarVideojuegos("", 0, model);
	}
	
	@RequestMapping("editarVideojuego")
	public String editarVideojuego(String id, Model model) {
		Videojuego v = servicioVideojuegos.obtenerVideojuegoPorId(Integer.parseInt(id));
		Map<String, String> mapCategorias = servicioCategorias.obtenerCategoriasParaDesplegable();
		model.addAttribute("categorias", mapCategorias);
		v.setIdCategoria(v.getCategoria().getId());
		model.addAttribute("videojuego", v);
		return "admin/formularioEditarVideojuego";
	}
	
	@RequestMapping("guardarCambiosVideojuego")
	public String guardarCambiosVideojuego( @ModelAttribute("videojuego") @Valid Videojuego videojuego, BindingResult br, Model model, HttpServletRequest request) {
		if(!br.hasErrors()) {
			System.out.println("datos del videojuego a actualizar: " + videojuego);
			System.out.println("archivo de imagen: " + videojuego.getImagen());
			String rutaRealDelProyecto = request.getServletContext().getRealPath("");
			GestorArchivos.guardarImagenVideojuego(videojuego, rutaRealDelProyecto);
			servicioVideojuegos.guardarCambiosVideojuego(videojuego);
			return listarVideojuegos("", 0, model);
		}else {
			model.addAttribute("videojuego", videojuego);
			model.addAttribute("categorias", servicioCategorias.obtenerCategoriasParaDesplegable());
			return "admin/formularioEditarVideojuego";
		}
	}
	
	@RequestMapping("darBajaVideojuego")
	public String darBajaVideojuego(String id, Model model) {
		servicioVideojuegos.darBajaVideojuego(Integer.parseInt(id));
		return listarVideojuegos("", 0, model);
	}
	
	@RequestMapping("darAltaVideojuego")
	public String darAltaVideojuego(String id, Model model) {
		servicioVideojuegos.darAltaVideojuego(Integer.parseInt(id));
		return listarVideojuegos("", 0, model);
	}

}//end class
