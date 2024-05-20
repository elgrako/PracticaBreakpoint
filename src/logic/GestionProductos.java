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
 * @version 1.2
 */
public class GestionProductos {

	private TreeMap<Integer, Productos> catalogo = new TreeMap<>();
	private List<Productos> productosSeleccionados = new ArrayList<>();

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
					+ "\nCantidad en stock: " + producto.getCantStock() + "\nGenero: " + producto.getGenero());

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
			contador = contador + 1;
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
		int cantidadCompra = seleccionarProducto(scanner);

		String contenidoTicket = generarContenidoTicket(cantidadCompra);
		int respuesta = Mensajes.confirmarCompra(scanner);

		if (respuesta == 1) {
			respuesta = Mensajes.confirmarCopiaTicket(scanner);
			Ficheros.escrituraTicket(contenidoTicket, respuesta);
			Mensajes.Mensaje_Compra();
		} else {
			System.out.println("Compra cancelada. No se ha generado el ticket de compra.");
		}

		productosSeleccionados.clear();
	}

	/**
	 * Método privado para seleccionar productos y la cantidad a comprar.
	 * 
	 * @param scanner Scanner para la entrada del usuario.
	 * @return Cantidad de productos seleccionada por el usuario.
	 */
	private int seleccionarProducto(Scanner scanner) {
		boolean seguirComprando = true;
		int cantidadCompra = 0;
		
		while (seguirComprando) {
			System.out.println("\nSeleccione el código del producto que desea comprar.  (0 para finalizar la compra):");
			int codigoProducto = scanner.nextInt();

			if (codigoProducto == 0) {
				seguirComprando = false;
			} else {
				Productos productoSeleccionado = catalogo.get(codigoProducto);
				if (productoSeleccionado != null) {
					cantidadCompra = seleccionarCantidad(scanner, productoSeleccionado);
					if (cantidadCompra > 0) {
						double totalCompra = agregarProductoCarrito(productoSeleccionado, cantidadCompra);
						System.out.println("\nProducto añadido al carrito: " + productoSeleccionado.getNombre());
						System.out.println("Cantidad: " + cantidadCompra);
						System.out.println("Total de la compra actual: " + totalCompra);
					} else {
						Mensajes.imprimirError("Cantidad inválida. Inténtelo de nuevo.");
					}
				} else {
					Mensajes.imprimirError("El código del producto no es válido.");
				}
			}
		}
		return cantidadCompra;
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
		return scanner.nextInt();
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
		producto.setCantStock(producto.getCantStock() - cantidadCompra);
		return precioTotal;
	}

	/**
	 * Método privado para generar el contenido del ticket de compra.
	 * 
	 * @param cantidadCompra Cantidad de productos comprados.
	 * @return Contenido del ticket de compra.
	 */
	private String generarContenidoTicket(int cantidadCompra) {
		StringBuilder contenido = new StringBuilder("\n------------------------------------------\n");
		contenido.append("            TICKET DE COMPRA\n");
		contenido.append("------------------------------------------\n");
		double totalCompra = 0;
		for (Productos producto : productosSeleccionados) {
			double precioTotalProducto = producto.getPrecioUnit() * cantidadCompra;
			totalCompra += precioTotalProducto;
			contenido.append("Producto: ").append(producto.getNombre()).append("\n").append("Cantidad: ")
					.append(cantidadCompra).append("\n").append("Precio unitario: ").append(producto.getPrecioUnit())
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
	    for (Productos producto : catalogo.values()) {
	        importeTotal += producto.getPrecioUnit() * producto.getCantStock();
	    }
	    return importeTotal;
	}


}