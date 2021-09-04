package uniandes.dpoo.modelo;

import java.util.ArrayList;

import uniandes.dpoo.procesamiento.Producto;

public class ProductoAjustado implements Producto {
	
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	
	public ProductoAjustado(ProductoMenu base_input,ArrayList<Ingrediente> agregados_input,ArrayList<Ingrediente> eliminados_input){
		// TODO Auto-generated constructor stub
		base=base_input;
		agregados=agregados_input;
		eliminados=eliminados_input;
	}

	@Override
	public String get_nombre() {
		// TODO Auto-generated method stub
		String nombre_base=base.get_nombre()+", agragados(";
		for (int i = 0; i < agregados.size(); i++) {
			String n=agregados.get(i).get_nombre();
			nombre_base+=n;
		}
		nombre_base+="), eliminados(";
		for (int j = 0; j < eliminados.size(); j++) {
			String n=eliminados.get(j).get_nombre();
			nombre_base+=n;
		}
		nombre_base+=")";
		/*formato del nombre: "haburgesa, agregados(awdw,wadfef,efse), eliminados(adawd, defe,qwew)" */
		return nombre_base;
	}

	@Override
	public int get_precio() {
		// TODO Auto-generated method stub
		int precio=base.get_precio();
		for (int i = 0; i < agregados.size(); i++) {
			precio+=agregados.get(i).get_precio();
		}
		return precio;
	}

	@Override
	public String Generar_texto_factura() {
		// TODO Auto-generated method stub
		return this.get_nombre()+": "+this.get_precio()+" $";
	}

}
