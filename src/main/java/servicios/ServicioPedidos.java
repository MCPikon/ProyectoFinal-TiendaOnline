package servicios;

import java.util.List;
import java.util.Map;

import datos.servicios.ResumenPedido;
import modelo.Pedido;

public interface ServicioPedidos {
	
	void procesarPaso1(String nombre, String direccion, String provincia, String codigoPostal, String pais, int idUsuario);
	void procesarPaso2(String titular, String numero, String tipoTarjeta, int idUsuario);
	ResumenPedido obtenerResumenDelPedido(int idUsuario);
	void confirmarPedido(int idUsuario);
	List<Pedido> obtenerPedidos();
	Pedido obtenerPedidoPorId(int idPedido);
	void actualizarEstadoPedido(int idPedido, String estado);
	List<Map<String, Object>> obtenerPedidosPorIdUsuario(int idUsuario);

}
