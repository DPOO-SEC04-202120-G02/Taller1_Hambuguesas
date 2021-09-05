package uniandes.dpoo.modelo;
import uniandes.dpoo.procesamiento.Producto;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombreP, int precioP)
	{
		this.nombre = nombreP;
		this.precioBase = precioP;
	}
	
	/**
	 * Permite convertir un objeto ProductoMenu a un String 
	 */
	@Override
	public String Generar_texto_factura()
	{
		return "Nombre: " + this.nombre + ", " + "Precio: $" + this.precioBase;
	}
	public String get_nombre(){
		return this.nombre;
	}
	
	public int get_precio(){
		return this.precioBase;
	}
	
}
