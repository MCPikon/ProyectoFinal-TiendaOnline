package serviciosImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Carrito;
import modelo.ProductoCarrito;
import modelo.Usuario;
import modelo.Videojuego;
import servicios.ServicioCarrito;

@Service
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void agregarProducto(Usuario u, int idProducto, int cantidad) {
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
		Carrito c = uBaseDeDatos.getCarrito();
		if(c == null) {
			c = new Carrito();
			c.setUsuario(uBaseDeDatos);
			uBaseDeDatos.setCarrito(c);
			sessionFactory.getCurrentSession().save(c);
		}
		
		boolean producto_en_carrito = false;
		for (ProductoCarrito pc_en_carrito : c.getProductosCarrito()) {
			if(pc_en_carrito.getVideojuego().getId() == idProducto) {
				producto_en_carrito = true;
				pc_en_carrito.setCantidad(pc_en_carrito.getCantidad() + cantidad);
				sessionFactory.getCurrentSession().merge(pc_en_carrito);
			}
		}
		if(!producto_en_carrito) {
			ProductoCarrito pc = new ProductoCarrito();
			pc.setCarrito(c);
			pc.setVideojuego((Videojuego) sessionFactory.getCurrentSession().get(Videojuego.class, idProducto));
			pc.setCantidad(cantidad);
			sessionFactory.getCurrentSession().save(pc);
		}
	}

	@Override
	public List<Map<String, Object>> obtenerProductosCarrito(Usuario u) {
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
		Carrito c = uBaseDeDatos.getCarrito();
		List<Map<String, Object>> res = null;
		if(c != null) {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_PRODUCTOS_CARRITO);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			query.setParameter("carrito_id", c.getId());
			res = query.list();
		}
		return res;
	}
	
	@Override
	public void actualizarProductoCarrito(int id_usuario, int id_producto, int cantidad) {
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id_usuario);
		Carrito c = uBaseDeDatos.getCarrito();
		if(c != null) {
			List<ProductoCarrito> pcs = c.getProductosCarrito();
			for (ProductoCarrito productoCarrito : pcs) {
				if(productoCarrito.getVideojuego().getId() == id_producto) {
					productoCarrito.setCantidad(cantidad);
					sessionFactory.getCurrentSession().update(productoCarrito);
				}
			}
		}
	}//end actualizarProductoCarrito

	@Override
	public void borrarProductoCarrito(int idUsuario, int idProducto) {
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
		Carrito c = uBaseDeDatos.getCarrito();
		if(c != null) {
			List<ProductoCarrito> pcs = c.getProductosCarrito();
			for (ProductoCarrito productoCarrito : pcs) {
				//uso sql para borrar el registro
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_BORRAR_PRODUCTO_CARRITO);
				query.setParameter("id_carrito", c.getId());
				query.setParameter("id_videojuego", idProducto);
				query.executeUpdate();
			}
		}
	}//end borrarProductoCarrito

}
