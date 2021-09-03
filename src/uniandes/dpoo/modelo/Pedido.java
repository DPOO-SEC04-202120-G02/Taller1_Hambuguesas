package uniandes.dpoo.modelo;
import java.util.*;

public class Pedido {
	private static int numeroPedidos;
	
	private int idPedido;
	
	private String nombreCliente;
	
	private String direccionCliente;
	
	private List<Producto> itemsPedido;
	
	
	public Pedido(String nombreCliente, String direccionCliente) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		itemsPedido = new ArrayList<Producto>();
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
			precio += producto.getPrecio();
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
	
	private String generarTextoFactura() {
		String imprimible = "";
		for (Producto producto : itemsPedido)
		{
			imprimible += producto.generarTextoFactura();
			imprimible += "\n";
		}
	}
	
}

