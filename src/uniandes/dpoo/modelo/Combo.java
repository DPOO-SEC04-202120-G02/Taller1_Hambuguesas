package uniandes.dpoo.modelo;

import java.util.ArrayList;

import uniandes.dpoo.procesamiento.Producto;

public class Combo implements Producto {
	private String nombreCombo;
	private double descuento;
	private ArrayList<ProductoMenu> itemsCombo;   
	
	/**
	 * Constructor de la clase
	 */
	public Combo(String nombreP, double descuentoP)
	{
		this.nombreCombo = nombreP;
		this.descuento = descuentoP;
		this.itemsCombo = new ArrayList<ProductoMenu>();    
	}

	/**
	 * Agrega el nombre de un productoMenu a un combo. Metodo simplificado.
	 * @param nombreProducto
	 */
	public void agregarProducto(ProductoMenu producto)
	{
		this.itemsCombo.add(producto);
	}
	public String get_nombre(){
		return this.nombreCombo;
	}
	/**
	 * Permite convertir un objeto Combo a un String 
	 */
	@Override
	public String Generar_texto_factura()
	{
		int precio_net=this.get_precio();
		return "Nombre: "+this.nombreCombo+", Descuento aplicado: "+100*this.descuento+"%, Precio final: "+precio_net;
	}
	
	public int get_precio() {/* Este es el precio antes del descuento */
		int precio_neto=0;
		for (int i = 0; i < itemsCombo.size(); i++) {
			precio_neto+=itemsCombo.get(i).get_precio();
		}
		return precio_neto;
	}
}
