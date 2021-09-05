package uniandes.dpoo.modelo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import uniandes.dpoo.procesamiento.LoaderInformacionArchivos;


public class Restaurante {
	
	public Pedido pedidoEnCurso;
	
	private Map<Integer, Pedido> pedidos;
	
	private ArrayList<Combo> combos;
	
	private ArrayList<ProductoMenu> menuBase;
	
	private ArrayList<Ingrediente> ingredientes;
	
	private HashMap<String, ProductoMenu> menuBase_por_nombre;
	
	
	public Restaurante() {
		pedidos = new Hashtable<Integer, Pedido>();
		this.combos = new ArrayList<Combo>();
		this.menuBase_por_nombre = new HashMap<String, ProductoMenu>();
		this.menuBase = new ArrayList<ProductoMenu>();
		this.ingredientes = new ArrayList<Ingrediente>();
	}
	
	
	public void iniciarPedido(String nombre, String direccionCliente) {
		pedidoEnCurso = new Pedido(nombre, direccionCliente);
	}
	
	public Pedido getPedidoEnCurso() {
		return this.pedidoEnCurso;
	}
	
	public void cerrarYGuardarPedido() {
		Integer id = pedidoEnCurso.getIdPedido();
		pedidos.put(id, pedidoEnCurso);
		pedidoEnCurso = null;
	}
	
	public ArrayList<ProductoMenu> getMenuBase() {
		return this.menuBase;
	}
	
	public ArrayList<Combo> getCombos() {
		return this.combos;
	}
	
	public ArrayList<Ingrediente> getIngredeintes() {
		return this.ingredientes;
	}
	
	private void CargarMenu() throws FileNotFoundException, IOException {
		
		try
		{
			this.menuBase=LoaderInformacionArchivos.leerInfoArchivoProductosMenu("./data/menu.txt");
			for (ProductoMenu i : this.menuBase) {
				this.menuBase_por_nombre.put(i.get_nombre(), i);
			}
			System.out.println("OK Se cargó el archivo " + "menu.txt" + " con información del menu principal.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo " + "menu.txt" + " no se encontró.");
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo " + "menu.txt");
			System.out.println(e.getMessage());
		}
		
	}
	
	private void CargarCombos() throws FileNotFoundException, IOException {
		
		try
		{
			combos=LoaderInformacionArchivos.leerInfoArchivoCombos("./data/combos.txt",menuBase_por_nombre);
			System.out.println("OK Se cargó el archivo " + "combos.txt" + " con información de los Combos.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo " + "combos.txt" + " no se encontró.");
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo " + "combos.txt");
			System.out.println(e.getMessage());
		}
	
	}
	
	private void CargarIngredientes() throws FileNotFoundException, IOException {	
		try
		{
			this.ingredientes=LoaderInformacionArchivos.leerInfoArchivoIngredientes("./data/ingredientes.txt");
			System.out.println("OK Se cargó el archivo " + "ingredientes.txt" + " con información de los ingredientes.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo " + "ingredientes.txt" + " no se encontró.");
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo " + "ingredientes.txt");
			System.out.println(e.getMessage());
		}
		
	}
	
	public void CargarInformacionRestaurante() throws FileNotFoundException, IOException {
		this.CargarMenu();
		this.CargarCombos();
		this.CargarIngredientes();
	}

	
	
	
	
	
	

}
