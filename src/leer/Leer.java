/**
 * 
 */
package leer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Esta clase se utiliza para la entrada de datos desde el teclado.
 * 
 * @author
 * @version 1.1
 */
public class Leer {

	public static String dato() {
		String sdato = " ";
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader flujoE = new BufferedReader(isr);
			sdato = flujoE.readLine();
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		return sdato;

	}

	/**
	 * Lee un número entero desde donde fue proporcionado por teclado.
	 * 
	 * @return El número entero leído.
	 */

	public static int datoInt(Scanner scanner) {
		return Integer.parseInt(dato());
	}

	public static int datoInt() {
		int numero = 0;
		try {
			numero = Integer.parseInt(dato().trim());
		} catch (NumberFormatException e) {
			System.out.println("Error: Entrada no válida. Introduzca un número entero.");
		}
		return numero;
	}

	/**
	 * Lee un número decimal desde donde fue proporcionado por teclado.
	 * 
	 * @return El número decimal leído.
	 */
	public static float datoFloat() {
		return Float.parseFloat(dato());
	}

	// Leer un char por teclado
	public static char datoChar() {
		char c = ' ';
		try {
			java.io.BufferedInputStream b = new BufferedInputStream(System.in);
			c = (char) b.read();
		} catch (IOException e) {
			System.out.println("Error al leer");
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Lee un número largo desde donde fue proporionado por teclado.
	 * 
	 * @return El número largo leído.
	 */
	public static long datoLong() {
		return Long.parseLong(dato());
	}
}
