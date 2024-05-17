/**
 * 
 */
package store;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Clase que proporciona métodos para leer la ruta desde un archivo de
 * configuración y para escribir el contenido del ticket en un archivo. La ruta
 * del archivo de configuración se encuentra en una ubicación predefinida.
 * 
 * @author 
 * @version 1.0
 */
public class Ficheros {
	/** Ruta del archivo de configuración. */
	private static final String RUTA_CONFIGURACION = "C:/Users/User/eclipse-workspace/Entornos_Tienda/conf/confFichero.txt";

	/**
	 * Lee la ruta desde el archivo de configuración.
	 * 
	 * @return La ruta obtenida del archivo de configuración.
	 */
	public static String leerRuta() {
		File fichero = new File(RUTA_CONFIGURACION);
		try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.contains(":")) {
					int index = linea.indexOf(":");
					return linea.substring(index + 1).trim();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Escribe el contenido del ticket en un archivo.
	 * 
	 * @param contenido Contenido del ticket a escribir en el archivo.
	 * @param respuesta Respuesta del usuario sobre si desea o no una copia del
	 *                  ticket.
	 */
	public static void escrituraTicket(String contenido, int respuesta) {
		String ruta = leerRuta();
		if (ruta != null) {
			if (respuesta == 1) {
				try (FileWriter fichero = new FileWriter(ruta); PrintWriter pw = new PrintWriter(fichero)) {
					pw.println(contenido);
					System.out.println("Revise su ticket, impresión correcta.");
				} catch (IOException e) {
					System.out.println("Error al imprimir el ticket.");
					e.printStackTrace();
				}
			} else if (respuesta == 2) {
				System.out.println("Ticket de compra:\n" + contenido);
			} else {
				System.out.println("Opción no válida. No se ha generado el ticket de compra.");
			}
		} else {
			System.out.println("No se encontró una ruta válida para el archivo de configuración.");
		}
	}
}
