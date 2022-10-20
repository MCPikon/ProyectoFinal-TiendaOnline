package serviciosImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Categoria;
import servicios.ServicioCategorias;

@Service
@Transactional
public class ServicioCategoriasImpl implements ServicioCategorias {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Categoria> obtenerCategorias() {
		return sessionFactory.getCurrentSession().createCriteria(Categoria.class).list();
	}

	@Override
	public void crearCategoria(Categoria c) {
		sessionFactory.getCurrentSession().persist(c); //hace basicamente lo mismo que save
	}

	@Override
	public Map<String, String> obtenerCategoriasParaDesplegable() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLE);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE); //lo siguiente es para que me de como resultado elementos tipo Map
		
		List<Map<String, Object>> res = query.list();
		Map<String, String> valoresDesplegable = new HashMap<String, String>();
		System.out.println("obtenido de la base de datos:");
		for (Map<String, Object> map : res) {
			System.out.println("id: " + map.get("id") + " nombre: " + map.get("nombre"));
			valoresDesplegable.put(map.get("id").toString(), map.get("nombre").toString());
		}
		return valoresDesplegable;
	}
	
	@Override
	public void borrarCategoria(int id) {
		Categoria c = (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, id);
		sessionFactory.getCurrentSession().delete(c);
	}
	
	@Override
	public Categoria obtenerCategoriaPorId(int id) {
		return (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, id);
	}

	@Override
	public void guardarCambiosCategoria(Categoria c) {
		sessionFactory.getCurrentSession().merge(c);
		
	}

	@Override
	public void darBajaCategoria(int id) {
		Categoria c = (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, id);
		c.setAlta(false);
		sessionFactory.getCurrentSession().update(c);
	}

	@Override
	public void darAltaCategoria(int id) {
		Categoria c = (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, id);
		c.setAlta(true);
		sessionFactory.getCurrentSession().update(c);	
	}

}
