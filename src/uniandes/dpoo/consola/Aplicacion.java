package uniandes.dpoo.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.modelo.Combo;
import uniandes.dpoo.modelo.ProductoMenu;
import uniandes.dpoo.procesamiento.LoaderInformacionArchivos;;

public class Aplicacion {

	/**
	 * Leer el archivo de Productos Menu y obtener su informacion en una lista de objetos ProductoMenu
	 * @param rutaArchivo
	 * @return Lista de objetos ProductoMenu
	 */
	private ArrayList<ProductoMenu> ejecutarCargarProductosMenu(String rutaArchivo)
	{
		ArrayList<ProductoMenu> productosMenu = new ArrayList<ProductoMenu>();
		try
		{
			productosMenu = LoaderInformacionArchivos.leerInfoArchivoProductosMenu(rutaArchivo);
			System.out.println("OK Se cargó el archivo " + rutaArchivo + " con información de los Productos Menu.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo " + rutaArchivo + " no se encontró.");
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo " + rutaArchivo);
			System.out.println(e.getMessage());
		}

		return productosMenu;
	}

	/**
	 * Leer el archivo de combos y obtener su informacion en una lista de objetos Combo
	 * @param rutaArchivo
	 * @param productosMenu Lista de productos menu donde aparecen todos los posibles productos de un combo
	 * @return Lista de objetos Combo
	 */
	private ArrayList<Combo> ejecutarCargarCombos(String rutaArchivo, ArrayList<ProductoMenu> productosMenu)
	{
		ArrayList<Combo> combos = new ArrayList<Combo>();
		try
		{
			combos = LoaderInformacionArchivos.leerInfoArchivoCombos(rutaArchivo, productosMenu);
			System.out.println("OK Se cargó el archivo " + rutaArchivo + " con información de los Combos.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo " + rutaArchivo + " no se encontró.");
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo " + rutaArchivo);
			System.out.println(e.getMessage());
		}

		return combos;
	}
	
	/**
	 * Este metodo sirve para imprimir un mensaje en la consola pidiendole
	 * informacion al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje El mensaje que se le mostrara al usuario
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	public String input(String mensaje)
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Inicio de ejecución de la aplicación");
		
		Aplicacion consola = new Aplicacion();
		
		ArrayList<ProductoMenu> productosMenu = consola.ejecutarCargarProductosMenu("./data/menu.txt");  // el parametro es la ruta del archivo menu.txt dentro del proyecto Eclipse
		for (ProductoMenu prodMenu : productosMenu)
			System.out.println(prodMenu);

		ArrayList<Combo> combos = consola.ejecutarCargarCombos("./data/combos.txt", productosMenu);  // parametros: ruta del archivo combos.txt y lista de productos menu disponibles
		for (Combo combo : combos)
			System.out.println(combo);

	}

}
