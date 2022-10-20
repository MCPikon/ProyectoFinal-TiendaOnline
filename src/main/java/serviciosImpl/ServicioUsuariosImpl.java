package serviciosImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Categoria;
import modelo.Usuario;
import servicios.ServicioUsuarios;

@Service
@Transactional
public class ServicioUsuariosImpl implements ServicioUsuarios {
	
	//@Resource asigna la bean de la id indicada, en este caso fff
	//a la variable a continuaci�n definida
	@Resource(name = "fff")
	private SessionFactory sessionFactory; 

	@Override
	public void registrarUsuario(Usuario u) {
		sessionFactory.getCurrentSession().save(u);		
	}

	@Override
	public boolean comprobarEmail(String email) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("email", email));
		if (c.uniqueResult() == null) {
			return false;
		}else {
			return true;
		}
	
	}

	@Override
	public Usuario obtenerUsuarioPorEmailYpass(String email, String pass) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("email", email));
		c.add(Restrictions.eq("pass", pass));
		return (Usuario) c.uniqueResult();
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		return sessionFactory.getCurrentSession().createCriteria(Usuario.class).list();
	}

	@Override
	public Map<String, Object> obtenerUsuarioPorId(int id) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_USUARIO_POR_ID);
		query.setParameter("id", id);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<String, Object>) query.uniqueResult();
	}

	@Override
	public void darBajaUsuario(int id) {
		Usuario u = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
		u.setAlta(false);
		sessionFactory.getCurrentSession().update(u);
	}

	@Override
	public void darAltaUsuario(int id) {
		Usuario u = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
		u.setAlta(true);
		sessionFactory.getCurrentSession().update(u);
	}

}
