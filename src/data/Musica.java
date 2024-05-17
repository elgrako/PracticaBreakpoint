/**
 * 
 */
package data;

/**
 * @author 
 * @version 0.4
 */
public class Musica extends Productos {
	// Atributo
	private String autor;// Autor de la canción

	// Constructores
	public Musica() {
		
	}

	public Musica(String nombre, double precioUnit, int cantStock, boolean disponible, String genero,int codigoBarras, String autor) {
		super(nombre, precioUnit, cantStock, disponible, genero, codigoBarras);
		this.autor = autor;
	}

	// Methods
	@Override
	public String toString() {
		return "PRODUCTO MUSICA\n" + "Autor: " + this.autor;
	}

	// Gets and Sets
	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

}
