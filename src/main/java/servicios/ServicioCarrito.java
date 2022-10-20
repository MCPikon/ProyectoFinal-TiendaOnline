package servicios;

import java.util.List;
import java.util.Map;

import modelo.Usuario;

public interface ServicioCarrito {
	
	void agregarProducto(Usuario u, int idProducto, int cantidad);
	List<Map<String, Object>> obtenerProductosCarrito(Usuario u);
	void actualizarProductoCarrito(int id_usuario, int id_producto, int cantidad);
	void borrarProductoCarrito(int idUsuario, int idProducto);

}
