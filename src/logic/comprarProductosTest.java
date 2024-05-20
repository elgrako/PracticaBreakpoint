package logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import data.Musica;
import data.Productos;

class comprarProductosTest {

	@Test
	public void testComprarProducto() {
		GestionProductos gestionProductos = new GestionProductos();
		TreeMap<Integer, Productos> catalogo = new TreeMap<>();
		catalogo.put(1, new Musica("Feelings", 18.53, 6, true, "Pop", 1, "Lauv"));

		Scanner scanner = new Scanner("1\n1\n0\n1\n1\n");
		gestionProductos.comprarProductos(scanner);

		Productos productoComprado = catalogo.get(1);
		assertEquals(6, productoComprado.getCantStock());
	}

	@Test
	public void testCancelarCompra() {
		GestionProductos gestionProductos = new GestionProductos();
		TreeMap<Integer, Productos> catalogo = new TreeMap<>();
		catalogo.put(1, new Musica("Feelings", 18.53, 6, true, "Pop", 1, "Lauv"));
		gestionProductos.cargarProductos(catalogo);

		Scanner scanner = new Scanner("1\n1\n0\n2\n1");
		gestionProductos.comprarProductos(scanner);

		Productos productoNoComprado = catalogo.get(1);
		assertEquals(5, productoNoComprado.getCantStock());
	}
}
