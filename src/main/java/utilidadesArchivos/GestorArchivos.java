package utilidadesArchivos;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import modelo.Usuario;
import modelo.Videojuego;

public class GestorArchivos {
	
	public static void guardarFotoUsuario(Usuario u, String rutaReal, CommonsMultipartFile foto) {
		String nombreArchivo = u.getId() + ".jpg";
		
		String rutaFotos = rutaReal + "/fotos";
		File fileCarpetaFotos = new File(rutaFotos);
		if(!fileCarpetaFotos.exists()) {
			fileCarpetaFotos.mkdirs();
		}
		
		if(foto.getSize() > 0) {
			try {
				foto.transferTo(new File(rutaFotos, nombreArchivo));
				System.out.println("foto de usuario disponible en: " + rutaFotos);
			} catch (IllegalStateException | IOException e) {
				System.out.println("no se pudo mover la foto a la ruta de subidas");
				e.printStackTrace();
			}
		}else {
			System.out.println("se creó un usuario sin imagen, no hay problema, de momento, la imagen es opcional");
		}
	}//end guardarFotoUsuario
	
	public static void guardarImagenVideojuego(Videojuego v, String rutaReal) {
		
		//si el videojuego recibido aquí ha sido previamente guardado en bd
		//por hibernate, hibernate le ha asignado ya una id
		MultipartFile archivo = v.getImagen();
		String nombreArchivo = v.getId() + ".jpg";
		MultipartFile archivo2 = v.getImagen2();
		String nombreArchivo2 = v.getId() + "D.jpg";
				
		//vamos a crear una carpeta de subidas (si no existe en la ruta real del proyecto) en la ruta real
		String rutaSubidas = rutaReal + "/subidas";
		File fileRutaSubidas = new File(rutaSubidas);
		if(!fileRutaSubidas.exists()) {
			fileRutaSubidas.mkdirs();
		}
				
		//mover el archivo a dicha ruta poniendole el nombre indicado:
		if(archivo.getSize() > 0) {
			try {
				archivo.transferTo(new File(rutaSubidas, nombreArchivo));
				System.out.println("archivo movido a: " + rutaSubidas);	
			} catch (IllegalStateException | IOException e) {
				System.out.println("no pude mover el archivo a la ruta de subidas");
				e.printStackTrace();
			}
		}else {
			System.out.println("se subió un producto sin imagen, no hay problema, de momento, la imagen es opcional");
		}
		
		if(archivo2.getSize() > 0) {
			try {
				archivo2.transferTo(new File(rutaSubidas, nombreArchivo2));
				System.out.println("archivo movido a: " + rutaSubidas);	
			} catch (IllegalStateException | IOException e) {
				System.out.println("no pude mover el archivo a la ruta de subidas");
				e.printStackTrace();
			}
		}else {
			System.out.println("se subió un producto sin imagen, no hay problema, de momento, la imagen es opcional");
		}
		
	}//end guardarImagenVideojuego

}//end class