package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tabla_videojuegos")
public class Videojuego {
	
	@OneToOne
	private ProductoCarrito productoCarrito;
	
	@OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL)
	private List<LikeProductoUsuario> likesProductoUsuario = new ArrayList<LikeProductoUsuario>(); 
	
	@NotEmpty(message = "nombre no puede estar vacio")
	@Size(min = 1, max = 60, message = "nombre debe tener entre 1 y 60 caracteres")
	@Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9.:,()\"'-_]{1,60}$", message = "solo letras, n˙meros y espacios")
	private String nombre;
	
	@Column(name = "precio_videojuego")
	@NumberFormat(style = Style.NUMBER, pattern = "#,###.###")
	@Min(value = 1, message = "el precio mÌnimo es un euro")
	private double precio;
	
	@Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9.:,()\"'-_]{1,120}$", message = "solo letras, n˙meros y espacios")
	private String descripcion;
	
	private String estado;
	
	@Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9.:,()\"'-_]{1,60}$", message = "solo letras, n˙meros y espacios")
	private String formato;
	
	@Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9.:,()\"'-_]{1,60}$", message = "solo letras, n˙meros y espacios")
	private String editor;
	
	@Pattern(regexp = "^[a-zA-Z ·ÈÌÛ˙¡…Õ”⁄Ò—0-9.:,()\"'-_]{1,60}$", message = "solo letras, n˙meros y espacios")
	private String plataforma;
	
	private int likes;
	
	private boolean alta;
	
	@Transient //con @Transient, el siguiente campo es ignorado por hibernate a la hora de trabajar con la tabla de la base de datos
	private MultipartFile imagen;
	
	@Transient //con @Transient, el siguiente campo es ignorado por hibernate a la hora de trabajar con la tabla de la base de datos
	private MultipartFile imagen2;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, targetEntity = Categoria.class, optional = false, fetch = FetchType.EAGER)
	private Categoria categoria;
	
	//asociacion a la categoria por el formulario de registro/edicion
	@Transient
	private int idCategoria;
	
	@Id
	@GeneratedValue
	private int id;
	
	public Videojuego() {
		// TODO Auto-generated constructor stub
	}

	public Videojuego(String nombre, double precio, String descripcion, String estado, String formato, String editor,
			String plataforma, boolean alta) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.estado = estado;
		this.formato = formato;
		this.editor = editor;
		this.plataforma = plataforma;
		this.alta = alta;
	}

	public ProductoCarrito getProductoCarrito() {
		return productoCarrito;
	}

	public void setProductoCarrito(ProductoCarrito productoCarrito) {
		this.productoCarrito = productoCarrito;
	}

	public List<LikeProductoUsuario> getLikesProductoUsuario() {
		return likesProductoUsuario;
	}

	public void setLikesProductoUsuario(List<LikeProductoUsuario> likesProductoUsuario) {
		this.likesProductoUsuario = likesProductoUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
	
	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public boolean isAlta() {
		return alta;
	}

	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	public MultipartFile getImagen() {
		return imagen;
	}

	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}

	public MultipartFile getImagen2() {
		return imagen2;
	}

	public void setImagen2(MultipartFile imagen2) {
		this.imagen2 = imagen2;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Videojuego [nombre=" + nombre + ", precio=" + precio + ", descripcion=" + descripcion + ", estado="
				+ estado + ", formato=" + formato + ", editor=" + editor + ", plataforma=" + plataforma + ", alta="
				+ alta + "]";
	}

}
