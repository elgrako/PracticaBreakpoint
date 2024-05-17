/**
 * 
 */
package data;

/**
 * @author 
 * @version 0.4
 */
public class Videojuegos extends Productos {
	// Atributo
	private String plataforma;

	// Constructores
	public Videojuegos() {

		}

	public Videojuegos(String nombre, double precioUnit, int cantStock, boolean disponible, String genero,int codigoBarras, String plataforma) {
			super(nombre, precioUnit, cantStock, disponible, genero, codigoBarras);
			this.setPlataforma(plataforma);
		}

	// Methods
	@Override
	public String toString() {
		return "PRODUCTO VIDEOJUEGO\n" + "Plataforma: " + this.plataforma;
	}


	// Gets and Sets
	/**
	 * @return the plataforma
	 */
	public String getPlataforma() {
		return plataforma;
	}

	/**
	 * @param plataforma the plataforma to set
	 */
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}



}
