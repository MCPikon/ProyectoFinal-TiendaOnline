package servicios;

import java.util.List;
import java.util.Map;

import modelo.Usuario;

public interface ServicioUsuarios {
	
	boolean comprobarEmail(String email);
	void registrarUsuario(Usuario u);
	List<Usuario> obtenerUsuarios();
	Usuario obtenerUsuarioPorEmailYpass(String email, String pass);
	Map<String, Object> obtenerUsuarioPorId(int id);
	void darBajaUsuario(int id);
	void darAltaUsuario(int id);
	
}
