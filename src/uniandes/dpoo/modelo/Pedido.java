package uniandes.dpoo.modelo;
import java.util.*;
import uniandes.dpoo.procesamiento.Producto;


public class Pedido {
	private static int numeroPedidos;
	
	private int idPedido;
	
	private String nombreCliente;
	
	private String direccionCliente;
	
	public ArrayList<Producto> itemsPedido;
	
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		itemsPedido = new ArrayList<Producto>();
		idPedido = numeroPedidos;
		numeroPedidos += 1;
	}
	
	
	public int getIdPedido() {
		return idPedido;
	}
	
	public void agregarProducto(Producto nuevoitem) { 
		itemsPedido.add(nuevoitem);
	}
	
	private int getPrecioTotalPedido() {
		int precio = 0;
		for (Producto producto : itemsPedido)
		{
			precio += producto.get_precio();
		}
		return precio;
	}
	
	private int getPrecioNetoPedido() {
		int precioNeto = (int) (getPrecioTotalPedido() * 0.81);
		return precioNeto;
	}
	
	private int getPrecioIVAPedido() {
		int precioIVA = (int) (getPrecioTotalPedido() * 0.19);
		return precioIVA;
	}
	
	public String generarTextoFactura() { //debería ser private
		String imprimible = "";
		for (Producto producto : itemsPedido)
		{
			imprimible += producto.Generar_texto_factura();
			imprimible += "\n";
		}
		return imprimible;
	}
	
}

