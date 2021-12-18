package controladores.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import modelo.Categoria;
import servicios.ServicioCategorias;

@Controller
@RequestMapping("admin/")
public class ControladorCategorias {
	
	@Autowired
	private ServicioCategorias servicioCategorias;
	
	@RequestMapping("listarCategorias")
	public String listarCategorias(Model model) {
		model.addAttribute("info", servicioCategorias.obtenerCategorias());
		return "admin/categorias";
	}
	
	@RequestMapping("registrarCategoria")
	public String registrarCategoria(Model model) {
		Categoria nueva = new Categoria();
		model.addAttribute("nuevaCategoria", nueva);
		return "admin/formularioRegistroCategoria";
	}
	
	@RequestMapping("guardarNuevaCategoria")
	public String guardarNuevaCategoria( @ModelAttribute("nuevaCategoria") @Valid Categoria nuevaCategoria, BindingResult br, Model model) {
		if(!br.hasErrors()) {
			servicioCategorias.crearCategoria(nuevaCategoria);
			return "admin/registroCategoriaOk";
		}else {
			model.addAttribute("nuevaCategoria", nuevaCategoria);
			return "admin/formularioRegistroCategoria";
		}
	}
	
	@RequestMapping("borrarCategoria")
	public String borrarCategoria(String id, Model model) {
		servicioCategorias.borrarCategoria(Integer.parseInt(id));
		return listarCategorias(model);
	}
	
	@RequestMapping("editarCategoria")
	public String editarCategoria(String id, Model model) {
		Categoria c = servicioCategorias.obtenerCategoriaPorId(Integer.parseInt(id));
		model.addAttribute("categoria", c);
		return "admin/formularioEditarCategoria";
	}
	
	@RequestMapping("guardarCambiosCategoria")
	public String guardarCambiosCategoria( @ModelAttribute("categoria") @Valid Categoria categoria, BindingResult br, Model model) {
		if(!br.hasErrors()) {
			servicioCategorias.guardarCambiosCategoria(categoria);
			return listarCategorias(model);
		}else {
			model.addAttribute("categoria", categoria);
			return "admin/formularioEditarCategoria";
		}
	}
	
	@RequestMapping("darBajaCategoria")
	public String darBajaCategoria(String id, Model model) {
		servicioCategorias.darBajaCategoria(Integer.parseInt(id));
		return listarCategorias(model);
	}
	
	@RequestMapping("darAltaCategoria")
	public String darAltaCategoria(String id, Model model) {
		servicioCategorias.darAltaCategoria(Integer.parseInt(id));
		return listarCategorias(model);
	}

}
