/**
 * 
 */
package app;

import logic.GestionProductos;
import java.util.Scanner;
import java.util.TreeMap;
import data.Productos;
import leer.Leer;
import mensajes.Mensajes;

/**
 * Clase principal que contiene el main para ejecutar la aplicación. Esta
 * aplicación contiene un menú interactivo al usuario y le permite seleccionar
 * entre diferentes opciones. Si se introduce una opción no válida, el programa
 * finaliza. Al finalizar la ejecución, se muestra un mensaje de despedida del
 * software.
 *
 * @author 
 * @version 1.0
 */
public class Aplicacion {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Mensajes.Mensaje_Inicial();

		boolean continuar = true;
		TreeMap<Integer, Productos> catalogo = new TreeMap<>();

		GestionProductos gestion = new GestionProductos();
		gestion.cargarProductos(catalogo);

		do {
			Mensajes.Opciones_Menu();

			switch (Leer.datoInt()) {
			case 1:
				System.out.println("Seleccion de productos disponibles".toUpperCase() + "\n");
				gestion.mostrarProductos();
				break;
			case 2:
				gestion.comprarProductos(scanner);
				break;
			case 3:
				// Opción 3
				break;
			default:
				// Se sale del programa
				continuar = false;
			}

		} while (continuar);

		Mensajes.Mensaje_Fin();
		scanner.close();

	}

}