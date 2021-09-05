
package uniandes.dpoo.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.modelo.Combo;
import uniandes.dpoo.modelo.Ingrediente;
import uniandes.dpoo.modelo.Pedido;
import uniandes.dpoo.modelo.ProductoMenu;
import uniandes.dpoo.modelo.Restaurante;
import uniandes.dpoo.procesamiento.LoaderInformacionArchivos;
import uniandes.dpoo.procesamiento.Producto;

public class Aplicacion {

	private static Restaurante restaurante= new Restaurante();
	
	public static String input(String mensaje)//Este metodo sirve para solicitar informacion al usuario mediante la consola.
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public static void imprimirProductosMenu(Restaurante rest) {
		ArrayList<ProductoMenu> productos_menu=rest.getMenuBase();
		int i = 0;
		System.out.println("**PRODUCTOS DEL MENÚ**");
		for (ProductoMenu prodMenu : productos_menu) {
			System.out.println(Integer.toString(i)+". "+prodMenu.Generar_texto_factura());
			i++;
			}
	}
		
	public static void imprimirIngredientes(Restaurante rest) {
		ArrayList<Ingrediente> ingredientes=rest.getIngredeintes();
		int i = 0;
		System.out.println("**INGREDIENTES PARA ADICIONES**");
		for (Ingrediente ingrediente : ingredientes) {
			System.out.println(Integer.toString(i)+". "+ingrediente.Generar_texto_factura());
			i++;
			}
	}
	
	public static void imprimirCombos(Restaurante rest) {
		ArrayList<Combo> combos_menu=rest.getCombos();
		int i = 0;
		System.out.println("**COMBOS**");
		for (Combo combo : combos_menu) {
			System.out.println(Integer.toString(i)+". "+combo.Generar_texto_factura());
			i++;
			}
	}
	
	public static void imprimirCesta(ArrayList<Producto> cesta) {
		int i = 0;
		for (Producto elem : cesta) {
			System.out.println(Integer.toString(i)+". "+ Producto.Generar_texto_factura());
			i++;
		}
	}
	
	public static void MostrarMenu(Restaurante rest) {//Muestra el menu, identificador de cada producto, y su precio.
		System.out.println("\n");
		System.out.println("----------------------T O D O S  L O S  P R O D U C T O S----------------------");
		System.out.println("\n");
		imprimirProductosMenu(rest);
		System.out.println("\n");
		imprimirIngredientes(rest);
		System.out.println("\n");
		imprimirCombos(rest);
		System.out.println("\n");
	}
	
	public static void primerFiltro(int primerFiltro) {
		if (primerFiltro == 1) {
			imprimirProductosMenu(restaurante);
			int pos = Integer.parseInt(input("Por favor escriba el número del producto que desea añadir"));
			ArrayList<ProductoMenu> estructura = restaurante.getMenuBase();
			ProductoMenu aAgregar = estructura.get(pos);
			restaurante.getPedidoEnCurso().agregarProducto(aAgregar);
		}
		if (primerFiltro == 2) {
			imprimirCombos(restaurante);
			int pos = Integer.parseInt(input("Por favor escriba el número del combo que desea añadir"));
			ArrayList<Combo> estructura = restaurante.getCombos();
			Combo aAgregar = estructura.get(pos);
			restaurante.getPedidoEnCurso().agregarProducto(aAgregar);
		}
		if (primerFiltro == 3) {
			if (restaurante.getPedidoEnCurso().itemsPedido.isEmpty()) {
				System.out.println("No puede hacer una adición, ya que no hay ningún producto en su pedido.\nPor favor agregue un producto antes para poder hacer la adición.");
			}
			else {
				
			}
		}
	}
	
	private static void crearPedido() {
		if (restaurante.getPedidoEnCurso() != null) {
			System.out.println("No puede hacer un nuevo pedido mientras tenga uno en curso.\nPor favor cierre el actual para poder hacer uno nuevo.");
		}
		else {
			String nombreCliente = input("Ingrese el nombre de quien pide");
			String direccionCliente = input("Ingrese la dirección de quien pide");
			restaurante.iniciarPedido(nombreCliente, direccionCliente);
			System.out.println("Pedido creado exitosamente");
		}
	}
	
	private static void agregarProducto() {
		Pedido pedidoActual = restaurante.getPedidoEnCurso();
		if (pedidoActual != null) {
			int primerFiltro = Integer.parseInt(input("Ingrese 1 para agregar un producto del menu, 2 para un combo o 3 para hacer una adición de un ingrediente"));
			primerFiltro(primerFiltro);
		}
	}
	
	public static void ejecutarOpcion(int opcionSeleccionada) {
		if (opcionSeleccionada == 1)
			MostrarMenu(restaurante);
		if (opcionSeleccionada == 2)
			crearPedido();
		if (opcionSeleccionada == 3)
			agregarProducto();
		if (opcionSeleccionada == 4)
			System.out.print(restaurante.pedidoEnCurso.generarTextoFactura());
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inicio de ejecución de la aplicación");
		boolean correr_app=true;		
		restaurante.CargarInformacionRestaurante();
		while (correr_app) {
			System.out.println("Menu de opciones:");
			System.out.println("1. Mostrar menu"+"\n"+"2. Nuevo pedido"+"\n"+"3. Agregar elemento al pedido actual"+"\n"+"4. Cerrar pedido y guardar factura"+"\n"+"5. Consultar info de un pedido"+"\n"+"6. Salir de la aplicacion");
			try {
				String s = input("Digite el numero de la opcion que desea seleccionar");
				ejecutarOpcion(Integer.parseInt(s));
			} catch (Exception e) {
				System.out.println("Ingrese un valor valido.");
			}
		}
		
	}

}