package serviciosImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Carrito;
import modelo.Categoria;
import modelo.LikeProductoUsuario;
import modelo.ProductoCarrito;
import modelo.Usuario;
import modelo.Videojuego;
import servicios.ServicioVideojuegos;

@Service
@Transactional
public class ServicioVideojuegosImpl implements ServicioVideojuegos {

	@Autowired
	private SessionFactory sessionFactory; //esta es la bean fff del hibernate-context.xml
	
	@Override
	public int obtenerTotalDeVideojuegos(String nombre) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_TOTAL_VIDEOJUEGOS);
		query.setParameter("nombre", "%" + nombre + "%");
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	@Override
	public List<Videojuego> obtenerVideojuegos(String nombre, int comienzo) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Videojuego.class);
		c.add(Restrictions.like("nombre", "%" + nombre + "%"));
		c.addOrder(Order.desc("id"));
		c.setFirstResult(comienzo);
		c.setMaxResults(10);
		return c.list();
	}
	
	@Override
	public List<Map<String, Object>> obtenerVideojuegosParaListado() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_VIDEOJUEGOS_PARA_LISTADO);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> res = query.list();
		return res;
	}

	@Override
	public void registrarVideojuego(Videojuego v) {
		//obtener categoria por hibernate
		Categoria c = (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, v.getIdCategoria());
		v.setCategoria(c);
		sessionFactory.getCurrentSession().save(v);
	}

	@Override
	public void borrarVideojuego(int id) {
		//forma "más hibernate"
//		Videojuego v = (Videojuego) sessionFactory.getCurrentSession().get(Videojuego.class, id);
//		sessionFactory.getCurrentSession().delete(v);
				
		//forma más eficiente usando SQL directamente:
		SQLQuery queryProductoCarrito = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_BORRAR_PRODUCTOCARRITO_ASOCIADO);
		queryProductoCarrito.setParameter("id", id);
		queryProductoCarrito.executeUpdate();
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_BORRAR_VIDEOJUEGO);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@Override
	public Videojuego obtenerVideojuegoPorId(int id) {
		return (Videojuego) sessionFactory.getCurrentSession().get(Videojuego.class, id);
	}

	@Override
	public void guardarCambiosVideojuego(Videojuego v) {
		Categoria c = (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, v.getIdCategoria());
		v.setCategoria(c);
		sessionFactory.getCurrentSession().merge(v);
		
	}

	@Override
	public Map<String, Object> obtenerDetallesVideojuego(int id) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_VIDEOJUEGO_POR_ID);
		query.setParameter("id", id);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<String, Object>) query.uniqueResult();
	}

	@Override
	public void darBajaVideojuego(int idVideojuego) {
		Videojuego v = (Videojuego) sessionFactory.getCurrentSession().get(Videojuego.class, idVideojuego);
		v.setAlta(false);
		sessionFactory.getCurrentSession().update(v);
	}

	@Override
	public void darAltaVideojuego(int idVideojuego) {
		Videojuego v = (Videojuego) sessionFactory.getCurrentSession().get(Videojuego.class, idVideojuego);
		v.setAlta(true);
		sessionFactory.getCurrentSession().update(v);
	}

	@Override
	public void registrarLikeVideojuego(int idVideojuego, Usuario u) {
		Videojuego v = (Videojuego) sessionFactory.getCurrentSession().get(Videojuego.class, idVideojuego);
		v.setLikes(v.getLikes() + 1);
		sessionFactory.getCurrentSession().update(v);
		LikeProductoUsuario lp = new LikeProductoUsuario();
		lp.setUsuario(u);
		lp.setVideojuego(v);
		sessionFactory.getCurrentSession().save(lp);
	}
	
	@Override
	public List<Map<String, Object>> obtenerProductosPorIdPedido(int idPedido) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_PRODUCTOS_PEDIDO);
		query.setParameter("pedido_id", idPedido);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> res = query.list();
		return res;
	}
		
}
