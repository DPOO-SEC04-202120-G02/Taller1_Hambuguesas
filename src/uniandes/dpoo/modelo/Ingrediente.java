package uniandes.dpoo.modelo;

public class Ingrediente {
public class Ingrediente{
	
	String nombre;
	//HOLAAAAAAA
	int costoAdicional;
	private String nombre;
	private int costoAdicional;
	
	
	public Ingrediente(String nombre, int costoAdicional) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	public Ingrediente(String nombre_input, int costoAdicional_input) {
		// TODO Auto-generated constructor stub
		nombre = nombre_input;
		costoAdicional = costoAdicional_input;
	}
	
	public String getNombre() {

	public String get_nombre() {
		// TODO Auto-generated method stub
		return nombre;
	}
	
	public int getCostoAdicional() {

	public int get_precio() {
		// TODO Auto-generated method stub
		return costoAdicional;
	}

	public String Generar_texto_factura() {
		// TODO Auto-generated method stub
		return nombre+": "+costoAdicional+" $";
	}
	
}
