
package uniandes.dpoo.consola;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

import uniandes.dpoo.modelo.Combo;
import uniandes.dpoo.modelo.Ingrediente;
import uniandes.dpoo.modelo.Pedido;
import uniandes.dpoo.modelo.ProductoAjustado;
import uniandes.dpoo.modelo.ProductoMenu;
import uniandes.dpoo.modelo.Restaurante;
import uniandes.dpoo.procesamiento.LoaderInformacionArchivos;
import uniandes.dpoo.procesamiento.Producto;

public class Aplicacion {

	private static Restaurante restaurante= new Restaurante();
	
	public String input(String mensaje)//Este metodo sirve para solicitar informacion al usuario mediante la consola.
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

	private void imprimirProductosMenu(Restaurante rest) {
		ArrayList<ProductoMenu> productos_menu=rest.getMenuBase();
		int i = 0;
		System.out.println("**PRODUCTOS DEL MENÚ**");
		for (ProductoMenu prodMenu : productos_menu) {
			System.out.println(Integer.toString(i)+". "+prodMenu.Generar_texto_factura());
			i++;
			}
	}
		
	private void imprimirIngredientes(Restaurante rest) {
		ArrayList<Ingrediente> ingredientes=rest.getIngredeintes();
		int i = 0;
		System.out.println("**INGREDIENTES PARA AJUSTES**");
		for (Ingrediente ingrediente : ingredientes) {
			System.out.println(Integer.toString(i)+". "+ingrediente.Generar_texto_factura());
			i++;
			}
	}
	
	private void imprimirCombos(Restaurante rest) {
		ArrayList<Combo> combos_menu=rest.getCombos();
		int i = 0;
		System.out.println("**COMBOS**");
		for (Combo combo : combos_menu) {
			System.out.println(Integer.toString(i)+". "+combo.Generar_texto_factura());
			i++;
			}
	}
	
	private void imprimirCesta(ArrayList<Producto> cesta) {
		System.out.println("**Cesta**");
		System.out.println("\n");
		int i = 0;
		for (Producto elem : cesta) {
			System.out.println(Integer.toString(i)+". "+ elem.Generar_texto_factura());
			i++;
		}
	}
	
	private void MostrarMenu(Restaurante rest) {//Muestra el menu, identificador de cada producto, y su precio.
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
	
	private void primerFiltro(int primerFiltro) {
		if (primerFiltro == 1) {
			imprimirProductosMenu(restaurante);
			int pos = Integer.parseInt(input("Por favor escriba el número del producto que desea añadir"));
			ArrayList<ProductoMenu> estructura = restaurante.getMenuBase();
			ProductoMenu aAgregar = estructura.get(pos);
			restaurante.getPedidoEnCurso().agregarProducto(aAgregar);
			System.out.println("Se agregó exitosamente el producto seleccionado");
			System.out.println("\n");
		}
		if (primerFiltro == 2) {
			imprimirCombos(restaurante);
			int pos = Integer.parseInt(input("Por favor escriba el número del combo que desea añadir"));
			ArrayList<Combo> estructura = restaurante.getCombos();
			Combo aAgregar = estructura.get(pos);
			restaurante.getPedidoEnCurso().agregarProducto(aAgregar);
			System.out.println("Se agregó exitosamente el combo seleccionado");
			System.out.println("\n");
		}
		if (primerFiltro == 3) {
			imprimirProductosMenu(restaurante);
			int posInPedido = Integer.parseInt(input("Por favor escriba el número del producto que desea ajustar"));
			ArrayList<ProductoMenu> segundaEstructura = restaurante.getMenuBase();
			ProductoMenu aModificar = segundaEstructura.get(posInPedido);
			ProductoAjustado productoAjustado = new ProductoAjustado(aModificar);
			boolean cicle = true;
			while (cicle){
				imprimirIngredientes(restaurante);
				int pos = Integer.parseInt(input("Por favor escriba el número del ingrediente"));
				ArrayList<Ingrediente> estructura = restaurante.getIngredeintes();
				Ingrediente aAgregar = estructura.get(pos);
				int eleccion = Integer.parseInt(input("Inserte 1 para adicionar o 2 para quitar"));
				if (eleccion == 1) {
					productoAjustado.agregados.add(aAgregar);
				}
				else if (eleccion == 2){
					productoAjustado.eliminados.add(aAgregar);
				}
				int decCiclo = Integer.parseInt(input("Inserte 1 si quiere seguir ajustando o 2 para agregar el producto ajustado a su pedido"));
				if (decCiclo == 2) {
					restaurante.getPedidoEnCurso().agregarProducto(productoAjustado);
					System.out.println("Se agregó exitosamente el producto ajustado");
					System.out.println("\n");
					cicle = false;
				}
			}
		}
	}
	
	private void crearPedido() {
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
	
	private void agregarProducto() {
		Pedido pedidoActual = restaurante.getPedidoEnCurso();
		if (pedidoActual != null) {
			int primerFiltro = Integer.parseInt(input("Ingrese 1 para agregar un producto del menu, 2 para un combo o 3 para un producto ajustado"));
			primerFiltro(primerFiltro);
		}
	}
	
	private void buscarPedido() throws IOException {
		Integer id = Integer.parseInt(input("Digite el id del pedido que desea buscar"));
		Pedido pedido = restaurante.pedidos.get(id);
		if (pedido == null) {
			System.out.println("No existe ningún pedido que concuerde con el id ingresado");
		}
		else {
			System.out.println("Búsqueda exitosa");
			pedido.guardarFactura();
			System.out.println("Por favor refresque el directorio y verá su factura en el archivo " +  id + ".txt");
		}
	}

	
	private void ejecutarOpcion(int opcionSeleccionada) throws IOException {
		if (opcionSeleccionada == 1) {
			MostrarMenu(restaurante);
		}
		if (opcionSeleccionada == 2) {
			crearPedido();
		}
		if (opcionSeleccionada == 3) {
			agregarProducto();
		}	
		if (opcionSeleccionada == 4) {
			System.out.println("El pedido se guardó existosamente con el id " + String.valueOf(restaurante.getPedidoEnCurso().getIdPedido()));
			restaurante.cerrarYGuardarPedido(); 
		}
		if (opcionSeleccionada == 5) {
			buscarPedido();
		}
			
	}
	
	public void ejecutarAplicacion() throws FileNotFoundException, IOException {
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
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}

}