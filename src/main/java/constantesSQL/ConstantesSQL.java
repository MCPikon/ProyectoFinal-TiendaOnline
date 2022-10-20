package constantesSQL;

public class ConstantesSQL {
	
	public final static String SQL_OBTENER_TOTAL_VIDEOJUEGOS = "SELECT COUNT(id) FROM tabla_videojuegos WHERE nombre LIKE :nombre";
	public final static String SQL_BORRAR_VIDEOJUEGO = "DELETE FROM tabla_videojuegos WHERE id = :id";
	public final static String SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLE = "SELECT id, nombre FROM categoria ORDER BY id ASC";
	public final static String SQL_OBTENER_VIDEOJUEGOS_PARA_LISTADO = "SELECT v.id, v.nombre, v.descripcion, v.precio_videojuego, c.nombre as nombre_categoria FROM tabla_videojuegos as v, categoria as c WHERE v.categoria_id = c.id AND v.alta = 1 AND c.alta = 1 ORDER BY id DESC";
	public static final String SQL_BORRAR_CATEGORIA = "DELETE FROM categoria WHERE id = :id";
	public static final String SQL_OBTENER_VIDEOJUEGO_POR_ID = "SELECT * FROM tabla_videojuegos WHERE id = :id";
	public static final String SQL_OBTENER_PRODUCTOS_CARRITO = "SELECT tv.id as videojuego_id, tv.nombre, tv.precio_videojuego, pc.cantidad FROM tabla_videojuegos as tv, productocarrito as pc WHERE pc.videojuego_id = tv.id AND pc.carrito_id = :carrito_id ORDER BY pc.id ASC";
	public static final String SQL_BORRAR_PRODUCTOCARRITO_ASOCIADO = "DELETE FROM productocarrito WHERE videojuego_id = :id";
	public final static String SQL_BORRAR_PRODUCTO_CARRITO = "DELETE FROM productocarrito WHERE carrito_id = :id_carrito AND videojuego_id = :id_videojuego";
	public final static String SQL_BORRAR_PRODUCTOS_CARRITO = "DELETE FROM productocarrito WHERE carrito_id = :carrito_id";
	public final static String SQL_OBTENER_USUARIO_POR_ID = "SELECT * FROM usuario WHERE id = :id";
	public final static String SQL_OBTENER_PEDIDOS_POR_ID_USUARIO = "SELECT * FROM pedido WHERE usuario_id = :usuario_id";
	public final static String SQL_OBTENER_PRODUCTOS_PEDIDO = "select tabla_videojuegos.id,tabla_videojuegos.nombre,tabla_videojuegos.precio_videojuego,productopedido.cantidad from productopedido,tabla_videojuegos where productopedido.pedido_id = :pedido_id and tabla_videojuegos.id = productopedido.videojuego_id";

}
