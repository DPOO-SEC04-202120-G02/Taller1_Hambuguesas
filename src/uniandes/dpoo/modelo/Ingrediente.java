package uniandes.dpoo.modelo;

public class Ingrediente {
	
	String nombre;
	
	int costoAdicional;
	
	
	public Ingrediente(String nombre, int costoAdicional) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getCostoAdicional() {
		return costoAdicional;
	}

}
