package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class LikeProductoUsuario {
	
	@ManyToOne
	@JoinColumn(name = "videojuego_id")
	private Videojuego videojuego;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Usuario usuario;
	
	@Id
	@GeneratedValue
	private int id;

	public Videojuego getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
