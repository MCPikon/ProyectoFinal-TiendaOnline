package controladores.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import constantes.EstadosPedido;
import modelo.Pedido;
import servicios.ServicioPedidos;

@Controller
@RequestMapping("/admin")
public class ControladorPedidos {
	
	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("listarPedidos")
	public String listarPedidos(Model model) {
		model.addAttribute("info", servicioPedidos.obtenerPedidos());
		return "admin/pedidos";
	}
	
	@RequestMapping("verDetallesPedido")
	public String verDetallesPedido(String id, Model model) {
		Pedido p = servicioPedidos.obtenerPedidoPorId(Integer.parseInt(id));
		model.addAttribute("pedido", p);
		
		Map<String, String> estados = new HashMap<String, String>();
		//estados.put(EstadosPedido.EN_PROCESO, "en proceso");
		estados.put(EstadosPedido.ENVIADO, "enviado");
		estados.put(EstadosPedido.LISTO_PARA_ENVIAR, "listo para enviar");
		estados.put(EstadosPedido.TERMINADO, "terminado por el usuario");
		
		model.addAttribute("estados", estados);
		
		return "admin/detallesPedido";
	}

}
