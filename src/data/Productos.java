/**
 * 
 */
package data;

/**
 * @author 
 * @version 0.4
 *
 */
public abstract class Productos {
	// Atributos
	private String nombre;
	private double precioUnit;
	private int cantStock;
	private boolean disponible = false;
	private String genero;
	private int codigoBarras;

	// Constructores
	/**
	* 
	*/
	public Productos() {

	}

	/**
	 * Constructor clase producto con 4 parametros
	 * 
	 * @param nombre
	 * @param precioUnit
	 * @param cantStock
	 * @param disponible
	 */
	public Productos(String nombre, double precioUnit, int cantStock, boolean disponible, String genero, int codigoBarras) {

		this.nombre = nombre;
		this.precioUnit = precioUnit;
		this.cantStock = cantStock;
		this.disponible = disponible;
		this.setGenero(genero);
		this.setCodigoBarras(codigoBarras);

	}

	/**
	 * Nos devuelve si hay stock de un producto o no
	 * 
	 * @return
	 */
	public boolean isDisponible() {
		if (getCantStock() > 0) {
			this.disponible = true;
		}
		return this.disponible;
	}

	// Métodos Get and Set

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public double getPrecioUnit() {
		return this.precioUnit;
	}

	/**
	 * 
	 * @param precioUnit
	 */
	public void setPrecioUnit(double precioUnit) {
		this.precioUnit = precioUnit;
	}

	/**
	 * 
	 * @return
	 */
	public int getCantStock() {
		return this.cantStock;
	}

	/**
	 * 
	 * @param cantStock
	 */
	public void setCantStock(int cantStock) {
		this.cantStock = cantStock;
	}

	/**
	 * 
	 * @param disponible
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return the codigoBarras
	 */
	public int getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * @param codigoBarras the codigoBarras to set
	 */
	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
}
