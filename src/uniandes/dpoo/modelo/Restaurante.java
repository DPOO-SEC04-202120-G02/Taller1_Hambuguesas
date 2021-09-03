package uniandes.dpoo.modelo;
import java.util.*;

public class Restaurante {
	
	public Pedido pedidoEnCurso;
	
	private Map<Integer, Pedido> pedidos;
	
	private List<Combo> combos;
	
	private List<ProductoMenu> menuBase;
	
	private List<Ingrediente> ingredientes;
	
	
	public Restaurante(ArrayList<Combo> combos, ArrayList<ProductoMenu> menuBase, ArrayList<Ingrediente> ingredientes) {
		pedidos = new Hashtable<Integer, Pedido>();
		this.combos = combos;
		this.menuBase = menuBase;
		this.ingredientes = ingredientes;
	}
	
	
	public void iniciarPedido(String nombre, String direccionCliente) {
		pedidoEnCurso = new Pedido(nombre, direccionCliente);
	}
	
	public void cerrarYGuardarPedido() {
		
		pedidoEnCurso = null;
	}
	
	
	
	
	
	

}
