package servicios;

import java.util.List;
import java.util.Map;

import modelo.Usuario;
import modelo.Videojuego;

public interface ServicioVideojuegos {
	
	public int obtenerTotalDeVideojuegos(String nombre);
	List<Videojuego> obtenerVideojuegos(String nombre, int comienzo);
	void registrarVideojuego(Videojuego v);
	void borrarVideojuego(int id);
	Videojuego obtenerVideojuegoPorId(int id);
	void guardarCambiosVideojuego(Videojuego v);
	void darBajaVideojuego(int idVideojuego);
	void darAltaVideojuego(int idVideojuego);
	void registrarLikeVideojuego(int idVideojuego, Usuario u);
	
	//funciones de ajax
	List<Map<String, Object>> obtenerVideojuegosParaListado();
	Map<String, Object> obtenerDetallesVideojuego(int id);
	List<Map<String, Object>> obtenerProductosPorIdPedido(int idPedido);

}
