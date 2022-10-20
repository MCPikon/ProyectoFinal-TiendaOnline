package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Categoria {
	
	@NotEmpty(message = "nombre no puede estar vacio")
	@Size(min = 1, max = 60, message = "nombre debe tener entre 1 y 60 caracteres")
	@Pattern(regexp = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ0-9.:,()\"'-_]{1,60}$", message = "solo letras, números y espacios")
	private String nombre;
	
	@Pattern(regexp = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ0-9.:,()\"'-_]{1,120}$", message = "solo letras, números y espacios")
	private String descripcion;	
	
	//relacion de Categoria con Libro
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Videojuego> videojuegos = new ArrayList<Videojuego>();
	
	private boolean alta;
	
	@Id
	@GeneratedValue
	private int id;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Videojuego> getVideojuego() {
		return videojuegos;
	}

	public void setLibros(List<Videojuego> videojuegos) {
		this.videojuegos = videojuegos;
	}

	public List<Videojuego> getVideojuegos() {
		return videojuegos;
	}

	public void setVideojuegos(List<Videojuego> videojuegos) {
		this.videojuegos = videojuegos;
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
		return nombre;
	}

}
