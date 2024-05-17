/**
 * 
 */
package data;

/**
 * @author 
 * @version 0.4
 */
public class Cine extends Productos {

	// Atributo
	private String director;

	// Constructores
	public Cine() {

	}

	public Cine(String nombre, double precioUnit, int cantStock, boolean disponible, String genero,int codigoBarras, String director) {
		super(nombre, precioUnit, cantStock, disponible, genero, codigoBarras);
		this.setDirector(director);
	}

	// Methods
	@Override
	public String toString() {
		return "PRODUCTO CINE\n" + "Director: " + this.director;
	}

	// Gets and Sets
	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(String director) {
		this.director = director;
	}


}
