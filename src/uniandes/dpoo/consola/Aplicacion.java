package uniandes.dpoo.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.modelo.Combo;
import uniandes.dpoo.modelo.Ingrediente;
import uniandes.dpoo.modelo.ProductoMenu;
import uniandes.dpoo.modelo.Restaurante;
import uniandes.dpoo.procesamiento.LoaderInformacionArchivos;;

public class Aplicacion {

	public  static String input(String mensaje)//Este metodo sirve para solicitar informacion al usuario mediante la consola.
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

	public static void MostrarMenu(Restaurante rest) {//Muestra el menu, identificador de cada producto, y su precio.
		ArrayList<ProductoMenu> productos_menu=rest.getMenuBase();
		int i = 0;
		System.out.println("Productos basicos:");
		for (ProductoMenu prodMenu : productos_menu) {
			System.out.println(Integer.toString(i)+". "+prodMenu.Generar_texto_factura());
			i++;
			}
		
		ArrayList<Ingrediente> ingredientes=rest.getIngredeintes();
		i = 0;
		System.out.println("Ingredeintes para los productos basicos:");
		for (Ingrediente ingrediente : ingredientes) {
			System.out.println(Integer.toString(i)+". "+ingrediente.Generar_texto_factura());
			i++;
			}
		
		ArrayList<Combo> combos_menu=rest.getCombos();
		i = 0;
		System.out.println("Combos:");
		for (Combo combo : combos_menu) {
			System.out.println(Integer.toString(i)+". "+combo.Generar_texto_factura());
			i++;
			}
	}
	
	public static void ejecutar_opcion(int opcion_seleccionada) {
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inicio de ejecución de la aplicación");
		//Aplicacion consola = new Aplicacion();
		Restaurante restaurante= new Restaurante();
		boolean correr_app=true;		
		restaurante.CargarInfomracionRestaurante();
		MostrarMenu(restaurante);
	}

}
