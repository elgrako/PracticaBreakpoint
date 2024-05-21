/**
 * 
 */
package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import data.Cine;
import data.Musica;
import data.Productos;
import data.Videojuegos;
import mensajes.Mensajes;
import store.Ficheros;

/**
 * Clase que gestiona los productos disponibles en la tienda. Permite cargar
 * productos, mostrarlos, y realizar compras.
 * 
 * @author Lucas
 * @version 1.3
 */

public class GestionProductos {
	private TreeMap<Integer, Productos> catalogo = new TreeMap<>();
	private List<Productos> productosSeleccionados = new ArrayList<>();
	private List<Integer> cantidadesSeleccionadas = new ArrayList<>();
	private List<Double> importesTotales = new ArrayList<>();

	/**
	 * Carga productos en el catálogo.
	 * 
	 * @param catalogo Catálogo de productos a cargar.
	 */
	public void cargarProductos(TreeMap<Integer, Productos> catalogo) {
		Productos disco1 = new Musica("Feelings", 18.53, 6, true, "Pop", generarCodigoBarras(), "Lauv");
		Productos disco2 = new Musica("Enchanted", 21.2, 20, true, "Pop", generarCodigoBarras(), "Taylor Swift");
		Productos cine1 = new Cine("Willy Wonka", 30.2, 5, true, "Fantasía", generarCodigoBarras(), "Tim Burton");
		Productos juego1 = new Videojuegos("Mario Bros", 35.11, 0, false, "Plataformas", generarCodigoBarras(),
				"Nintendo");

		catalogo.put(disco1.getCodigoBarras(), disco1);
		catalogo.put(disco2.getCodigoBarras(), disco2);
		catalogo.put(cine1.getCodigoBarras(), cine1);
		catalogo.put(juego1.getCodigoBarras(), juego1);

		this.catalogo = catalogo;
	}

	/**
	 * Muestra la lista de productos disponibles
	 * 
	 */
	public void mostrarProductos() {
		int contador = 1;
		for (Map.Entry<Integer, Productos> entry : catalogo.entrySet()) {
			Productos producto = entry.getValue();
			System.out.println("PRODUCTO " + contador + "\nCódigo de barras: " + entry.getKey() + "\nNombre: "
					+ producto.getNombre() + "\nPrecio unitario: " + producto.getPrecioUnit() + " euros"
					+ "\nCantidad en stock: " + producto.getCantStock() + "\nGénero: " + producto.getGenero());

			if (producto instanceof Musica) {
				Musica musica = (Musica) producto;
				System.out.println("Autor: " + musica.getAutor());
			} else if (producto instanceof Cine) {
				Cine cine = (Cine) producto;
				System.out.println("Director: " + cine.getDirector());
			} else if (producto instanceof Videojuegos) {
				Videojuegos videojuego = (Videojuegos) producto;
				System.out.println("Plataforma: " + videojuego.getPlataforma());
			}
			System.out.println();
			contador++;
		}
		System.out.println("Lista de códigos de productos:");
		for (Productos productos : catalogo.values()) {
			System.out.println(productos.getCodigoBarras());
		}
	}

	/**
	 * Permite al usuario seleccionar productos y realizar la compra.
	 * 
	 * @param scanner Scanner para la entrada del usuario.
	 */
	public void comprarProductos(Scanner scanner) {
		seleccionarProducto(scanner);

		String contenidoTicket = generarContenidoTicket();
		int respuesta = Mensajes.confirmarCompra(scanner);

		if (respuesta == 1) {
			respuesta = Mensajes.confirmarCopiaTicket(scanner);
			Ficheros.escrituraTicket(contenidoTicket, respuesta);
			Mensajes.Mensaje_Compra();
			// Agregar el importe total de la compra actual a la lista de importes totales
			importesTotales.add(calcularImporteCompraActual());
		} else {
			System.out.println("Compra cancelada. No se ha generado el ticket de compra.");
		}

		productosSeleccionados.clear();
		cantidadesSeleccionadas.clear();
	}

	/**
	 * Método privado para seleccionar productos y la cantidad a comprar.
	 * 
	 * @param scanner Scanner para la entrada del usuario.
	 * @return Cantidad de productos seleccionada por el usuario.
	 */
	private void seleccionarProducto(Scanner scanner) {
		boolean seguirComprando = true;
		while (seguirComprando) {
			System.out.println("\nSeleccione el código del producto que desea comprar. (0 para finalizar la compra):");
			int codigoProducto = scanner.nextInt();
			if (codigoProducto == 0) {
				seguirComprando = false;
			} else {
				Productos productoSeleccionado = catalogo.get(codigoProducto);
				if (productoSeleccionado != null) {
					int cantidadCompra = seleccionarCantidad(scanner, productoSeleccionado);
					if (cantidadCompra > 0) {
						double totalProducto = agregarProductoCarrito(productoSeleccionado, cantidadCompra);
						System.out.println("\nProducto añadido al carrito: " + productoSeleccionado.getNombre());
						System.out.println("Cantidad: " + cantidadCompra);
						System.out.println("Total de la compra actual: " + totalProducto);
					} else {
						Mensajes.imprimirError("Cantidad inválida. Inténtelo de nuevo.");
					}
				} else {
					Mensajes.imprimirError("El código del producto no es válido.");
				}
			}
		}
	}

	/**
	 * Método privado para seleccionar la cantidad de productos a comprar.
	 * 
	 * @param scanner  Scanner para la entrada del usuario.
	 * @param producto Producto seleccionado por el usuario.
	 * @return Cantidad de productos a comprar.
	 */
	private int seleccionarCantidad(Scanner scanner, Productos producto) {
		System.out.println("¿Cuántos productos desea añadir?: ");
		int cantidad = scanner.nextInt();
		if (cantidad <= producto.getCantStock()) {
			return cantidad;
		} else {
			Mensajes.imprimirError("Cantidad insuficiente en stock.");
			return -1;
		}
	}

	/**
	 * Método privado para agregar productos al carrito de compra.
	 * 
	 * @param producto       Producto a agregar al carrito.
	 * @param cantidadCompra Cantidad de productos a comprar.
	 * @return Precio total de los productos agregados al carrito.
	 */
	private double agregarProductoCarrito(Productos producto, int cantidadCompra) {
		double precioTotal = cantidadCompra * producto.getPrecioUnit();
		productosSeleccionados.add(producto);
		cantidadesSeleccionadas.add(cantidadCompra);
		producto.setCantStock(producto.getCantStock() - cantidadCompra);
		return precioTotal;
	}

	private double calcularImporteCompraActual() {
		double importeCompraActual = 0.0;
		for (int i = 0; i < productosSeleccionados.size(); i++) {
			Productos producto = productosSeleccionados.get(i);
			int cantidad = cantidadesSeleccionadas.get(i);
			double precioTotalProducto = producto.getPrecioUnit() * cantidad;
			importeCompraActual += precioTotalProducto;
		}
		return importeCompraActual;
	}

	/**
	 * Método privado para generar el contenido del ticket de compra.
	 * 
	 * @param cantidadCompra Cantidad de productos comprados.
	 * @return Contenido del ticket de compra.
	 */
	private String generarContenidoTicket() {
		StringBuilder contenido = new StringBuilder("\n------------------------------------------\n");
		contenido.append("            TICKET DE COMPRA\n");
		contenido.append("------------------------------------------\n");
		double totalCompra = 0;
		for (int i = 0; i < productosSeleccionados.size(); i++) {
			Productos producto = productosSeleccionados.get(i);
			int cantidad = cantidadesSeleccionadas.get(i);
			double precioTotalProducto = producto.getPrecioUnit() * cantidad;
			totalCompra += precioTotalProducto;
			contenido.append("Producto: ").append(producto.getNombre()).append("\n").append("Cantidad: ")
					.append(cantidad).append("\n").append("Precio unitario: ").append(producto.getPrecioUnit())
					.append("€\n").append("Precio total: ").append(precioTotalProducto).append("€\n\n");
		}
		contenido.append("------------------------------------------\n");
		contenido.append("TOTAL: ").append(totalCompra).append("€\n");
		contenido.append("------------------------------------------\n");
		return contenido.toString();
	}

	/**
	 * Obtiene el catálogo de productos.
	 * 
	 * @return Catálogo de productos.
	 */
	public TreeMap<Integer, Productos> getCatalogo() {
		return catalogo;
	}

	/**
	 * Devuelve un numero random.
	 * 
	 * @return Número random de 6 cifras
	 */
	public int generarCodigoBarras() {
		Random random = new Random();
		return 100000 + random.nextInt(900000);
	}

	public double calcularImporteTotal() {
		double importeTotal = 0.0;
		for (double importe : importesTotales) {
			importeTotal += importe;
		}
		return importeTotal;
	}
}
