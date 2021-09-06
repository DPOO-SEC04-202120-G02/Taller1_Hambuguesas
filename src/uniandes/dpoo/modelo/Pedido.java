package uniandes.dpoo.modelo;
import java.io.*;
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
	
	private String generarTextoFactura() { 
		String imprimible = "";
		for (Producto producto : itemsPedido)
		{
			imprimible += producto.Generar_texto_factura();
			imprimible += "\n";
		}
		return imprimible;
	}
	
	public void guardarFactura() throws IOException {
		File file = new File(idPedido + ".txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println("--------F A C T U R A--------");
		pw.println("Id del pedido: " + idPedido + "\nNombre del cliente: " + nombreCliente + "\nDirección del cliente: " + direccionCliente);
		pw.println("\n\nProductos:");
		pw.println(generarTextoFactura());
		pw.println("\n\n");
		pw.println("Precio Neto: " + getPrecioNetoPedido());
		pw.println("+IVA: " + getPrecioIVAPedido());
		pw.println("--------------------------------------------------------");
		pw.println("Precio total: " + getPrecioTotalPedido());
		
	
		
		pw.close();
	} 
	
}

