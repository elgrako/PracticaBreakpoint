/**
 * 
 */
package mensajes;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase proporciona métodos para mostrar difrentes tipos de mensajes: de
 * bienvenida, opciones de menú y un mensaje de despedida. Esta clase se centra
 * en la interacción con el usuario.
 * 
 * @author 
 * @version 1.0
 */
public class Mensajes {

	/**
	 * Muestra un mensaje inicial de bienvenida a la tienda virtual.
	 */
	public static void Mensaje_Inicial() {
		System.out.println(
				"Bienvenido a la tienda virtual\n\n" + "El programa simula una tienda que vende juegos, música y cine\n"
						+ "Solamente se pueden vender productos si aparecen disponibles en la tienda\n");
	}

	/**
	 * Muestra las opciones del menú disponibles.
	 */
	public static void Opciones_Menu() {

		System.out.println("\nSeleccione que desea realizar:\n\n" + "\t1. Mostrar productos. (Listar productos)\n"
				+ "\t2. Comprar productos\n" + "\t3. Mostrar caja. (Importe total de la compra actual)\n"
				+ "\tSALIR --> Pulse cualquier otro número\n");
	}

	/**
	 * Muestra un mensaje de despedida al usuario.
	 */
	public static void Mensaje_Fin() {

		System.out.println("---- Gracias por usar este software. ----");
	}

	/**
	 * Muestra un mensaje indicando que la compra se ha realizado con éxito.
	 */
	public static void Mensaje_Compra() {

		System.out.println("--- Compra realizada con éxito. ---");
	}

	/**
	 * Solicita al usuario que confirme si desea continuar con la compra o
	 * cancelarla.
	 *
	 * @param scanner Scanner para obtener la entrada del usuario.
	 * @return 1 si el usuario desea continuar con la compra, 2 si desea cancelarla.
	 */
	public static int confirmarCompra(Scanner scanner) {
		int respuestaContinuar = 0;
		boolean entradaValida = false;
		while (!entradaValida) {
			System.out.println(
					"¿Desea cancelar la compra o desea continuar? \n1. Continuar con la compra \n2. Cancelar la compra");
			try {
				respuestaContinuar = scanner.nextInt();
				if (respuestaContinuar == 1 || respuestaContinuar == 2) {
					entradaValida = true;
				} else {
					System.out.println("Error: Entrada no válida. Por favor, introduzca 1 o 2.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Entrada no válida. Por favor, introduzca 1 o 2.");
				scanner.nextLine();
			}
		}
		return respuestaContinuar;
	}

	/**
	 * Solicita al usuario que confirme si desea obtener una copia del ticket de
	 * compra.
	 *
	 * @param scanner Scanner para obtener la entrada del usuario.
	 * @return 1 si el usuario desea una copia del ticket, 2 si no la desea.
	 */
	public static int confirmarCopiaTicket(Scanner scanner) {
		boolean entradaValida = false;
		while (!entradaValida) {
			System.out.println("¿Desea copia de su compra? \n1. Sí \n2. No");
			try {
				int respuestaCopia = scanner.nextInt();
				if (respuestaCopia == 1 || respuestaCopia == 2) {
					entradaValida = true;
					return respuestaCopia;
				} else {
					System.out.println("Error: Entrada no válida. Por favor, introduzca 1 o 2.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error: Entrada no válida. Por favor, introduzca 1 o 2.");
				scanner.nextLine();
			}
		}
		return 0;
	}

	/**
	 * Muestra un mensaje de error.
	 * 
	 * @param mensaje Mensaje de error a mostrar.
	 */
	public static void imprimirError(String mensaje) {
		System.out.println("ERROR:" + mensaje);

	}

}
