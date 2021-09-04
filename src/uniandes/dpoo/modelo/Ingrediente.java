package uniandes.dpoo.modelo;

public class Ingrediente{
	
	private String nombre;
	private int costoAdicional;
	
	public Ingrediente(String nombre_input, int costoAdicional_input) {
		// TODO Auto-generated constructor stub
		nombre = nombre_input;
		costoAdicional = costoAdicional_input;
	}

	public String get_nombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	public int get_precio() {
		// TODO Auto-generated method stub
		return costoAdicional;
	}

	public String Generar_texto_factura() {
		// TODO Auto-generated method stub
		return nombre+": "+costoAdicional+" $";
	}
	
}
