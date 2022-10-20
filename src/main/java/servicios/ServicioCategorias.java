package servicios;

import java.util.List;
import java.util.Map;

import modelo.Categoria;

public interface ServicioCategorias {
	
	Map<String, String> obtenerCategoriasParaDesplegable();
	List<Categoria> obtenerCategorias();
	void crearCategoria(Categoria c);
	void borrarCategoria(int id);
	Categoria obtenerCategoriaPorId(int id);
	void guardarCambiosCategoria(Categoria c);
	void darBajaCategoria(int id);
	void darAltaCategoria(int id);

}
