package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Usuario {
	
	@OneToOne
	private Carrito carrito;
	
	@OneToOne
	private LikeProductoUsuario likeProductoUsuario;
	
	private String nombre;
	
	@Column(unique = true)
	private String email;
	private String pass;
	
	private boolean alta;
	
	@Id
	@GeneratedValue
	private int id;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String email, String pass, int id) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.pass = pass;
		this.id = id;
	}
	
	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public LikeProductoUsuario getLikeProductoUsuario() {
		return likeProductoUsuario;
	}

	public void setLikeProductoUsuario(LikeProductoUsuario likeProductoUsuario) {
		this.likeProductoUsuario = likeProductoUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", pass=" + pass + ", id=" + id + "]";
	}
	
}
