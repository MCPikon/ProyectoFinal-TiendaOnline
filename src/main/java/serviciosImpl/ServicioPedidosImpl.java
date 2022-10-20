package serviciosImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantes.EstadosPedido;
import constantesSQL.ConstantesSQL;
import datos.servicios.ResumenPedido;
import modelo.Carrito;
import modelo.Pedido;
import modelo.ProductoCarrito;
import modelo.ProductoPedido;
import modelo.Usuario;
import servicios.ServicioCarrito;
import servicios.ServicioPedidos;

@Service
@Transactional
public class ServicioPedidosImpl implements ServicioPedidos {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ServicioCarrito servicioCarrito;
	
	@Override
	public void procesarPaso1(String nombre, String direccion, String provincia, String codigoPostal, String pais, int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		
		p.setNombreCompleto(nombre);
		p.setDireccion(direccion);
		p.setProvincia(provincia);
		p.setCodigoPostal(codigoPostal);
		p.setPais(pais);
		p.setEstado(EstadosPedido.EN_PROCESO);
		
		sessionFactory.getCurrentSession().merge(p);
		
	}

	@Override
	public void procesarPaso2(String titular, String numero, String tipoTarjeta, int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		p.setTitularTarjeta(titular);
		p.setNumeroTarjeta(numero);
		p.setTipoTarjeta(tipoTarjeta);
		sessionFactory.getCurrentSession().merge(p);
		
	}
	
	@Override
	public ResumenPedido obtenerResumenDelPedido(int idUsuario) {
		ResumenPedido resumen = new ResumenPedido();
		Pedido p = obtenerPedidoActual(idUsuario);
		resumen.setNombreCompleto(p.getNombreCompleto());
		resumen.setDireccion(p.getDireccion());
		resumen.setProvincia(p.getProvincia());
		resumen.setCodigoPostal(p.getCodigoPostal());
		resumen.setPais(p.getPais());
		resumen.setTipoTarjeta(p.getTipoTarjeta());
		resumen.setTitularTarjeta(p.getTitularTarjeta());
		
		String numero_original = p.getNumeroTarjeta();
		if(numero_original.length() > 4) {
			String parte1 = numero_original.substring(0, numero_original.length()-5);
			String parte2 = numero_original.substring(numero_original.length()-5, numero_original.length()-1);
			String parte1conAsteriscos = parte1.replaceAll("[0-9]", "*");
			resumen.setNumeroTarjeta(parte1conAsteriscos + parte2);
		}else {
			resumen.setNumeroTarjeta(p.getNumeroTarjeta());
		}
		resumen.setVideojuegos(servicioCarrito.obtenerProductosCarrito((Usuario) sessionFactory.getCurrentSession().get(Usuario.class, idUsuario)));
		return resumen;
	}

	@Override
	public void confirmarPedido(int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		//pasar los productos del carrito a producto pedido
		Usuario uBaseDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
		Carrito c = uBaseDatos.getCarrito();
		if(c != null) {
			for (ProductoCarrito pc : c.getProductosCarrito()) {
				ProductoPedido pp = new ProductoPedido();
				pp.setCantidad(pc.getCantidad());
				pp.setVideojuego(pc.getVideojuego());
				p.getProductosPedido().add(pp);
				pp.setPedido(p);
				sessionFactory.getCurrentSession().save(pp);
			}
		}
		//borrar los productos del carrito del usuario
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_BORRAR_PRODUCTOS_CARRITO);
		query.setParameter("carrito_id", c.getId());
		query.executeUpdate();
		//finalizamos pedido
		p.setEstado(EstadosPedido.TERMINADO);	
	}//end confirmarPedido
	
	private Pedido obtenerPedidoActual(int idUsuario) {
		Usuario uBaseDeDatos = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
		Object pedidoEnProceso = sessionFactory.getCurrentSession().createCriteria(Pedido.class).
				add(Restrictions.eq("estado", EstadosPedido.EN_PROCESO)).
				add(Restrictions.eq("usuario.id", uBaseDeDatos.getId())).uniqueResult();
		
		Pedido p = null;
		if(pedidoEnProceso == null) {
			p = new Pedido();
			p.setUsuario(uBaseDeDatos);
		}else {
			p = (Pedido) pedidoEnProceso;
		}
		return p;
	}

	@Override
	public List<Pedido> obtenerPedidos() {
		List<Pedido> pedidos = sessionFactory.getCurrentSession().createQuery("from Pedido").list();
		return pedidos;
	}

	@Override
	public Pedido obtenerPedidoPorId(int idPedido) {
		return (Pedido) sessionFactory.getCurrentSession().get(Pedido.class, idPedido);
	}

	@Override
	public void actualizarEstadoPedido(int idPedido, String estado) {
		Pedido p = obtenerPedidoPorId(idPedido);
		p.setEstado(estado);
		sessionFactory.getCurrentSession().update(p);
	}

	@Override
	public List<Map<String, Object>> obtenerPedidosPorIdUsuario(int idUsuario) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_PEDIDOS_POR_ID_USUARIO);
		query.setParameter("usuario_id", idUsuario);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> res = query.list();
		return res;
	}

}
