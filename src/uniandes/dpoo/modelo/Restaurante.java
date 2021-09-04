package uniandes.dpoo.modelo;
import java.util.*;

public class Restaurante {
	
	public Pedido pedidoEnCurso;
	
	private Map<Integer, Pedido> pedidos;
	
	private List<Combo> combos;
	
	private HashMap<String, ProductoMenu> menuBase;
	
	private List<Ingrediente> ingredientes;
	
	
	public Restaurante(ArrayList<Combo> combos, ArrayList<ProductoMenu> menuBase, ArrayList<Ingrediente> ingredientes) {
		pedidos = new Hashtable<Integer, Pedido>();
		this.combos = combos;
		this.menuBase = new HashMap<String, ProductoMenu>();
		this.ingredientes = ingredientes;
		for (ProductoMenu i : menuBase) {
			this.menuBase.put(i.get_nombre(), i);
		}
	}
	
	
	public void iniciarPedido(String nombre, String direccionCliente) {
		pedidoEnCurso = new Pedido(nombre, direccionCliente);
	}
	
	public void cerrarYGuardarPedido() {
		
		pedidoEnCurso = null;
	}
	
	
	
	
	
	

}
